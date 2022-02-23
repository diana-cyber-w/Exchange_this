package com.example.exchangethis.presentation.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.exchangethis.R
import com.example.exchangethis.databinding.AccountLayoutBinding
import com.example.exchangethis.domain.models.User
import com.example.exchangethis.presentation.viewModels.MyBookViewModel
import com.example.exchangethis.presentation.viewModels.UserViewModel
import com.example.exchangethis.utils.MyUtils
import com.example.exchangethis.utils.preference.SharedPreferenceManagerImpl
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.sharedViewModel

class AccountFragment : Fragment(R.layout.account_layout) {

    private val binding: AccountLayoutBinding by viewBinding(AccountLayoutBinding::bind)
    private val viewModel: UserViewModel by sharedViewModel()
    private val bookViewModel: MyBookViewModel by sharedViewModel()
    private val prefs by lazy { SharedPreferenceManagerImpl(requireContext()) }
    private var bookTotalRating: Double = 0.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserByEmailAndPassword(
            prefs.getString(resources.getString(R.string.EMAIL_KEY)),
            prefs.getString(resources.getString(R.string.PASSWORD_KEY))
        )
        setAverageRating()
        fillUserData()

        binding.refactorButton.setOnClickListener {
            checkUser()
        }

        binding.logout.setOnClickListener {
            findNavController().navigate(R.id.toRegistration)
            prefs.saveString(
                resources.getString(R.string.EMAIL_KEY),
                resources.getString(R.string.STRING_DEFAULT_VALUE)
            )
        }
    }

    private fun fillUserData() {
        viewModel.user.observe(viewLifecycleOwner) { users ->
            viewLifecycleOwner.lifecycleScope.launch {
                binding.accountFullName.text = users[0].fullName
                binding.emailName.text = users[0].email
                binding.fullNameRefactor.setText(users[0].fullName)
                binding.phoneRefactor.setText(users[0].phone)
                binding.passwordRefactor.setText(users[0].password)
            }
        }
        viewModel.setBookCounter(prefs.getInt(resources.getString(R.string.COUNTER_KEY)))
        viewModel.myBookCounter.observe(viewLifecycleOwner) { counter ->
            viewLifecycleOwner.lifecycleScope.launch {
                binding.userBookNumber.text = counter.toString()
            }
        }
        bookViewModel.countAverageBookRating(prefs.getFloat(resources.getString(R.string.RATING_KEY)))
        bookViewModel.averageBookRating.observe(viewLifecycleOwner) { rating ->
            viewLifecycleOwner.lifecycleScope.launch {
                if (rating.toDouble().isNaN()) {
                    binding.userRating.text = resources.getString(R.string.DOUBLE_DEFAULT)
                } else {
                    binding.userRating.text = rating.toDouble().toString()
                }
            }
        }
    }

    private fun checkUser() {
        val newUser by lazy {
            User(
                binding.fullNameRefactor.text.toString().trim(),
                binding.emailName.text.toString().trim(),
                binding.phoneRefactor.text.toString().trim(),
                binding.passwordRefactor.text.toString().trim()
            )
        }

        if (TextUtils.isEmpty(binding.fullNameRefactor.text.toString().trim())) {
            binding.fullNameRefactor.error = resources.getString(R.string.FULL_NAME_EMPTY_ERROR)
        } else if (TextUtils.isEmpty(binding.passwordRefactor.text.toString().trim())
        ) {
            binding.passwordRefactor.error = resources.getString(R.string.PASSWORD_EMPTY_ERROR)
        } else if (binding.passwordRefactor.text.toString().trim().length < 6) {
            binding.passwordRefactor.error = resources.getString(R.string.PASSWORD_SHORT_ERROR)
        } else if (!MyUtils.isMobileValid(binding.phoneRefactor.text.toString().trim())) {
            binding.phoneRefactor.error = resources.getString(R.string.PHONE_FORMAT_ERROR)
        } else {
            prefs.saveString(resources.getString(R.string.EMAIL_KEY), newUser.email)
            prefs.saveString(resources.getString(R.string.PASSWORD_KEY), newUser.password)
            viewModel.updateUser(newUser.fullName, newUser.phone, newUser.password, newUser.email)
            binding.accountFullName.text = viewModel.user.value?.get(0)?.fullName
        }
    }

    private fun setAverageRating() {
        bookViewModel.myBook.observe(viewLifecycleOwner) { books ->
            viewLifecycleOwner.lifecycleScope.launch {
                books?.forEach { myBook ->
                    bookTotalRating += myBook.rating
                }
            }
            prefs.saveFloat(
                resources.getString(R.string.RATING_KEY),
                (bookTotalRating / prefs.getInt(resources.getString(R.string.COUNTER_KEY))).toFloat()
            )
            bookViewModel.countAverageBookRating(prefs.getFloat(resources.getString(R.string.RATING_KEY)))
        }
    }
}