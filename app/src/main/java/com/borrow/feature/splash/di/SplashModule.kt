package com.borrow.feature.splash.di

import com.borrow.feature.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SplashModule {

    @ContributesAndroidInjector(modules = [
    SplashDependenciesModule::class
    ])
    abstract fun provideSplashActivity(): SplashActivity
}