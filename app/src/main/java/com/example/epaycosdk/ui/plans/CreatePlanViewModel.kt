package com.example.epaycosdk.ui.plans

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreatePlanViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Cargando..."
    }
    val text: LiveData<String> = _text
}