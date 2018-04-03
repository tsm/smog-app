package com.tomszom.smogapp.city

import com.tomszom.smogapp.BuildConfig
import com.tomszom.smogapp.utils.applySchedulers
import com.tomszom.smogapp.utils.disposeIfNeeded
import io.reactivex.disposables.Disposable

/**
 * Created by tsm on 03/04/2018
 */
class CityPresenter : CityContract.Presenter {

    private var view: CityContract.View? = null
    private var selectedStation = Long.MIN_VALUE

    private val provider: CityContract.Provider = CityProvider() // TODO inject
    private var measuresDisposable: Disposable? = null

    override fun attach(view: CityContract.View) {
        this.view = view
    }

    override fun detach() {
        measuresDisposable.disposeIfNeeded()
        view = null
    }

    override fun selectStation(stationId: Long) {
        selectedStation = stationId
        refresh()
    }

    override fun refresh() {
        measuresDisposable.disposeIfNeeded()
        measuresDisposable = provider.getMeasures(selectedStation)
                .applySchedulers()
                .doOnSubscribe { view?.showLoading() }
                .subscribe(
                        { list ->
                            view?.showMeasures(list)
                        },
                        { e ->
                            view?.showError()
                            if (BuildConfig.DEBUG) {
                                e.printStackTrace()
                            }
                        }
                )
    }
}