package com.tomszom.smogapp.city

import com.tomszom.smogapp.city.measure.MeasureViewModel
import io.reactivex.Single

/**
 * Created by tsm on 03/04/2018
 */
interface CityContract {
    interface View {
        fun showLoading()
        fun showNoData()
        fun showError()
        fun showMeasures(measures: List<MeasureViewModel>)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun selectStation(stationId: Long)
        fun refresh()
    }

    interface Provider {
        fun getMeasures(stationId: Long): Single<List<MeasureViewModel>>
    }
}