package com.example.yolharakatiqoidalari

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.yolharakatiqoidalari.databinding.FragmentInfoBinding


class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(layoutInflater)



        binding.home.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
        binding.liked.setOnClickListener {
            findNavController().navigate(R.id.likedFragment)
        }

        binding.info.setOnClickListener {
            findNavController().navigate(R.id.infoFragment)
        }


        return binding.root
    }

}