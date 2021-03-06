package com.example.exchangethis.presentation.fragments

import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.exchangethis.R
import com.example.exchangethis.databinding.WelcomeLayoutBinding
import com.example.exchangethis.utils.preference.SharedPreferenceManagerImpl

class WelcomeFragment : Fragment(R.layout.welcome_layout) {

    private val binding: WelcomeLayoutBinding by viewBinding(WelcomeLayoutBinding::bind)
    private val prefs by lazy { SharedPreferenceManagerImpl(requireContext()) }

    override fun onStart() {
        super.onStart()

        val image = binding.mainImageView
        val logo = binding.logo
        val slogan = binding.slogan
        val topAnim: Animation = AnimationUtils.loadAnimation(context, R.anim.top_animation)
        val bottomAnim: Animation =
            AnimationUtils.loadAnimation(context, R.anim.bottom_animation)

        image.startAnimation(topAnim)
        logo.startAnimation(bottomAnim)
        slogan.startAnimation(bottomAnim)

        Handler(Looper.getMainLooper()).postDelayed({
            if (prefs.getString(resources.getString(R.string.EMAIL_KEY)) !=
                resources.getString(R.string.STRING_DEFAULT_VALUE)
            ) {
                findNavController().navigate(R.id.toMenuFromWelcome)
            } else {
                findNavController().navigate(R.id.toLogin)
            }
        }, SPLASH_SCREEN)
    }

    companion object {
        private const val SPLASH_SCREEN = 4000L
    }
}