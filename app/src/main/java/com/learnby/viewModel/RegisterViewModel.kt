package com.learnby.viewModel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class RegisterViewModel {
    private val _emailRg =  MutableLiveData<String>()
    val emailRg : LiveData<String> = _emailRg

    private val _passwordRg =  MutableLiveData<String>()
    val passwordRg : LiveData<String> = _passwordRg

    private val _phone =  MutableLiveData<String>()
    val phoneRg : LiveData<String> = _phone

    private val _name =  MutableLiveData<String>()
    val nameRg : LiveData<String> = _name

    private val _registerEnable =  MutableLiveData<Boolean>()
    val registerEnable : LiveData<Boolean> = _registerEnable


    fun registerFields(emailRg: String, passwordRg: String,phone: String ,name: String) {
        _emailRg.value = emailRg
        _passwordRg.value = passwordRg
        _phone.value = phone
        _name.value = name
        _registerEnable.value = validarPass(passwordRg) && validarMail(emailRg) &&
                validarName(name) && validarPhone(phone)
    }

    private fun validarName(name: String): Boolean = name.length >1

    private fun validarPhone(phone: String): Boolean = phone.length >6

    private fun validarPass(password: String): Boolean = password.length >8

    private fun validarMail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
}