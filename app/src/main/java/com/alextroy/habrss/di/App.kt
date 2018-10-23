package com.alextroy.habrss.di

import android.app.Application

class App : Application() {

    private lateinit var netComponent: NetComponent

    override fun onCreate() {
        super.onCreate()
        netComponent = DaggerNetComponent.builder()
            .netModule(NetModule)
            .build()
    }

    fun getComponent(): NetComponent = netComponent
}