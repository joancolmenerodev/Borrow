package com.borrow.login.feature.signin.data

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume


interface SignInRepository {
    suspend fun firebaseSignInWithGoogle(googleAuthCredential: AuthCredential): FirebaseUser?
}

class SignInRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) :
    SignInRepository {

    override suspend fun firebaseSignInWithGoogle(googleAuthCredential: AuthCredential): FirebaseUser? {
        return suspendCancellableCoroutine { continuation ->
            firebaseAuth.signInWithCredential(googleAuthCredential)
                .addOnCompleteListener { authTask: Task<AuthResult> ->
                    if (authTask.isSuccessful) {
                        val firebaseUser: FirebaseUser? =
                            firebaseAuth.currentUser
                        continuation.resume(firebaseUser)
                    } else {
                        continuation.resume(null)
                    }
                }
        }
    }

}