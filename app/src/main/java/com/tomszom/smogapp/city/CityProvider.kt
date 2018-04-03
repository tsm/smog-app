package com.tomszom.smogapp.city

import com.tomszom.smogapp.city.measure.MeasureViewModel
import io.reactivex.Single

/**
 * Created by tsm on 03/04/2018
 */
class CityProvider : CityContract.Provider {
    override fun getMeasures(stationId: Long): Single<List<MeasureViewModel>> {
        return Single.fromCallable { mockMeasures() }
    }

    private fun mockMeasures(): List<MeasureViewModel> {
        val measureValues = ArrayList<MeasureViewModel.MeasureValue>()

        measureValues.add(MeasureViewModel.MeasureValue(1.0, "2018-04-03 11:00:00"))
        measureValues.add(MeasureViewModel.MeasureValue(2.0, "2018-04-03 12:00:00"))

        val measures = ArrayList<MeasureViewModel>()
        measures.add(MeasureViewModel("MOCK", measureValues, "mg"))
        measures.add(MeasureViewModel("MOCK2", measureValues, "g"))
        measures.add(MeasureViewModel("MOCK3", measureValues, "ml"))
        measures.add(MeasureViewModel("MOCK4", measureValues))
        measures.add(MeasureViewModel("MOCK5", measureValues, "pH"))

        return measures
    }
}