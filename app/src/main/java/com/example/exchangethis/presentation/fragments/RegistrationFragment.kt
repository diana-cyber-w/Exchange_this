package com.example.exchangethis.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.exchangethis.R
import com.example.exchangethis.databinding.LoginLayoutBinding
import com.example.exchangethis.presentation.recycler.LoginAdapter
import com.example.exchangethis.utils.StringUtils
import com.google.android.material.tabs.TabLayoutMediator

class RegistrationFragment : Fragment(R.layout.login_layout) {

    private val binding: LoginLayoutBinding by viewBinding(LoginLayoutBinding::bind)
    private val adapter by lazy {
        LoginAdapter(
            requireActivity(),
            binding.tabLayout.tabCount,
            requireContext()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> StringUtils.LOGIN
                1 -> StringUtils.SIGN_UP
                else -> null
            }
        }.attach()
    }
}