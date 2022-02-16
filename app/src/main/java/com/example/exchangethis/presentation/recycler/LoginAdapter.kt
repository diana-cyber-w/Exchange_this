package com.example.exchangethis.presentation.recycler

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.exchangethis.presentation.fragments.LoginFragment
import com.example.exchangethis.presentation.fragments.SignUpFragment
import com.example.exchangethis.utils.StringUtils

class LoginAdapter(
    fragmentActivity: FragmentActivity,
    private val totalTabs: Int,
    context: Context
) : FragmentStateAdapter(fragmentActivity) {

    private val loginFragment by lazy { LoginFragment(context) }
    private val signUpFragment by lazy { SignUpFragment(context) }

    override fun getItemCount() = totalTabs

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> loginFragment
            1 -> signUpFragment
            else -> throw IllegalStateException(StringUtils.UNKNOWN_POSITION_ERROR)
        }
    }

}