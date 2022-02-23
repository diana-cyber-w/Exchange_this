package com.example.exchangethis.presentation.recycler

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.exchangethis.presentation.fragments.LoginFragment
import com.example.exchangethis.presentation.fragments.SignUpFragment

class LoginAdapter(
    fragmentActivity: FragmentActivity,
    private val totalTabs: Int
) : FragmentStateAdapter(fragmentActivity) {

    private val loginFragment by lazy { LoginFragment() }
    private val signUpFragment by lazy { SignUpFragment() }

    override fun getItemCount() = totalTabs

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> loginFragment
            1 -> signUpFragment
            else -> throw IllegalStateException(UNKNOWN_POSITION_ERROR)
        }
    }

    companion object {
        private const val UNKNOWN_POSITION_ERROR = "Unknown position"
    }
}