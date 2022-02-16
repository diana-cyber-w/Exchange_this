package com.example.exchangethis.presentation.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.exchangethis.R
import com.example.exchangethis.databinding.LoginItemLayoutBinding
import com.example.exchangethis.presentation.activities.MainActivity
import com.example.exchangethis.presentation.activities.SecondActivity
import com.example.exchangethis.presentation.viewModels.UserViewModel
import com.example.exchangethis.utils.StringUtils
import com.example.exchangethis.utils.preference.SharedPreferenceManagerImpl
import org.koin.android.viewmodel.ext.android.sharedViewModel

class LoginFragment(context: Context) : Fragment(R.layout.login_item_layout) {

    private val binding: LoginItemLayoutBinding by viewBinding(LoginItemLayoutBinding::bind)
    private var loginEmail: String = ""
    private var loginPassword: String = ""
    private val viewModel: UserViewModel by sharedViewModel()
    private val prefs = SharedPreferenceManagerImpl(context)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUsers()

        binding.loginButton.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        loginEmail = binding.email.text.toString().trim()
        loginPassword = binding.password.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(loginEmail).matches()) {
            binding.email.error = StringUtils.EMAIL_FORMAT_ERROR
        } else if (TextUtils.isEmpty(loginPassword)) {
            binding.password.error = StringUtils.PASSWORD_EMPTY_ERROR
        } else {
            prefs.saveEmail(EMAIL_KEY, loginEmail)
            prefs.savePassword(PASSWORD_KEY, loginPassword)
            Log.e("SAVE LOGIN PREFS", prefs.getEmail(EMAIL_KEY))
            checkUser()
        }
    }

    private fun checkUser() {
        var loginUser: String? = null

        if (viewModel.users.value != null) {
            viewModel.users.value!!.forEach { user ->
                if (user.email == loginEmail && user.password == loginPassword) {
                    loginUser = "Not"
                }
            }
            if (loginUser != null) {
                Log.i("LoginUser", viewModel.user.value.toString())
                val intent = Intent(requireContext(), SecondActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), StringUtils.NOT_SUCCESS_LOGIN, Toast.LENGTH_LONG)
                    .show()
            }
        } else {
            Toast.makeText(requireContext(), StringUtils.NOT_SUCCESS_LOGIN, Toast.LENGTH_LONG)
                .show()
        }

//        viewModel.if (viewModel.user.value == null
//            && viewModel.user.value?.get(0)?.email != loginEmail
//            && viewModel.user.value?.get(0)?.password != loginPassword
//        ) {
//            Toast.makeText(requireContext(), StringUtils.NOT_SUCCESS_LOGIN, Toast.LENGTH_LONG)
//                .show()
//        } else {
//            Log.i("LoginUser", viewModel.user.value.toString())
//            val intent = Intent(requireContext(), SecondActivity::class.java)
//            startActivity(intent)
//        }
    }

    companion object {
        private const val EMAIL_KEY = "USER EMAIL"
        private const val PASSWORD_KEY = "USER PASSWORD"
    }
}