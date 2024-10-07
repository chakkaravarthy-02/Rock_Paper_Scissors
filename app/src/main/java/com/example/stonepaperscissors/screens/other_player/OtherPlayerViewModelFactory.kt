package com.example.stonepaperscissors.screens.other_player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class OtherPlayerViewModelFactory: ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OtherPlayerViewModel::class.java)) {
            return OtherPlayerViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}