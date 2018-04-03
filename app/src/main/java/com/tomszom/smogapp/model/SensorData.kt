package com.tomszom.smogapp.model

/**
 * Created by tsm on 03/04/2018
 */
data class SensorData(val key: String, val values: List<MeasureValue>) {

    data class MeasureValue(val value: Double, val date: String)
}