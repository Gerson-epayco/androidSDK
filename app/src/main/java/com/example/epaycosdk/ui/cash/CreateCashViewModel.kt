package com.example.epaycosdk.ui.cash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateCashViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Cargando"
    }
    val text: LiveData<String> = _text
}