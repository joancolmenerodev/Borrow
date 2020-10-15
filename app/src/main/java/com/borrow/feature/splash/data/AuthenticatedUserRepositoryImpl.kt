package com.borrow.feature.splash.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject


interface AuthenticatedUserRepository {
    fun isLogged(): FirebaseUser?
}

class AuthenticatedUserRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    AuthenticatedUserRepository {

    override fun isLogged() = firebaseAuth.currentUser
}