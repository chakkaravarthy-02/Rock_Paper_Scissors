package com.example.stonepaperscissors.screens.finish

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.stonepaperscissors.R
import com.example.stonepaperscissors.databinding.FragmentFinishBinding

class FinishFragment : Fragment() {

    private lateinit var binding: FragmentFinishBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater,R.layout.fragment_finish, container, false
        )

        val args = FinishFragmentArgs.fromBundle(requireArguments())
        val factory = FinishViewModelFactory(args.winnerName)
        val finishViewModel = ViewModelProvider(this,factory)[FinishViewModel::class.java]

        binding.lifecycleOwner = this
        binding.finishViewModel = finishViewModel

        finishViewModel.navigateFinishToGame.observe(viewLifecycleOwner, Observer { event ->
            if(event) {
                navigate()
                finishViewModel.doneNavigating()
            }
        })

        return binding.root
    }
    private fun navigate(){
        binding.root.findNavController().navigate(R.id.action_finishFragment_to_gameFragment)
    }
}