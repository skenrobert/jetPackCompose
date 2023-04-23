package com.example.jetcompose.ui.login.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class LoginViewModel : ViewModel() {

    private val _email = MutableLiveData<String>() // cache input that view loginScreen, is private avoid modified for error
    val email: LiveData<String> = _email // this variable allow share element behind, this element modified for me, avoid error

    private val _password = MutableLiveData<String>() // private password no modified, avoid error
    val password: LiveData<String> = _password // work on this variable

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable // active button login

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading // inactive button login after click

    fun onLoginChanged(email: String, password: String) {
        _email.value = email  //change value after login
        _password.value = password
        _loginEnable.value = isValidEmail(email) && isValidPassword(password)
    }

    //this function only compare this password is > 6 character, I need refactor, for better
    private fun isValidPassword(password: String): Boolean = password.length > 6

    //this function validate this is a true email, is propre kotlin
    private fun isValidEmail(email: String): Boolean  = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    suspend fun onLoginSelected() { // suspend is a coroutine
        _isLoading.value = true
        delay(4000)
        _isLoading.value = false
    }

}