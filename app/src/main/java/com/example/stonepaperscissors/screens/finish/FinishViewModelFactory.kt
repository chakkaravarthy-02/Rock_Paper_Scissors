package com.example.stonepaperscissors.screens.finish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FinishViewModelFactory(private val winner: String):ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass : Class<T>): T{
        if(modelClass.isAssignableFrom(FinishViewModel::class.java)){
            return FinishViewModel(winner) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}