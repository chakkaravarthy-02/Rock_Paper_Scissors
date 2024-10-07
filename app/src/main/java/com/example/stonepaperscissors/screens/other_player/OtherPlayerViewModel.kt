package com.example.stonepaperscissors.screens.other_player

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OtherPlayerViewModel : ViewModel() {

    private lateinit var setTime: CountDownTimer

    private var player1Name = "Player 1"
    private var player2Name = "Player 2"

    private var player1Ready = false
    private var player2Ready = false
    private var allowPlaying = true

    private lateinit var selectionP1: String
    private lateinit var selectionP2: String

    private val _animationCalled = MutableLiveData<Boolean>()
    val animationCalled: LiveData<Boolean>
        get() = _animationCalled

    private val _startAnimation = MutableLiveData<Boolean>()
    val startAnimation: LiveData<Boolean>
        get() = _startAnimation

    private val _stopAnimation = MutableLiveData<Boolean>()
    val stopAnimation: LiveData<Boolean>
        get() = _stopAnimation

    private val _player = MutableLiveData<String>()
    val player: LiveData<String>
        get() = _player

    private val _p1Score = MutableLiveData<Int>()
    val p1Score: LiveData<Int>
        get() = _p1Score

    private val _p2Score = MutableLiveData<Int>()
    val p2Score: LiveData<Int>
        get() = _p2Score

    private val _winnerString = MutableLiveData<String>()
    val winnerString: LiveData<String>
        get() = _winnerString

    private val _setSelectedImage = MutableLiveData<String>()
    val setSelectedImage: LiveData<String>
        get() = _setSelectedImage

    private val _setSelectedImage2 = MutableLiveData<String>()
    val setSelectedImage2: LiveData<String>
        get() = _setSelectedImage2

    private val _player1Played = MutableLiveData<Boolean>()
    val player1Played: LiveData<Boolean>
        get() = _player1Played

    private val _player2Played = MutableLiveData<Boolean>()
    val player2Played: LiveData<Boolean>
        get() = _player2Played

    init {
        _p1Score.value = 0
        _p2Score.value = 0
    }

    fun setName(player1: String,player2: String){
        player1Name = player1
        player2Name = player2
    }

    fun onPlayer1Played() {
        _player1Played.value = false
    }

    fun onPlayer2Played() {
        _player2Played.value = false
    }

    fun onPlayPlayer1(selection: String) {
        if (allowPlaying) {
            _player1Played.value = true
            selectionP1 = selection
            player1Ready = true
            if (player2Ready) {
                allowPlaying = false
                playAnimation()
            }
        }
    }

    fun onPlayPlayer2(selection: String) {
        if (allowPlaying) {
            _player2Played.value = true
            selectionP2 = selection
            player2Ready = true
            if (player1Ready) {
                allowPlaying = false
                playAnimation()
            }
        }
    }

    private fun playAnimation() {
        _animationCalled.value = true
    }

    fun doneAnimation() {
        _animationCalled.value = false
    }

    fun countDown() {
        setTime = object : CountDownTimer(3000, 1000) {
            override fun onTick(p0: Long) {
                _startAnimation.value = true
            }

            override fun onFinish() {
                _stopAnimation.value = false
                allowPlaying = true
                player1Ready = false
                player2Ready = false
                setSelectedIcon(selectionP1, selectionP2)
                setScore()
                endGame()
            }
        }.start()
    }

    private fun setSelectedIcon(selectionP1: String, selectionP2: String) {
        _setSelectedImage.value = selectionP1
        _setSelectedImage2.value = selectionP2
    }

    private fun getResult(): String {
        return if (selectionP1 == selectionP2) "Tie"
        else if (selectionP1 == "rock" && selectionP2 == "scissor" ||
            selectionP1 == "paper" && selectionP2 == "rock" ||
            selectionP1 == "scissor" && selectionP2 == "paper"
        ) "P1"
        else "P2"
    }

    private fun setScore() {
        _winnerString.value = getResult()
    }
    fun p1Increased() {
        _p1Score.value = (_p1Score.value ?: 0) + 1
    }

    fun p2Increased() {
        _p2Score.value = (_p2Score.value ?: 0) + 1
    }

    private fun endGame() {
        if (_p1Score.value == 3 || _p2Score.value == 3) {
            _player.value = if (_p1Score.value == 3)
                player1Name
            else
                player2Name
        }
    }
}