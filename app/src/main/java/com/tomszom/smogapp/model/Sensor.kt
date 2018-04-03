package com.tomszom.smogapp.model

/**
 * Created by tsm on 31/03/2018
 */
data class Sensor(val id: Long, val stationId: Long?, val param: Param) {

    data class Param(val paramName: String = "", val paramFormula: String = "", val idParam: Long?)
}