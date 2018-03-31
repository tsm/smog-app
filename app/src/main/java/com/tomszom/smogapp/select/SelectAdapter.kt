package com.tomszom.smogapp.select

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.tomszom.smogapp.R
import com.tomszom.smogapp.model.Station
import com.tomszom.smogapp.utils.inflate
import kotlinx.android.synthetic.main.select_row.view.*

/**
 * Created by tsm on 31/03/2018
 */
class SelectAdapter(private val itemClickListener: SelectClickListener) : RecyclerView.Adapter<SelectAdapter.SelectViewHolder>() {

    var selectList: List<Station> = mutableListOf()

    override fun getItemCount(): Int = selectList.size

    override fun onBindViewHolder(holder: SelectViewHolder, position: Int) {
        val station = selectList[position]
        holder.itemView.select_row_title.text = station.stationName
        holder.itemView.setOnClickListener { itemClickListener.selectionItemClicked(station.id) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectViewHolder =
            SelectViewHolder(parent.context.inflate(R.layout.select_row, parent))


    class SelectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface SelectClickListener {
        fun selectionItemClicked(id: Long)
    }
}