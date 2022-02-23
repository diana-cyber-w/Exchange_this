package com.example.exchangethis.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangethis.domain.interactors.UserInteractor
import com.example.exchangethis.domain.models.User
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserViewModel(
    private val userInteractor: UserInteractor
) : ViewModel() {

    val users: LiveData<List<User>> get() = _users
    private val _users = MutableLiveData<List<User>>()

    val user: LiveData<List<User>> get() = _user
    private val _user = MutableLiveData<List<User>>()

    val userByEmail: LiveData<List<User>> get() = _userByEmail
    private val _userByEmail = MutableLiveData<List<User>>()

    val myBookCounter: LiveData<Int> get() = _myBookCounter
    private val _myBookCounter = MutableLiveData<Int>()

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

    fun getUserByEmail(email: String) {
        viewModelScope.launch {
            userInteractor.getUserByEmail(email).collect { users ->
                _userByEmail.value = users
            }
        }
    }

    fun insertUser(user: User) {
        viewModelScope.launch {
            userInteractor.insertUser(user)
        }
    }

    fun updateUser(fullName: String, phone: String, password: String, email: String) {
        viewModelScope.launch {
            userInteractor.updateUser(fullName, phone, password, email)
        }
    }

    fun setBookCounter(counter: Int) {
        viewModelScope.launch {
            _myBookCounter.value = counter
        }
    }

}