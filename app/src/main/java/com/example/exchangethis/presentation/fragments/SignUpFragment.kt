package com.example.exchangethis.presentation.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.exchangethis.R
import com.example.exchangethis.databinding.SignUpItemLayoutBinding
import com.example.exchangethis.domain.models.User
import com.example.exchangethis.presentation.viewModels.UserViewModel
import com.example.exchangethis.utils.MyUtils
import com.example.exchangethis.utils.preference.SharedPreferenceManagerImpl
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SignUpFragment : Fragment(R.layout.sign_up_item_layout) {

    private val binding: SignUpItemLayoutBinding by viewBinding(SignUpItemLayoutBinding::bind)
    private var fullName: String = resources.getString(R.string.EMPTY)
    private var email: String = resources.getString(R.string.EMPTY)
    private var phone: String = resources.getString(R.string.EMPTY)
    private var password: String = resources.getString(R.string.EMPTY)
    private val newUser by lazy { User(fullName, email, phone, password) }
    private val viewModel: UserViewModel by sharedViewModel()
    private val prefs by lazy { SharedPreferenceManagerImpl(requireContext()) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUsers()

        binding.signUpButton.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        fullName = binding.fullName.text.toString().trim()
        email = binding.email.text.toString().trim()
        phone = binding.phoneNumber.text.toString().trim()
        password = binding.password.text.toString().trim()

        if (TextUtils.isEmpty(fullName)) {
            binding.fullName.error = resources.getString(R.string.FULL_NAME_EMPTY_ERROR)
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.email.error = resources.getString(R.string.EMAIL_FORMAT_ERROR)
        } else if (!MyUtils.isMobileValid(phone)) {
            binding.phoneNumber.error = resources.getString(R.string.PHONE_FORMAT_ERROR)
        } else if (TextUtils.isEmpty(password)) {
            binding.password.error = resources.getString(R.string.PASSWORD_EMPTY_ERROR)
        } else if (password.length < 6) {
            binding.password.error = resources.getString(R.string.PASSWORD_SHORT_ERROR)
        } else if (isUserAlreadySignUp()) {
            binding.email.error = resources.getString(R.string.EMAIL_IS_BUSY_ERROR)
        } else {
            findNavController().navigate(R.id.toMenu)
            viewModel.insertUser(newUser)
            prefs.saveString(resources.getString(R.string.EMAIL_KEY), newUser.email)
            prefs.saveString(resources.getString(R.string.PASSWORD_KEY), newUser.password)
        }
    }

    private fun isUserAlreadySignUp(): Boolean {
        var isSignUp = false

        if (viewModel.users.value != null) {
            viewModel.users.value!!.forEach { user ->
                if (user.email == email) {
                    isSignUp = true
                }
            }
        }
        return isSignUp
    }
}