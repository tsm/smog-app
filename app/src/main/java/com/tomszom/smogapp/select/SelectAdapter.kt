package com.tomszom.smogapp.select

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tomszom.smogapp.R
import com.tomszom.smogapp.model.Station
import com.tomszom.smogapp.utils.inflate

/**
 * Created by tsm on 31/03/2018
 */
class SelectAdapter : RecyclerView.Adapter<SelectAdapter.SelectViewHolder>() {

    var selectList: List<Station> = mutableListOf()

    override fun getItemCount(): Int = selectList.size

    override fun onBindViewHolder(holder: SelectViewHolder, position: Int) {
        (holder.itemView as TextView).text = selectList[position].stationName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectViewHolder =
            SelectViewHolder(parent.context.inflate(R.layout.select_row))


    class SelectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}