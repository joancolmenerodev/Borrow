package com.borrow.feature.splash.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject


interface AuthenticatedUserRepository {
    fun isLogged(): MutableLiveData<FirebaseUser>
}

class AuthenticatedUserRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    AuthenticatedUserRepository {

    override fun isLogged(): MutableLiveData<FirebaseUser> {
        val authenticatedUser: MutableLiveData<FirebaseUser> =
            MutableLiveData()
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) authenticatedUser.postValue(currentUser)
        else authenticatedUser.postValue(null)
        return authenticatedUser
    }
}