package com.alextroy.habrss.di

import com.alextroy.habrss.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetModule::class])
interface NetComponent {
    fun inject(mainActivity: MainActivity)
}