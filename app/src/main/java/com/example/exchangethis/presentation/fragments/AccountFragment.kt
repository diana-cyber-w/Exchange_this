package com.example.exchangethis.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.exchangethis.R
import com.example.exchangethis.databinding.AccountLayoutBinding
import com.example.exchangethis.domain.User
import com.example.exchangethis.presentation.viewModels.UserViewModel
import com.example.exchangethis.utils.preference.SharedPreferenceManagerImpl
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.sharedViewModel

class AccountFragment(context: Context) : Fragment(R.layout.account_layout) {

    private val binding: AccountLayoutBinding by viewBinding(AccountLayoutBinding::bind)
    private val viewModel: UserViewModel by sharedViewModel()
    private val prefs = SharedPreferenceManagerImpl(context)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserByEmailAndPassword(
            prefs.getEmail(EMAIL_KEY),
            prefs.getPassword(PASSWORD_KEY)
        )
        fillUserData()

        binding.refactorButton.setOnClickListener {
            val newUser by lazy {
                User(
                    binding.fullNameRefactor.text.toString().trim(),
                    binding.emailName.text.toString().trim(),
                    binding.phoneRefactor.text.toString().trim(),
                    binding.passwordRefactor.text.toString().trim()
                )
            }
            prefs.saveEmail(EMAIL_KEY, newUser.email)
            prefs.savePassword(PASSWORD_KEY, newUser.password)
            viewModel.insertUser(newUser)
            binding.accountFullName.text = viewModel.user.value?.get(0)?.fullName
        }
    }

    private fun fillUserData() {
        viewModel.user.observe(viewLifecycleOwner) { users ->
            if (users.isNotEmpty()) {
                viewLifecycleOwner.lifecycleScope.launch {
                    binding.accountFullName.text = users[0].fullName
                    binding.emailName.text = users[0].email
                    binding.fullNameRefactor.setText(users[0].fullName)
                    binding.phoneRefactor.setText(users[0].phone)
                    binding.passwordRefactor.setText(users[0].password)
                }
            } else {
                Toast.makeText(requireContext(), "Complete Profile", Toast.LENGTH_SHORT).show()
            }
        }
        Log.e("!!!account", viewModel.user.value?.get(0).toString())
    }

    companion object {
        private const val EMAIL_KEY = "USER EMAIL"
        private const val PASSWORD_KEY = "USER PASSWORD"
    }
}