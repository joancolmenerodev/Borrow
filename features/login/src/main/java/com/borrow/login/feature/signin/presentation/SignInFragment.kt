package com.borrow.login.feature.signin.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.borrow.home.HomeActivity
import com.borrow.librarybase.extensions.observe
import com.borrow.librarybase.viewbinding.viewBinding
import com.borrow.login.R
import com.borrow.login.databinding.FragmentSigninBinding
import com.borrow.login.feature.signin.data.FirebaseUser
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


private const val RC_SIGN_IN = 123

class SignInFragment : Fragment(R.layout.fragment_signin) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var googleSignInClient: GoogleSignInClient

    private val signInViewModel by viewModels<SignInViewModel> { viewModelFactory }


    private val binding by viewBinding(FragmentSigninBinding::bind)

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSignInButton()

        observe(
            signInViewModel.signInViewState,
            { viewState -> viewState?.let { displaySignInResult(it) } })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val googleSignInAccount: GoogleSignInAccount? =
                    task.getResult(ApiException::class.java)
                googleSignInAccount?.let { getGoogleAuthCredential(it) }
            } catch (e: ApiException) {
                Log.d("SignInFragment", e.message)
            }
        }
    }

    private fun displaySignInResult(it: SignInViewState<FirebaseUser>) {
        when (it) {
            is SignInViewState.Success -> {
                Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
            }
            is SignInViewState.Error -> {
                Toast.makeText(context, it.result.message, Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun signInWithGoogleAuthCredential(googleAuthCredential: AuthCredential) {
        signInViewModel.signInWithGoogle(googleAuthCredential)
        signInViewModel.authenticatedUser?.observe(this) { authenticatedUser ->
            toastMessage("User created $authenticatedUser")
            requireContext().startActivity(Intent(requireContext(), HomeActivity::class.java))
            activity?.finish()
        }
    }

    private fun toastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


    private fun getGoogleAuthCredential(googleSignInAccount: GoogleSignInAccount) {
        val googleTokenId = googleSignInAccount.idToken
        val googleAuthCredential =
            GoogleAuthProvider.getCredential(googleTokenId, null)
        signInWithGoogleAuthCredential(googleAuthCredential)
    }

    private fun initSignInButton() {
        binding.signInGoogle.setOnClickListener { signIn() }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
}