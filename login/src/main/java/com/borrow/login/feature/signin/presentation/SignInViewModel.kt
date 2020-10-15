package com.borrow.login.feature.signin.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.borrow.login.feature.signin.data.FirebaseUser
import com.borrow.login.feature.signin.data.SignInRepository
import com.google.firebase.auth.AuthCredential
import javax.inject.Inject


class SignInViewModel @Inject constructor(private val signInRepository: SignInRepository) :
    ViewModel() {

    var authenticatedUser: LiveData<FirebaseUser>? = null

    private val _signInViewState =
        MutableLiveData<SignInViewState<FirebaseUser>>()
    val signInViewState: LiveData<SignInViewState<FirebaseUser>>
        get() = _signInViewState

    fun signInWithGoogle(googleAuthCredential: AuthCredential?) {
        authenticatedUser = googleAuthCredential?.let { authCredential ->
            signInRepository.firebaseSignInWithGoogle(
                authCredential
            )
        }
    }

}

sealed class SignInViewState<out T> {
    data class Success<out T>(val result: T) : SignInViewState<T>()
    data class Error<out T>(val result: Exception) : SignInViewState<T>()
}