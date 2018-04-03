package com.tomszom.smogapp.city.measure

import com.tomszom.smogapp.model.SensorData

/**
 * Created by tsm on 03/04/2018
 */
data class MeasureViewModel(val name: String,
                            val values: List<SensorData.MeasureValue> = ArrayList(),
                            val unit: String = "")