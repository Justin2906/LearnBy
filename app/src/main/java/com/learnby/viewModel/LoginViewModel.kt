package com.learnby.viewModel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    private val _email =  MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _password =  MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _loginEnable =  MutableLiveData<Boolean>()
    val loginEnable : LiveData<Boolean> = _loginEnable

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        //habilitara o desahabilitara el boton sign in
        _loginEnable.value = validarEmail(email) && validarPassword(password)
    }

    private fun validarPassword(password: String): Boolean = password.length >8

    private fun validarEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
}