package com.example.yolharakatiqoidalari

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.navigation.fragment.findNavController
import com.example.yolharakatiqoidalari.adapters.MyFragmentPagerAdapter
import com.example.yolharakatiqoidalari.databinding.FragmentHomeBinding
import com.example.yolharakatiqoidalari.databinding.ItemTabBinding
import com.example.yolharakatiqoidalari.models.Data
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var myFragmentPagerAdapter: MyFragmentPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.btnAdd.setOnClickListener {
            Data.IsAdd = true
            findNavController().navigate(R.id.addLabelFragment)
        }

        binding.home.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
        binding.liked.setOnClickListener {
            findNavController().navigate(R.id.likedFragment)
        }

        binding.info.setOnClickListener {
            findNavController().navigate(R.id.infoFragment)
        }

        binding.tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                customView?.findViewById<ImageView>(R.id.dot)?.visibility = View.VISIBLE
                customView?.findViewById<TextView>(R.id.text_tab1)?.visibility = View.VISIBLE

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                customView?.findViewById<ImageView>(R.id.dot)?.visibility = View.GONE
                customView?.findViewById<TextView>(R.id.text_tab)?.visibility = View.VISIBLE
                customView?.findViewById<TextView>(R.id.text_tab1)?.visibility = View.GONE


            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })


        myFragmentPagerAdapter = MyFragmentPagerAdapter(this)

        binding.pager.adapter = myFragmentPagerAdapter


        val list = ArrayList<String>()

        list.add("Ogohlantiruchi")
        list.add("Imtiyozli")
        list.add("Taqiqlovchi")
        list.add("Buyuruvchi")


        TabLayoutMediator(binding.tab, binding.pager){tab, position->
            val itemTabBinding = ItemTabBinding.inflate(layoutInflater)

            itemTabBinding.textTab.text = list[position]
            itemTabBinding.textTab1.text = list[position]

            itemTabBinding.textTab1.visibility = View.GONE

            itemTabBinding.dot.visibility = View.GONE

            tab.customView = itemTabBinding.root
        }.attach()
        return binding.root
    }
}