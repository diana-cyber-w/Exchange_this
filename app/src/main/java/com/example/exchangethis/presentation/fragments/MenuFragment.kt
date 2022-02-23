package com.example.exchangethis.presentation.fragments

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.exchangethis.R
import com.example.exchangethis.databinding.MenuLayoutBinding

class MenuFragment : Fragment(R.layout.menu_layout) {

    private val binding: MenuLayoutBinding by viewBinding(MenuLayoutBinding::bind)

    override fun onStart() {
        super.onStart()
        replaceFragment(MyBookFragment())
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.myBooks -> replaceFragment(MyBookFragment())
                R.id.library -> replaceFragment(LibraryFragment())
                R.id.favourite -> replaceFragment(FavouriteBooksFragment())
                R.id.account -> replaceFragment(AccountFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }

}