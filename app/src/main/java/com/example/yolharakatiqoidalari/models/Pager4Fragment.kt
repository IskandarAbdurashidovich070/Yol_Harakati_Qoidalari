package com.example.yolharakatiqoidalari.models

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.yolharakatiqoidalari.R
import com.example.yolharakatiqoidalari.adapters.Click
import com.example.yolharakatiqoidalari.adapters.MyRvAdapter
import com.example.yolharakatiqoidalari.databinding.FragmentPager4Binding
import com.example.yolharakatiqoidalari.db.MyDbHelper


class Pager4Fragment : Fragment(), Click {
    private lateinit var binding: FragmentPager4Binding
    private lateinit var rvAdapter: MyRvAdapter
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var list: ArrayList<User>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPager4Binding.inflate(layoutInflater)


        myDbHelper = MyDbHelper(context)
        list = ArrayList()
        for (i in myDbHelper.getLabel()){
            if (i.type == "3"){
                list.add(i)
            }
        }
        rvAdapter = MyRvAdapter(list, this)

        binding.rv.adapter = rvAdapter


        return binding.root
        }

    override fun onLiked(user: User, position: Int, view: ImageView) {
        if (user.like == 0){
            user.like = 1
            view.setImageResource(R.drawable.liked)
            myDbHelper.updateLabel(user)
            findNavController().popBackStack()
            findNavController().navigate(R.id.homeFragment)
        }else{
            user.like = 0
            view.setImageResource(R.drawable.likeing)
            myDbHelper.updateLabel(user)
            findNavController().popBackStack()
            findNavController().navigate(R.id.homeFragment)
        }
    }


    override fun delete(user: User, position: Int) {
        myDbHelper.deleteLabel(user)
        list.remove(user)
        rvAdapter.notifyItemRemoved(position)
    }

    override fun edit(user: User, position: Int) {
        Data.user = user
        Data.IsAdd = false
        findNavController().navigate(R.id.addLabelFragment)
    }



    override fun OnClick(user: User, position: Int) {
        Data.user = user
        findNavController().navigate(R.id.aboutLabelFragment)
    }
}