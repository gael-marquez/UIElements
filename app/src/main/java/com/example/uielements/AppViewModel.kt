package com.example.uielements

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppViewModel : ViewModel() {

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> get() = _userName

    private val _preferencia = MutableLiveData<String>()
    val preferencia: LiveData<String> get() = _preferencia

    private val _modoAvanzado = MutableLiveData<Boolean>(false)
    val modoAvanzado: LiveData<Boolean> get() = _modoAvanzado

    private val _userEmail = MutableLiveData<String>()
    val userEmail: LiveData<String> = _userEmail

    fun setUserEmail(email: String) {
        _userEmail.value = email
    }
    fun setUserName(name: String) { _userName.value = name }
    fun setPreferencia(pref: String) { _preferencia.value = pref }
    fun setModoAvanzado(on: Boolean) { _modoAvanzado.value = on }
}