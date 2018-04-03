package com.tomszom.smogapp.city.measure

/**
 * Created by tsm on 03/04/2018
 */
data class MeasureViewModel(val name: String,
                            val values: List<MeasureValue>) {

    data class MeasureValue(val value: Double, val date: String)
}