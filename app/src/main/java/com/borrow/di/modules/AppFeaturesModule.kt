package com.borrow.di.modules

import com.borrow.feature.splash.di.SplashModule
import com.borrow.home.di.HomeModule
import com.borrow.login.di.LoginModule
import dagger.Module

@Module(
    includes = [
        SplashModule::class,
        LoginModule::class,
        HomeModule::class
    ]
)
object AppFeaturesModule