package com.borrow.login.feature.signin.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject


interface SignInRepository {
    fun firebaseSignInWithGoogle(googleAuthCredential: AuthCredential): MutableLiveData<FirebaseUser>
    fun createUserInFirestoreIfNotExists(authenticatedUser: FirebaseUser): MutableLiveData<FirebaseUser>
    fun logOut()
}

private const val USERS_REF = "USERS"
private const val NO_DISPLAY_NAME = "NO DISPLAY NAME"
private const val NO_EMAIL = "NO EMAIL"

class SignInRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) :
    SignInRepository {

    private val usersRef: CollectionReference by lazy {
        firebaseFirestore.collection(USERS_REF)
    }


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
                        authenticatedUserMutableLiveData.postValue(user)
                    }
                } else {
                    Log.d("SignInRepository", authTask.exception?.message)
                }
            }
        return authenticatedUserMutableLiveData
    }

    override fun createUserInFirestoreIfNotExists(authenticatedUser: FirebaseUser): MutableLiveData<FirebaseUser> {
        val newUserMutableLiveData: MutableLiveData<FirebaseUser> =
            MutableLiveData()
        val uidRef =
            usersRef.document(authenticatedUser.uid)
        uidRef.get()
            .addOnCompleteListener { uidTask: Task<DocumentSnapshot?> ->
                if (uidTask.isSuccessful) {
                    val document = uidTask.result
                    if (!document!!.exists()) {
                        uidRef.set(authenticatedUser)
                            .addOnCompleteListener { userCreationTask: Task<Void?> ->
                                if (userCreationTask.isSuccessful) {
                                    authenticatedUser.isCreated = true
                                    newUserMutableLiveData.setValue(authenticatedUser)
                                } else {
                                    Log.d("SignInRepository", userCreationTask.exception?.message)
                                }
                            }
                    } else {
                        newUserMutableLiveData.setValue(authenticatedUser)
                    }
                } else {
                    Log.d("SignInRepository", uidTask.exception?.message)
                }
            }
        return newUserMutableLiveData
    }


    override fun logOut() {
    }
}