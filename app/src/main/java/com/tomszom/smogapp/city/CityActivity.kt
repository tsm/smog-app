package com.tomszom.smogapp.city

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.tomszom.smogapp.R
import com.tomszom.smogapp.city.measure.MeasureViewModel
import com.tomszom.smogapp.model.Station

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

    //val presenter : CityContract.Presenter = CityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_activity)

        initToolbar()

        val station = intent.extras.getParcelable<Station>(STATION_KEY)

        showStationInfo(station)
    }

    private fun initToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.toolbarTranslucent)))
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNoData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMeasures(measures: List<MeasureViewModel>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}