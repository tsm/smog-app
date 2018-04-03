package com.tomszom.smogapp.city

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.view.MenuItem
import com.tomszom.smogapp.R
import com.tomszom.smogapp.city.measure.MeasureAdapter
import com.tomszom.smogapp.city.measure.MeasureViewModel
import com.tomszom.smogapp.model.Station
import com.tomszom.smogapp.utils.gone
import com.tomszom.smogapp.utils.visible
import kotlinx.android.synthetic.main.city_activity.*

/**
 * Created by tsm on 03/04/2018
 */
class CityActivity : AppCompatActivity(), CityContract.View {

    companion object {
        private val STATION_KEY = "station_key"

        fun createIntent(context: Context, station: Station): Intent {
            val intent = Intent(context, CityActivity::class.java)
            intent.putExtra(STATION_KEY, station)

            return intent
        }
    }

    private val presenter: CityContract.Presenter = CityPresenter() // TODO inject
    private val measureAdapter = MeasureAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.city_activity)

        initToolbar()
        initRecycler()

        val station = intent.extras.getParcelable<Station>(STATION_KEY)

        presenter.attach(this)
        presenter.selectStation(station.id)

        showStationInfo(station)

    }

    private fun initToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.toolbarTranslucent)))
    }

    private fun initRecycler() {
        val layoutManager = GridLayoutManager(this, 3)
        city_measure_recycler.layoutManager = layoutManager

        city_measure_recycler.adapter = measureAdapter

        val dividerItemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        city_measure_recycler.addItemDecoration(dividerItemDecoration)
    }

    private fun showStationInfo(station: Station) {
        supportActionBar?.title = station.stationName
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showLoading() {
        city_measure_recycler.gone()
        city_msg.gone()
        city_progress.visible()
    }

    override fun showNoData() {
        showMsg(R.string.common_no_data)
    }

    override fun showError() {
        showMsg(R.string.common_error_try_later)
    }

    private fun showMsg(strRes: Int) {
        city_measure_recycler.gone()
        city_msg.visible()
        city_msg.setText(strRes)
        city_progress.gone()
    }

    override fun showMeasures(measures: List<MeasureViewModel>) {
        if (measures.isEmpty()) {
            showNoData()
        } else {
            measureAdapter.measureList = measures
            measureAdapter.notifyDataSetChanged()

            city_measure_recycler.visible()
            city_msg.gone()
            city_progress.gone()
        }
    }

}