package com.example.exchangethis.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.exchangethis.R
import com.example.exchangethis.databinding.SecondActivityLayoutBinding
import com.example.exchangethis.presentation.fragments.AccountFragment
import com.example.exchangethis.presentation.fragments.FavouriteBooksFragment
import com.example.exchangethis.presentation.fragments.LibraryFragment
import com.example.exchangethis.presentation.fragments.SearchFragment

class SecondActivity : AppCompatActivity(R.layout.second_activity_layout) {

    private val binding: SecondActivityLayoutBinding by viewBinding(SecondActivityLayoutBinding::bind)

    override fun onStart() {
        super.onStart()
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.search -> replaceFragment(SearchFragment())
                R.id.library -> replaceFragment(LibraryFragment())
                R.id.favourite -> replaceFragment(FavouriteBooksFragment())
                R.id.account -> replaceFragment(AccountFragment(this))
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }
}