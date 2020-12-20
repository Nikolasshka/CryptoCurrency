package com.example.currency.mvp.presenter

import com.example.currency.CoinAPI.GeckoCoinAPI
import com.example.currency.di.App
import com.example.currency.mvp.contract.LatestChartContract
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LatestChartPresenter : LatestChartContract.Presenter() {
    @Inject
    lateinit var geckoApi: GeckoCoinAPI
    init {
        App.appComponent.inject(this)
    }
    override fun makeChart(id: String) {
        subscribe(geckoApi.getCoinMarketChart(id)
            .map { it.prices }
            .flatMap { Observable.fromIterable(it) }
            .doOnComplete {
                view.hideProgress()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.hideProgress()
                view.addEntryToChart(it[0], it[1])
            }, {
                view.hideProgress()
                view.showErrorMessage(it.message)
                it.printStackTrace()
            })
        )
    }
    override fun refreshChart() {
        view.refresh()
    }
}