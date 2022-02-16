package com.example.exchangethis.presentation.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.exchangethis.R
import com.example.exchangethis.databinding.SignUpItemLayoutBinding
import com.example.exchangethis.domain.User
import com.example.exchangethis.presentation.activities.SecondActivity
import com.example.exchangethis.presentation.viewModels.UserViewModel
import com.example.exchangethis.utils.StringUtils
import com.example.exchangethis.utils.preference.SharedPreferenceManagerImpl
import org.koin.android.viewmodel.ext.android.viewModel

class SignUpFragment(context: Context) : Fragment(R.layout.sign_up_item_layout) {

    private val binding: SignUpItemLayoutBinding by viewBinding(SignUpItemLayoutBinding::bind)
    private var fullName: String = ""
    private var email: String = ""
    private var phone: String = ""
    private var password: String = ""
    private val newUser by lazy { User(fullName, email, phone, password) }
    private val viewModel: UserViewModel by viewModel()
    private val prefs = SharedPreferenceManagerImpl(context)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpButton.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {

        fullName = binding.fullName.text.toString().trim()
        email = binding.email.text.toString().trim()
        phone = binding.phoneNumber.text.toString().trim()
        password = binding.password.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.email.error = StringUtils.EMAIL_FORMAT_ERROR
        } else if (TextUtils.isEmpty(password)) {
            binding.password.error = StringUtils.PASSWORD_EMPTY_ERROR
        } else if (password.length < 6) {
            binding.password.error = StringUtils.PASSWORD_SHORT_ERROR
        } else if (TextUtils.isEmpty(fullName)) {
            binding.fullName.error = StringUtils.FULL_NAME_EMPTY_ERROR
        } else if (TextUtils.isEmpty(phone)) {
            binding.phoneNumber.error = StringUtils.PHONE_EMPTY_ERROR
        } else if (viewModel.user.value != null && newUser.email == viewModel.user.value?.get(0)?.email.orEmpty()) {
            binding.email.error = StringUtils.EMAIL_IS_BUSY_ERROR
        } else {
            viewModel.insertUser(newUser)
            prefs.saveEmail(EMAIL_KEY, newUser.email)
            prefs.savePassword(PASSWORD_KEY, newUser.password)
            Log.e("SIGNUP", newUser.toString())
            Log.e("SIGN UP PREFS", prefs.getEmail(EMAIL_KEY))
            val intent = Intent(requireContext(), SecondActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        private const val EMAIL_KEY = "USER EMAIL"
        private const val PASSWORD_KEY = "USER PASSWORD"
    }
}