package com.borrow.feature.splash.di

import androidx.lifecycle.ViewModel
import com.borrow.feature.splash.data.AuthenticatedUserRepository
import com.borrow.feature.splash.data.AuthenticatedUserRepositoryImpl
import com.borrow.feature.splash.presentation.SplashViewModel
import com.borrow.librarybase.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SplashDependenciesModule {

    @Binds
    abstract fun bindAuthenticatedUserRepository(repository: AuthenticatedUserRepositoryImpl): AuthenticatedUserRepository

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

}