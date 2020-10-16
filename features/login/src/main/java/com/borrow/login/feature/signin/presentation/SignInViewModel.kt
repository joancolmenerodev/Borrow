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


class SignInViewModel @Inject constructor(private val signInRepository: SignInRepository) :
    ViewModel() {

    private val _authenticatedUser =
        MutableLiveData<FirebaseUser>()
    val authenticatedUser: LiveData<FirebaseUser>
        get() = _authenticatedUser

    fun signInWithGoogle(googleAuthCredential: AuthCredential?) {
        viewModelScope.launch {
            googleAuthCredential?.let {
                val firebaseUser = signInRepository.firebaseSignInWithGoogle(googleAuthCredential)
                _authenticatedUser.postValue(firebaseUser)
            }
        }
    }

}

sealed class SignInViewState<out T> {
    data class Success<out T>(val result: T) : SignInViewState<T>()
    data class Error<out T>(val result: Exception) : SignInViewState<T>()
}