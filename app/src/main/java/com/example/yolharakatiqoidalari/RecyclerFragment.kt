package com.example.yolharakatiqoidalari

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.yolharakatiqoidalari.adapters.Click
import com.example.yolharakatiqoidalari.adapters.MyRvAdapter
import com.example.yolharakatiqoidalari.databinding.FragmentRecyclerBinding
import com.example.yolharakatiqoidalari.db.MyDbHelper
import com.example.yolharakatiqoidalari.models.Data
import com.example.yolharakatiqoidalari.models.User

class RecyclerFragment : Fragment(), Click {
    private lateinit var binding: FragmentRecyclerBinding
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var rvAdapter: MyRvAdapter
    private lateinit var list: ArrayList<User>
    private lateinit var listSpinner: ArrayList<String>
    private lateinit var user: User
    var index: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerBinding.inflate(layoutInflater)


        user = User()
        listSpinner = ArrayList()

        listSpinner.add("Ogohlantiruvchi")
        listSpinner.add("Imtiyozli")
        listSpinner.add("Taqiqlovchi")
        listSpinner.add("Buyuruvchi")

        myDbHelper = MyDbHelper(context)
        list = ArrayList()
        list = myDbHelper.getLabel() as ArrayList<User>

        rvAdapter = MyRvAdapter(list, this)

        binding.rv.adapter = rvAdapter


        listSpinner.forEach {
            if (it == Data.user.type) {
                index = listSpinner.indexOf(it)
            }
        }


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
        var index = list.indexOf(user)
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
