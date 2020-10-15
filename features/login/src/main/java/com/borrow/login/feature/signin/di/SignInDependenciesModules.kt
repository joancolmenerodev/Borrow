package com.borrow.login.feature.signin.di

import androidx.lifecycle.ViewModel
import com.borrow.librarybase.di.ViewModelKey
import com.borrow.login.feature.signin.data.SignInRepository
import com.borrow.login.feature.signin.data.SignInRepositoryImpl
import com.borrow.login.feature.signin.presentation.SignInViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SignInDependenciesModules {

    @Binds
    abstract fun bindSignInRepository(repository: SignInRepositoryImpl): SignInRepository

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    abstract fun bindSignInViewModel(viewModel: SignInViewModel): ViewModel


}