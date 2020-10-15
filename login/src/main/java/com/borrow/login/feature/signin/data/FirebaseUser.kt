package com.borrow.login.feature.signin.data

import com.google.firebase.firestore.Exclude

data class FirebaseUser(
    var uid: String,
    var name: String,
    @SuppressWarnings("WeakerAccess") var email: String,
    @Exclude var isAuthenticated: Boolean = false,
    @Exclude var isNew: Boolean = false,
    @Exclude var isCreated: Boolean = false
)
