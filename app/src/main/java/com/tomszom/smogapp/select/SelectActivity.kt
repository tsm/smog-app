package com.tomszom.smogapp.select

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.tomszom.smogapp.R
import com.tomszom.smogapp.city.CityActivity
import com.tomszom.smogapp.model.Station
import com.tomszom.smogapp.utils.gone
import com.tomszom.smogapp.utils.visible
import kotlinx.android.synthetic.main.select_activity.*


class SelectActivity : AppCompatActivity(), SelectContract.View, SelectAdapter.SelectClickListener {

    private val presenter: SelectContract.Presenter = SelectPresenter() //TODO inject

    private val selectAdapter = SelectAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_activity)
        presenter.attach(this)

        initRefreshLayout()
        initRecycler()
    }

    private fun initRefreshLayout() {
        select_refresh.setOnRefreshListener { presenter.refresh(false) }
        select_refresh.isRefreshing = false
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        select_recycler.layoutManager = layoutManager

        select_recycler.adapter = selectAdapter

        val dividerItemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        select_recycler.addItemDecoration(dividerItemDecoration)
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun showLoading() {
        select_recycler.gone()
        select_msg.gone()
        select_progress.visible()
    }

    override fun showNoData() {
        showMsg(R.string.common_no_data)
    }

    override fun showError() {
        select_refresh.isRefreshing = false
        showMsg(R.string.common_error_try_later)
    }

    private fun showMsg(strRes: Int) {
        select_recycler.gone()
        select_msg.visible()
        select_msg.setText(strRes)
        select_progress.gone()
    }

    override fun showSelectionList(selectionList: List<Station>) {
        select_refresh.isRefreshing = false
        if (selectionList.isEmpty()) {
            showNoData()
        } else {
            selectAdapter.selectList = selectionList
            selectAdapter.notifyDataSetChanged()

            select_recycler.visible()
            select_msg.gone()
            select_progress.gone()
        }
    }

    override fun selectionItemClicked(station: Station) {
        startCity(station)
    }

    override fun startCity(station: Station) {
        startActivity(CityActivity.createIntent(this, station))
    }
}
