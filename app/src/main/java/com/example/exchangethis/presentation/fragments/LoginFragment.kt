package com.example.exchangethis.presentation.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.exchangethis.R
import com.example.exchangethis.databinding.LoginItemLayoutBinding
import com.example.exchangethis.presentation.viewModels.UserViewModel
import com.example.exchangethis.utils.preference.SharedPreferenceManagerImpl
import org.koin.android.viewmodel.ext.android.sharedViewModel

class LoginFragment : Fragment(R.layout.login_item_layout) {

    private val binding: LoginItemLayoutBinding by viewBinding(LoginItemLayoutBinding::bind)
    private var loginEmail: String = ""
    private var loginPassword: String = ""
    private val viewModel: UserViewModel by sharedViewModel()
    private val prefs by lazy { SharedPreferenceManagerImpl(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUsers()

        binding.loginButton.setOnClickListener {
            validateData()
        }

        binding.forgetPassword.setOnClickListener {
            findNavController().navigate(R.id.toForgetPassword)
        }
    }

    private fun validateData() {
        loginEmail = binding.email.text.toString().trim()
        loginPassword = binding.password.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(loginEmail).matches()) {
            binding.email.error = context?.getString(R.string.EMAIL_FORMAT_ERROR)
        } else if (TextUtils.isEmpty(loginPassword)) {
            binding.password.error = context?.getString(R.string.PASSWORD_EMPTY_ERROR)
        } else {
            prefs.saveString(resources.getString(R.string.EMAIL_KEY), loginEmail)
            prefs.saveString(resources.getString(R.string.PASSWORD_KEY), loginPassword)

            checkUser()
        }
    }

    private fun checkUser() {
        var loginUser: String? = null

        if (viewModel.users.value != null) {
            viewModel.users.value!!.forEach { user ->
                if (user.email == loginEmail && user.password == loginPassword) {
                    loginUser = resources.getString(R.string.STRING_DEFAULT_VALUE)
                }
            }
            if (loginUser != null) {
                findNavController().navigate(R.id.toMenu)
            } else {
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.NOT_SUCCESS_LOGIN),
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        } else {
            Toast.makeText(
                requireContext(),
                resources.getString(R.string.NOT_SUCCESS_LOGIN),
                Toast.LENGTH_LONG
            )
                .show()
        }
    }
}