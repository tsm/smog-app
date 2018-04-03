package com.tomszom.smogapp.city

import com.tomszom.smogapp.model.Sensor
import io.reactivex.Single

/**
 * Created by tsm on 03/04/2018
 */
interface CityContract {
    interface View {
        fun showLoading()
        fun showNoData()
        fun showError()
        fun showMeasurements(dataList: List<Sensor>)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun refresh(showProgress: Boolean)
    }

    interface Provider {
        fun getMeasurements(): Single<List<Sensor>>
    }
}