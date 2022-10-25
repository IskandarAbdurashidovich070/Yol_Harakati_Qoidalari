package com.example.yolharakatiqoidalari

import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.yolharakatiqoidalari.databinding.FragmentAboutLabelBinding

class AboutLabelFragment : Fragment() {
    private lateinit var binding: FragmentAboutLabelBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutLabelBinding.inflate(layoutInflater)



        binding.image.setImageURI(Uri.parse(com.example.yolharakatiqoidalari.models.Data.user.image))
        binding.nameTv.text = com.example.yolharakatiqoidalari.models.Data.user.image
        binding.textAbout.text = com.example.yolharakatiqoidalari.models.Data.user.image

        binding.btnAdd.setOnClickListener {
            findNavController().popBackStack()
        }


        return binding.root
    }
}