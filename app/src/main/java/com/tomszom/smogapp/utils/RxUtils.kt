package com.tomszom.smogapp.utils

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by tsm on 31/03/2018
 */

fun Disposable?.disposeIfNeeded() {
    if (this != null && !this.isDisposed) {
        this.dispose()
    }
}

fun <T> Observable<T>.applySchedulers(): Observable<T> = subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.applySchedulers(): Single<T> = subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
