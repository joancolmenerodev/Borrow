package com.borrow.di

import com.borrow.App
import com.borrow.di.modules.AppFeaturesModule
import com.borrow.di.modules.AppModule
import com.borrow.di.modules.DispatcherModule
import com.borrow.firebase.di.FirebaseModule
import com.borrow.librarybase.di.ViewModelsModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ViewModelsModule::class,
        AppFeaturesModule::class,
        FirebaseModule::class,
        DispatcherModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: App): AppComponent
    }
}