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
        fun attach(view: View, stationId: Long)
        fun detach()
        fun refresh(showProgress: Boolean)
    }

    interface Provider {
        fun getMeasures(): Single<List<MeasureViewModel>>
    }
}