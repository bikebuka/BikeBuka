package com.bikebuka.bikebuka.di

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    override fun onCleared() {
        super.onCleared()
    }
}