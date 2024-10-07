package com.example.stonepaperscissors.screens.finish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FinishViewModel(val winner:String): ViewModel() {

    fun startNavigating(){
        _navigateFinishToGame.value=true
    }
    fun doneNavigating() {
        _navigateFinishToGame.value=false
    }

    private val _navigateFinishToGame = MutableLiveData<Boolean>()
    val navigateFinishToGame: LiveData<Boolean>
        get() = _navigateFinishToGame

}