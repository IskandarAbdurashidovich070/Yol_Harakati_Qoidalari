package com.example.yolharakatiqoidalari.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.yolharakatiqoidalari.RecyclerFragment

class MyFragmentPagerAdapter(fa: Fragment): FragmentStateAdapter(fa) {
     override fun getItemCount(): Int = 4

     override fun createFragment(position: Int): Fragment {
         return RecyclerFragment()
     }


 }