package com.tomszom.smogapp.network

import com.tomszom.smogapp.model.Sensor
import com.tomszom.smogapp.model.Station
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by tsm on 31/03/2018
 */

const val GIOS_ENDPOINT = "http://api.gios.gov.pl/pjp-api/rest/"

interface GiosAirQualityService {

    @GET("station/findAll")
    fun getStations(): Single<List<Station>>

    @GET("station/sensors/{stationId}")
    fun getSensors(@Path("stationId") stationId: String): Single<List<Sensor>>
}