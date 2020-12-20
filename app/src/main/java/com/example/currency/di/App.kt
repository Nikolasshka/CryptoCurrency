package com.example.currency.di

import android.app.Application
import com.example.currency.di.DaggerAppComponent

class App : Application() {
    companion object{
        lateinit var appComponent: AppComponent
    }
    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .restModule(RestModule())
                .mvpModule(MvpModule())
                .chartModule(ChartModule())
                .build()
    }
}
