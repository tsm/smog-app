package com.tomszom.smogapp.city.measure

import android.animation.*
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.tomszom.smogapp.R
import com.tomszom.smogapp.utils.inflate
import kotlinx.android.synthetic.main.measure_cell.view.*

/**
 * Created by tsm on 03/04/2018
 */
class MeasureAdapter : RecyclerView.Adapter<MeasureAdapter.MeasureViewHolder>() {

    var measureList: List<MeasureViewModel> = mutableListOf()

    override fun getItemCount(): Int = measureList.size

    override fun onBindViewHolder(holder: MeasureViewHolder, position: Int) {
        val measure = measureList[position]

        var value = if (measure.values.isEmpty()) null else measure.values.last().value

        var name = measure.name
        var unit = measure.unit
        var part: MeasureParticle? = null
        try {
            part = MeasureParticle.valueOf(measure.name.toUpperCase().replace(".", ""))
            name = part.formula
            unit = part.unit
        } catch (ignore: IllegalArgumentException) {
        }

        holder.itemView.measure_cell_name.text = name

        if (value == null || value < 0) { //not available
            holder.itemView.measure_cell_value.setText(R.string.not_available)
            holder.itemView.measure_cell_percent.text = ""
            holder.itemView.measure_cell_container.setBackgroundColor(
                    ContextCompat.getColor(holder.itemView.context, R.color.condition_not_available))
        } else {
            if (value > 0.0) { // animation if there is positive value
                val valueAnim = ValueAnimator.ofFloat(0.0f, value.toFloat())
                valueAnim.duration = 2500

                valueAnim.addUpdateListener({ animation ->
                    val animValue = (animation.animatedValue as Float)
                    holder.showValue(animValue.toDouble(), unit, part)
                })

                valueAnim.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {

                        //show final value anyway (double to float conversion may cause error)
                        holder.showValue(value, unit, part)

                        // if value is greater than last threshold show pulse animation
                        if (part != null && value > part?.threshold5 ?: Double.MAX_VALUE) {
                            holder.pulseAnimate()
                        }
                    }
                })

                valueAnim.start()
            } else {
                holder.showValue(value, unit, part)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeasureViewHolder =
            MeasureViewHolder(parent.context.inflate(R.layout.measure_cell, parent))


    class MeasureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun showValue(value: Double, unit: String, part: MeasureParticle? = null) {
            var color: Int = R.color.condition_not_available
            var percent = ""
            if (part != null) {
                when (value) {
                    in (0.0..part.threshold1) -> color = R.color.condition_very_good
                    in (part.threshold1..part.threshold2) -> color = R.color.condition_good
                    in (part.threshold2..part.threshold3) -> color = R.color.condition_ok
                    in (part.threshold3..part.threshold4) -> color = R.color.condition_average
                    in (part.threshold4..part.threshold5) -> color = R.color.condition_bad
                    in (part.threshold5..Double.MAX_VALUE) -> color = R.color.condition_very_bad
                }
                percent = String.format("%.0f", value / part.threshold5 * 100.0) + "%"
            }

            itemView.measure_cell_container.setBackgroundColor(ContextCompat.getColor(itemView.context, color))
            val valueStr = String.format("%.2f", value) + " " + unit
            itemView.measure_cell_value.text = valueStr
            itemView.measure_cell_percent.text = percent
        }

        fun pulseAnimate() {
            val scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                    itemView,
                    PropertyValuesHolder.ofFloat("scaleX", 1.1f),
                    PropertyValuesHolder.ofFloat("scaleY", 1.1f))
            scaleDown.duration = 500

            scaleDown.repeatCount = ObjectAnimator.INFINITE
            scaleDown.repeatMode = ObjectAnimator.REVERSE

            scaleDown.start()
        }
    }

}