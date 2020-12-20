package com.example.currency.di

import com.example.currency.chart.LatestChart
import com.example.currency.formatters.YearValueFormatter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ChartModule {
    @Provides
    @Singleton
    fun provideLatestChart() = LatestChart()

    @Provides
    @Singleton
    fun provideYearFormatter() = YearValueFormatter()
}