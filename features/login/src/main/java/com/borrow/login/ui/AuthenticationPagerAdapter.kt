package com.borrow.login.ui

import androidx.annotation.NonNull
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.borrow.login.feature.register.presentation.RegisterFragment
import com.borrow.login.feature.signin.presentation.SignInFragment

class AuthenticationPagerAdapter(@NonNull fragment: FragmentActivity) :
    FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int) =
        when (position) {
            SIGN_IN_POSITION -> SignInFragment()
            REGISTER_POSITION -> RegisterFragment()
            else -> SignInFragment()
        }

    override fun getItemCount() = AUTHENTICATION_ADAPTER_SIZE

    companion object {
        private const val SIGN_IN_POSITION = 0
        private const val REGISTER_POSITION = 1
        private const val AUTHENTICATION_ADAPTER_SIZE = 2
    }
}