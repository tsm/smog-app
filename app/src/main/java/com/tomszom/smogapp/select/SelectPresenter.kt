package com.tomszom.smogapp.select

import com.tomszom.smogapp.BuildConfig
import com.tomszom.smogapp.utils.applySchedulers

/**
 * Created by tsm on 31/03/2018
 */
class SelectPresenter : SelectContract.Presenter {
    private var view: SelectContract.View? = null
    private val provider: SelectContract.Provider = SelectProvider() // TODO inject

    override fun attach(view: SelectContract.View) {
        this.view = view
        refresh()

    }

    override fun detach() {
        view = null
    }

    override fun refresh() {
        provider.getStations()
                .applySchedulers()
                .doOnSubscribe { view?.showLoading() }
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
                );

    }
}