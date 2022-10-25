package com.example.yolharakatiqoidalari

import android.R
import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.yolharakatiqoidalari.adapters.Click
import com.example.yolharakatiqoidalari.adapters.MyRvAdapter
import com.example.yolharakatiqoidalari.databinding.FragmentAddLabelBinding
import com.example.yolharakatiqoidalari.databinding.ItemRvBinding
import com.example.yolharakatiqoidalari.db.MyDbHelper
import com.example.yolharakatiqoidalari.models.Data
import com.example.yolharakatiqoidalari.models.User
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date

class AddLabelFragment : Fragment() {
    private lateinit var binding: FragmentAddLabelBinding
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var listSpinner: ArrayList<String>
    private lateinit var list: ArrayList<User>
    var path:String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddLabelBinding.inflate(layoutInflater)

        listSpinner = ArrayList()

        listSpinner.add("Ogohlantiruvchi")
        listSpinner.add("Imtiyozli")
        listSpinner.add("Taqiqlovchi")
        listSpinner.add("Buyuruvchi")

        myDbHelper = MyDbHelper(context)

        list = ArrayList()

        binding.setImage.setOnClickListener {
            setImage.launch("image/*")
        }

        binding.btmBack.setOnClickListener {
            findNavController().popBackStack()
        }
        when(Data.IsAdd){
            true->{
        binding.spinnerType.adapter = ArrayAdapter<String>(binding.root.context, R.layout.simple_list_item_1, listSpinner)
        binding.btnSave.setOnClickListener {

            val user = User(path, binding.edtName.text.toString(), binding.edtAbout.text.toString(), binding.spinnerType.selectedItemPosition.toString(), Data.user.like)
            myDbHelper.addLabel(user)
            list.add(user)
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()

            findNavController().navigate(com.example.yolharakatiqoidalari.R.id.homeFragment)
        }
            }
            false->{
                binding.setImage.setImageURI(Uri.parse(Data.user.image))
                binding.edtName.setText(Data.user.name)
                binding.edtAbout.setText(Data.user.about)
                path = Data.user.image
                binding.spinnerType.adapter = ArrayAdapter<String>(binding.root.context, R.layout.simple_list_item_1, listSpinner)
                binding.spinnerType.setSelection(Data.user.type.toInt())
                binding.btnSave.setOnClickListener {

                    Data.user.image = path
                    Data.user.name = binding.edtName.text.toString()
                    Data.user.about = binding.edtAbout.text.toString()
                    Data.user.type = binding.spinnerType.selectedItemPosition.toString()

                    myDbHelper.updateLabel(Data.user)
                    findNavController().navigate(com.example.yolharakatiqoidalari.R.id.homeFragment)

                }
            }
        }


        return binding.root
    }


    private var setImage =
        registerForActivityResult(ActivityResultContracts.GetContent()){
            it ?: return@registerForActivityResult
            binding.setImage.setImageURI(it)
            val inputStream = requireActivity().contentResolver?.openInputStream(it)
            val title = SimpleDateFormat("dd.MM.yyyy_hh.mm.ss").format(Date())
            val file = File(requireActivity().filesDir, "${title}.jpg")
            val outputStream = FileOutputStream(file)
            inputStream?.copyTo(outputStream)
            inputStream?.close()
            outputStream.close()
            path = file.absolutePath
        }



}