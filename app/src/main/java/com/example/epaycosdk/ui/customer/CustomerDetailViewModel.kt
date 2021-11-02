package com.example.epaycosdk.ui.customer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CustomerDetailViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Cargando...."
    }
    val text: LiveData<String> = _text
}