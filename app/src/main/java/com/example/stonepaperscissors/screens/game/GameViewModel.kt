package com.example.stonepaperscissors.screens.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    private val _navigateGameToOther = MutableLiveData<Boolean>()
    val navigateGameToOther: LiveData<Boolean>
        get() = _navigateGameToOther

    private val _navigateGameToComputer = MutableLiveData<Boolean>()
    val navigateGameToComputer: LiveData<Boolean>
        get() = _navigateGameToComputer

    fun doneComputerNavigating(){
        _navigateGameToComputer.value = false
    }
    fun doneOtherNavigating(){
        _navigateGameToOther.value = false
    }

    fun startNavigatingToOther(){
        _navigateGameToOther.value = true
    }

    fun startNavigatingToComputer(){
        _navigateGameToComputer.value = true
    }
}