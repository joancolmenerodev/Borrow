package com.borrow.login.feature.signin.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borrow.login.feature.signin.data.SignInRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val USER_LOGIN_FAILED = "User Login Failed"

class SignInViewModel @Inject constructor(private val signInRepository: SignInRepository) :
    ViewModel() {

    private val _authenticatedUser =
        MutableLiveData<SignInViewState<FirebaseUser>>()
    val authenticatedUser: LiveData<SignInViewState<FirebaseUser>>
        get() = _authenticatedUser


    fun signInWithGoogle(googleAuthCredential: AuthCredential?) {
        viewModelScope.launch {
            googleAuthCredential?.let {
                val firebaseUser = signInRepository.firebaseSignInWithGoogle(googleAuthCredential)
                if (firebaseUser != null) _authenticatedUser.postValue(
                    SignInViewState.Success(
                        firebaseUser
                    )
                )
                else _authenticatedUser.postValue(SignInViewState.Error(Exception(USER_LOGIN_FAILED)))
            }
        }
    }

}

sealed class SignInViewState<out T> {
    data class Success<out T>(val result: T) : SignInViewState<T>()
    data class Error<out T>(val result: Exception) : SignInViewState<T>()
}