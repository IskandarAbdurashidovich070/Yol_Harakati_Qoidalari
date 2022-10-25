package com.example.yolharakatiqoidalari

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.yolharakatiqoidalari.adapters.Click
import com.example.yolharakatiqoidalari.adapters.MyRvAdapter
import com.example.yolharakatiqoidalari.databinding.FragmentLikedBinding
import com.example.yolharakatiqoidalari.db.MyDbHelper
import com.example.yolharakatiqoidalari.models.Data
import com.example.yolharakatiqoidalari.models.User

class LikedFragment : Fragment(), Click {
    private lateinit var binding: FragmentLikedBinding
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var list: ArrayList<User>
    private lateinit var myRvAdapter: MyRvAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLikedBinding.inflate(layoutInflater)



        myDbHelper = MyDbHelper(context)
        list = ArrayList()
        for (i in myDbHelper.getLabel()){
            if (i.like == 1) list.add(i)
        }
        myRvAdapter = MyRvAdapter(list, this)

        binding.rv.adapter = myRvAdapter

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

    override fun onLiked(user: User, position: Int,view: ImageView) {
        if (user.like == 0){
            user.like = 1
            view.setImageResource(R.drawable.likeing)
            myDbHelper.updateLabel(user)
            findNavController().popBackStack()
            findNavController().navigate(R.id.likedFragment)
        }else{
            user.like = 0
            view.setImageResource(R.drawable.liked)
            myDbHelper.updateLabel(user)
            findNavController().popBackStack()
            findNavController().navigate(R.id.likedFragment)
        }
    }


    override fun delete(user: User, position: Int) {
    }

    override fun edit(user: User, position: Int) {
    }



    override fun OnClick(user: User, position: Int) {

    }
}