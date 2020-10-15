package com.borrow.login.feature.signin.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


interface SignInRepository {
    fun firebaseSignInWithGoogle(googleAuthCredential: AuthCredential): MutableLiveData<FirebaseUser>
    fun logOut()
}

private const val NO_DISPLAY_NAME = "NO DISPLAY NAME"
private const val NO_EMAIL = "NO EMAIL"

class SignInRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) :
    SignInRepository {

    override fun firebaseSignInWithGoogle(googleAuthCredential: AuthCredential): MutableLiveData<FirebaseUser> {
        val authenticatedUserMutableLiveData: MutableLiveData<FirebaseUser> = MutableLiveData()
        firebaseAuth.signInWithCredential(googleAuthCredential)
            .addOnCompleteListener { authTask: Task<AuthResult> ->
                if (authTask.isSuccessful) {
                    val isNewUser =
                        authTask.result?.additionalUserInfo?.isNewUser
                    val firebaseUser: com.google.firebase.auth.FirebaseUser? =
                        firebaseAuth.currentUser
                    if (firebaseUser != null) {
                        val uid = firebaseUser.uid
                        val name: String = firebaseUser.displayName ?: NO_DISPLAY_NAME
                        val email = firebaseUser.email
                        val user = FirebaseUser(uid, name, email ?: NO_EMAIL)
                        user.isNew = isNewUser ?: false
                        Log.d("FirebaseUser", firebaseUser.toString())
                        authenticatedUserMutableLiveData.postValue(user)
                    }
                } else {
                    Log.d("FirebaseUser", authTask.exception?.message)
                }
            }
        return authenticatedUserMutableLiveData
    }

    override fun logOut() {
    }
}