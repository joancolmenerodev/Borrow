package com.borrow.di.modules

import android.content.Context
import com.borrow.App
import dagger.Module
import dagger.Provides

@Module
object AppModule {

    @Provides
    fun provideContext(application: App): Context = application
}