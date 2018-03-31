package com.tomszom.smogapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.tomszom.smogapp.model.Station
import com.tomszom.smogapp.network.NetworkRestAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getStationsObservable().subscribe { stations ->
            for (station in stations) {
                Log.d("MainActivity", "got station ${station.stationName} with id ${station.id} ")
            }
            Toast.makeText(this, "Received ${stations.size} stations", Toast.LENGTH_LONG).show()
        }
    }

    private fun getStationsObservable(): Observable<List<Station>> {

        return NetworkRestAdapter().createGiosAirQualityService().getStations().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
