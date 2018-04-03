package com.tomszom.smogapp.city

import com.tomszom.smogapp.city.measure.MeasureViewModel
import com.tomszom.smogapp.model.Sensor
import com.tomszom.smogapp.model.SensorData
import com.tomszom.smogapp.network.NetworkRestAdapter
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by tsm on 03/04/2018
 */
class CityProvider : CityContract.Provider {
    private val airQualityService = NetworkRestAdapter().createGiosAirQualityService()

    override fun getMeasures(stationId: Long): Single<List<MeasureViewModel>> {
        return getSensors(stationId).flatMap {
            Observable.fromIterable(it)
                    .map(this::measuresFromSensor)
                    .toList()
        }
    }

    private fun getSensors(stationId: Long): Single<List<Sensor>> = airQualityService.getSensors(stationId.toString())

    private fun measuresFromSensor(sensor: Sensor): MeasureViewModel {
        val sensorData = getSensorData(sensor.id).toObservable().blockingFirst()

        return MeasureViewModel(sensor.param.paramFormula, sensorData.values)
    }

    private fun getSensorData(sensorId: Long): Single<SensorData> = airQualityService.getSensorData(sensorId.toString())

}