package com.example.idledrink.ui.attack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AttackViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is attack Fragment"
    }
    val text: LiveData<String> = _text
}