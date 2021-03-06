package com.borrow.feature.splash.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.borrow.feature.splash.data.AuthenticatedUserRepository
import javax.inject.Inject


class SplashViewModel @Inject constructor(
    private val authenticatedUserRepository: AuthenticatedUserRepository
) : ViewModel() {

    private val _splashViewState =
        MutableLiveData<SplashViewState>()
    val splashViewState: LiveData<SplashViewState>
        get() = _splashViewState

    fun checkIfUserIsAuthenticatedInFirebase() {
        val firebaseUser = authenticatedUserRepository.isLogged()
        if (firebaseUser == null) {
            _splashViewState.postValue(SplashViewState.UserNotAuthenticated)
        } else {
            _splashViewState.postValue(SplashViewState.UserAuthenticated)
        }
    }
}


sealed class SplashViewState {
    object UserAuthenticated : SplashViewState()
    object UserNotAuthenticated : SplashViewState()
}