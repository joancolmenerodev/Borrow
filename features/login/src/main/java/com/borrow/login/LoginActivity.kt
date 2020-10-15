package com.borrow.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.borrow.librarybase.viewbinding.viewBinding
import com.borrow.login.databinding.ActivityLoginBinding
import com.borrow.login.ui.AuthenticationPagerAdapter
import dagger.android.AndroidInjection

class LoginActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityLoginBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpViewPager()
    }

    private fun setUpViewPager(){
        binding.vpLogin.adapter = AuthenticationPagerAdapter(this)
    }
}