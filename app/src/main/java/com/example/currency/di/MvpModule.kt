package com.example.currency.di

import com.example.currency.mvp.presenter.CurrenciesPresenter
import com.example.currency.mvp.presenter.LatestChartPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MvpModule {
    @Provides
    @Singleton
    fun provideCurrenciesPresenter(): CurrenciesPresenter = CurrenciesPresenter()
    @Provides
    @Singleton
    fun provideLatestChartPresenter(): LatestChartPresenter = LatestChartPresenter()
}