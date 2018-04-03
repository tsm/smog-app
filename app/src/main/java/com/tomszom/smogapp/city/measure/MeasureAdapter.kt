package com.tomszom.smogapp.city.measure

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

        val value = if (measure.values.isEmpty()) null else measure.values.last().value

        var color: Int = R.color.condition_not_available
        var name = measure.name
        var unit = measure.unit
        var percent = ""
        try {
            val part = MeasureParticle.valueOf(measure.name.toUpperCase().replace(".", ""))

            name = part.formula
            unit = part.unit
            if (value != null) {
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

        } catch (ignore: IllegalArgumentException) {
        }

        holder.itemView.measure_cell_name.text = name
        holder.itemView.measure_cell_container.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, color))
        if (value == null || value < 0) {
            holder.itemView.measure_cell_value.setText(R.string.not_available)
            holder.itemView.measure_cell_percent.text = ""
        } else {
            val valueStr = String.format("%.2f", value) + " " + unit
            holder.itemView.measure_cell_value.text = valueStr
            holder.itemView.measure_cell_percent.text = percent
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeasureViewHolder =
            MeasureViewHolder(parent.context.inflate(R.layout.measure_cell, parent))


    class MeasureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}