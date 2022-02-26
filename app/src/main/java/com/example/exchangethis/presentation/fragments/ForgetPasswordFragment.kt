package com.example.exchangethis.presentation.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.exchangethis.R
import com.example.exchangethis.databinding.ForgetPasswordLayoutBinding
import com.example.exchangethis.presentation.viewModels.UserViewModel
import com.example.exchangethis.utils.preference.SharedPreferenceManagerImpl
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ForgetPasswordFragment : Fragment(R.layout.forget_password_layout) {

    private val binding: ForgetPasswordLayoutBinding by viewBinding(ForgetPasswordLayoutBinding::bind)
    private val viewModel: UserViewModel by sharedViewModel()
    private val prefs by lazy { SharedPreferenceManagerImpl(requireContext()) }
    private var email: String = ""
    private var password: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUsers()

        binding.loginButton.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        email = binding.email.text.toString().trim()
        password = binding.password.text.toString().trim()

        viewModel.getUserByEmail(email)

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.email.error = resources.getString(R.string.EMAIL_FORMAT_ERROR)
        } else if (TextUtils.isEmpty(password)) {
            binding.password.error = resources.getString(R.string.PASSWORD_EMPTY_ERROR)
        } else if (password.length < 6) {
            binding.password.error = resources.getString(R.string.PASSWORD_SHORT_ERROR)
        } else if (viewModel.userByEmail.value == null) {
            binding.email.error = resources.getString(R.string.EMAIL_IS_EMPTY_ERROR)
        } else {
            viewModel.updateUser(
                viewModel.user.value?.get(0)?.fullName.orEmpty(),
                viewModel.user.value?.get(0)?.phone.orEmpty(),
                password,
                email
            )
            prefs.saveString(resources.getString(R.string.EMAIL_KEY), email)
            prefs.saveString(resources.getString(R.string.PASSWORD_KEY), password)
            findNavController().navigate(R.id.toMenuFromPassword)
        }
    }
}