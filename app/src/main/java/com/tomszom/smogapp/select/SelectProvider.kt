package com.tomszom.smogapp.select

import com.tomszom.smogapp.model.Station
import com.tomszom.smogapp.network.NetworkRestAdapter
import io.reactivex.Single

/**
 * Created by tsm on 31/03/2018
 */
class SelectProvider : SelectContract.Provider {

    private val airQualityService = NetworkRestAdapter().createGiosAirQualityService()

    override fun getStations(): Single<List<Station>> = airQualityService.getStations()

}