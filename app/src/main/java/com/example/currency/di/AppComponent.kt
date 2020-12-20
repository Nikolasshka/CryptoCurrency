package com.example.currency.di

import com.example.currency.MainActivity
import com.example.currency.activities.ChartActivity
import com.example.currency.chart.LatestChart
import com.example.currency.fragments.CurrenciesListFragment
import com.example.currency.mvp.presenter.CurrenciesPresenter
import com.example.currency.mvp.presenter.LatestChartPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RestModule::class, MvpModule::class, ChartModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(presenter: CurrenciesPresenter)
    fun inject(presenter: LatestChartPresenter)
    fun inject(fragment: CurrenciesListFragment)
    fun inject(chart: LatestChart)
    fun inject(activity: ChartActivity)
}