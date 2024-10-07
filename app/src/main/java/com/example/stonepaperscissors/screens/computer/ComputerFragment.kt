package com.example.stonepaperscissors.screens.computer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.stonepaperscissors.R
import com.example.stonepaperscissors.databinding.FragmentComputerBinding


class ComputerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentComputerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_computer, container, false
        )
        return binding.root
    }
}