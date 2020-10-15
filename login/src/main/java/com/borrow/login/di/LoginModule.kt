package com.borrow.login.di

import com.borrow.login.LoginActivity
import com.borrow.login.feature.register.presentation.RegisterFragment
import com.borrow.login.feature.signin.di.SignInDependenciesModules
import com.borrow.login.feature.signin.presentation.SignInFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginModule {

    @ContributesAndroidInjector
    abstract fun provideLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [SignInDependenciesModules::class])
    abstract fun provideSignInFragment(): SignInFragment

    @ContributesAndroidInjector
    abstract fun provideRegisterFragment(): RegisterFragment


}