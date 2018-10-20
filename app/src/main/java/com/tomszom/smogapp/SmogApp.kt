package com.tomszom.smogapp

import android.app.Application
import com.tomszom.smogapp.select.SelectContract
import com.tomszom.smogapp.select.SelectPresenter
import com.tomszom.smogapp.select.SelectProvider
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

/**
 * Created by tsm on 27/04/2018
 */
class SmogApp : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        bind<SelectContract.Provider>() with provider { SelectProvider() }
        bind<SelectContract.Presenter>() with provider { SelectPresenter() }
    }
}