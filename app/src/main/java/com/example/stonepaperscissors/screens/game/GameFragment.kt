package com.example.stonepaperscissors.screens.game

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.stonepaperscissors.R
import com.example.stonepaperscissors.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game, container, false
        )

        binding.lifecycleOwner = this
        val factory = GameViewModelFactory()
        val gameViewModel = ViewModelProvider(this, factory)[GameViewModel::class.java]
        binding.gameViewModel = gameViewModel
        binding.gameFragment = this

        gameViewModel.navigateGameToOther.observe(viewLifecycleOwner, Observer {
            if (it) {
                navigateToOther()
                gameViewModel.doneOtherNavigating()
            }
        })
        gameViewModel.navigateGameToComputer.observe(viewLifecycleOwner, Observer {
            if (it) {
                navigateToComputer()
                gameViewModel.doneComputerNavigating()
            }
        })

        return binding.root
    }

    private fun navigateToOther() {
        findNavController().navigate(R.id.action_gameFragment_to_otherPlayerFragment)
    }

    private fun navigateToComputer() {
        findNavController().navigate(R.id.action_gameFragment_to_computerFragment)
    }
    fun showInstruction() {
        val nameDialog = Dialog(requireContext())
        nameDialog.setContentView(R.layout.instruction_dialog)
        nameDialog.show()
    }
}