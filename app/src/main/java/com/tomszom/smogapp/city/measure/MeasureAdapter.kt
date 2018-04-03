package com.tomszom.smogapp.city.measure

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
        holder.itemView.measure_cell_name.text = measure.name
        if (measure.values.isEmpty()) {
            holder.itemView.measure_cell_value.text = ""
        } else {
            val valueStr = measure.values.last().value.toString() + " " + measure.unit
            holder.itemView.measure_cell_value.text = valueStr
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeasureViewHolder =
            MeasureViewHolder(parent.context.inflate(R.layout.measure_cell, parent))


    class MeasureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}