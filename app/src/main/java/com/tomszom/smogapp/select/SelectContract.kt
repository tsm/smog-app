package com.tomszom.smogapp.select

import com.tomszom.smogapp.model.Station
import io.reactivex.Single

/**
 * Created by tsm on 31/03/2018
 */
interface SelectContract {

    interface View {
        fun showLoading()
        fun showNoData()
        fun showError()
        fun showSelectionList(selectionList: List<Station>)
        fun startStationActivity(stationId: Long)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun refresh(showProgress: Boolean)
    }

    interface Provider {
        fun getStations(): Single<List<Station>>
    }
}