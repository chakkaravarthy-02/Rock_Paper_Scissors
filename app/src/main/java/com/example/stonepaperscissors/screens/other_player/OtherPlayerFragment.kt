package com.example.stonepaperscissors.screens.other_player

import android.app.Dialog
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
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
import com.example.stonepaperscissors.databinding.FragmentOtherPlayerBinding

class OtherPlayerFragment : Fragment() {

    private lateinit var binding: FragmentOtherPlayerBinding
    private lateinit var animation1: AnimationDrawable
    private lateinit var animation2: AnimationDrawable
    private lateinit var otherViewModel: OtherPlayerViewModel

    private var player1Name = "Player 1"
    private var player2Name = "Player 2"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_other_player, container, false
        )

        val factory = OtherPlayerViewModelFactory()
        otherViewModel = ViewModelProvider(this,factory)[OtherPlayerViewModel::class.java]

        binding.lifecycleOwner = this

        binding.otherViewModel = otherViewModel

        getPlayerName()

        otherViewModel.player1Played.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.p1Image.setImageResource(R.drawable.check)
                binding.p1StatusText.text = "Ready"
                otherViewModel.onPlayer1Played()
            }
        })
        otherViewModel.player2Played.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.p2Image.setImageResource(R.drawable.check)
                binding.p2StatusText.text = "Ready"
                otherViewModel.onPlayer2Played()
            }
        })

        otherViewModel.setSelectedImage.observe(viewLifecycleOwner, Observer {
            when (it) {
                "rock" -> binding.p1Image.setImageResource(R.drawable.stone)
                "scissor" -> binding.p1Image.setImageResource(R.drawable.scissors)
                "paper" -> binding.p1Image.setImageResource(R.drawable.paper)
            }
        })

        otherViewModel.setSelectedImage2.observe(viewLifecycleOwner, Observer {
            when (it) {
                "rock" -> binding.p2Image.setImageResource(R.drawable.stone)
                "scissor" -> binding.p2Image.setImageResource(R.drawable.scissors)
                "paper" -> binding.p2Image.setImageResource(R.drawable.paper)
            }
        })

        otherViewModel.winnerString.observe(viewLifecycleOwner, Observer {
            when (it) {
                "Tie" -> {
                    binding.p1StatusText.text = "Tie"
                    binding.p2StatusText.text = "Tie"
                }

                "P1" -> {
                    binding.p1StatusText.text = "Win"
                    binding.p2StatusText.text = "Lose"
                    otherViewModel.p1Increased()
                }

                "P2" -> {
                    binding.p1StatusText.text = "Lose"
                    binding.p2StatusText.text = "Win"
                    otherViewModel.p2Increased()
                }
            }
        })

        otherViewModel.p1Score.observe(viewLifecycleOwner, Observer {
            when (it) {
                1 -> binding.p1Star1Image.setImageResource(R.drawable.yes_star)
                2 -> binding.p1Star2Image.setImageResource(R.drawable.yes_star)
                3 -> binding.p1Star3Image.setImageResource(R.drawable.yes_star)
            }
        })

        otherViewModel.p2Score.observe(viewLifecycleOwner, Observer {
            when (it) {
                1 -> binding.p2Star1Image.setImageResource(R.drawable.yes_star)
                2 -> binding.p2Star2Image.setImageResource(R.drawable.yes_star)
                3 -> binding.p2Star3Image.setImageResource(R.drawable.yes_star)
            }
        })

        otherViewModel.animationCalled.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.p1Image.setImageResource(0)
                binding.p2Image.setImageResource(0)

                binding.p1StatusText.text = ""
                binding.p2StatusText.text = ""


                binding.p1Image.setBackgroundResource(R.drawable.animation_rpc)
                animation1 = binding.p1Image.background as AnimationDrawable

                binding.p2Image.setBackgroundResource(R.drawable.animation_rpc)
                animation2 = binding.p2Image.background as AnimationDrawable
                otherViewModel.countDown()
                otherViewModel.doneAnimation()
            }
        })

        otherViewModel.startAnimation.observe(viewLifecycleOwner, Observer {
            animation1.start()
            animation2.start()
        })

        otherViewModel.stopAnimation.observe(viewLifecycleOwner, Observer {
            animation1.stop()
            animation2.stop()
            binding.p1Image.setBackgroundResource(0)
            binding.p2Image.setBackgroundResource(0)
        })

        otherViewModel.player.observe(viewLifecycleOwner, Observer {
            val navController = findNavController()
            navController.navigate(
                OtherPlayerFragmentDirections.actionOtherPlayerFragmentToFinishFragment(
                    it
                )
            )
        })

        return binding.root
    }

    private fun getPlayerName() {
        val nameDialog = Dialog(requireContext())
        nameDialog.setContentView(R.layout.player_name_dialog)
        nameDialog.findViewById<Button>(R.id.ok_button).setOnClickListener {
            val name1 = nameDialog.findViewById<EditText>(R.id.et_name1).text
            val name2 = nameDialog.findViewById<EditText>(R.id.et_name2).text

            if (name1.isNotEmpty() && name2.isNotEmpty()) {
                player1Name = name1.toString()
                player2Name = name2.toString()
                binding.player1Text.text = player1Name
                binding.player2Text.text = player2Name
                nameDialog.cancel()
                otherViewModel.setName(player1Name,player2Name)
            } else {
                Toast.makeText(requireContext(), "Enter both player name.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        nameDialog.show()
    }
}