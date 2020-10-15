package com.borrow.home.di

import com.borrow.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeModule {

    @ContributesAndroidInjector
    abstract fun provideHomeActivity(): HomeActivity
}