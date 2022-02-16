package com.example.exchangethis.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangethis.domain.User
import com.example.exchangethis.domain.UserInteractor
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserViewModel(
    private val userInteractor: UserInteractor
) : ViewModel() {

    val users: LiveData<List<User>> get() = _users
    private val _users = MutableLiveData<List<User>>()

    val user: LiveData<List<User>>
        get() = _user
    private val _user = MutableLiveData<List<User>>()

    fun getUsers() {
        viewModelScope.launch {
            userInteractor.getUsers().collect { users ->
                _users.value = users
            }
        }
    }

    fun getUserByEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            userInteractor.getUserByEmailAndPassword(email, password).collect { users ->
                _user.value = users
            }
        }
    }

    fun insertUser(user: User) {
        viewModelScope.launch {
            userInteractor.insertUser(user)
        }
    }
}