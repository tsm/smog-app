package com.tomszom.smogapp.select

import com.tomszom.smogapp.BuildConfig
import com.tomszom.smogapp.utils.applySchedulers
import com.tomszom.smogapp.utils.disposeIfNeeded
import io.reactivex.disposables.Disposable

/**
 * Created by tsm on 31/03/2018
 */
class SelectPresenter : SelectContract.Presenter {
    private var view: SelectContract.View? = null

    private val provider: SelectContract.Provider = SelectProvider() // TODO inject
    private var stationsDisposable: Disposable? = null

    override fun attach(view: SelectContract.View) {
        this.view = view
        refresh(true)
    }

    override fun detach() {
        stationsDisposable.disposeIfNeeded()
        view = null
    }

    override fun refresh(showProgress: Boolean) {
        stationsDisposable.disposeIfNeeded()
        stationsDisposable = provider.getStations()
                .applySchedulers()
                .doOnSubscribe { if (showProgress) view?.showLoading() }
                .subscribe(
                        { list ->
                            view?.showSelectionList(list)
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