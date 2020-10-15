package com.borrow.login.feature.signin.data


data class FirebaseUser(
    var uid: String,
    var name: String,
    @SuppressWarnings("WeakerAccess") var email: String,
    var isAuthenticated: Boolean = false,
    var isNew: Boolean = false,
    var isCreated: Boolean = false
)
