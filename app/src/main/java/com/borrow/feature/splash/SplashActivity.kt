package com.borrow.feature.splash

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.borrow.R
import com.borrow.feature.splash.presentation.SplashViewModel
import com.borrow.feature.splash.presentation.SplashViewState
import com.borrow.home.HomeActivity
import com.borrow.librarybase.extensions.observe
import com.borrow.login.LoginActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val splashViewModel by viewModels<SplashViewModel> { viewModelFactory }


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splashViewModel.checkIfUserIsAuthenticatedInFirebase()

        observe(
            splashViewModel.splashViewState,
            {
                when (it) {
                    is SplashViewState.UserAuthenticated -> {
                        Toast.makeText(this, "User Authenticated", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    }
                    is SplashViewState.UserNotAuthenticated -> {
                        Toast.makeText(this, "User Not Authenticated", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                }
            }
        )

    }
}