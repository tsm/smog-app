package com.tomszom.smogapp.network

import com.tomszom.smogapp.model.Station
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by tsm on 31/03/2018
 */

const val GIOS_ENDPOINT = "http://api.gios.gov.pl/pjp-api/rest/"

interface GiosAirQualityService {

    @GET("station/findAll")
    fun getStations(): Observable<List<Station>>
}