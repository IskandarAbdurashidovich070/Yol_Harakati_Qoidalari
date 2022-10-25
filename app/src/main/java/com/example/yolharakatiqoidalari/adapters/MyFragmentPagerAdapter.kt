package com.example.yolharakatiqoidalari.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.yolharakatiqoidalari.RecyclerFragment
import com.example.yolharakatiqoidalari.models.Pager2Fragment
import com.example.yolharakatiqoidalari.models.Pager3Fragment
import com.example.yolharakatiqoidalari.models.Pager4Fragment

class MyFragmentPagerAdapter(fa: Fragment): FragmentStateAdapter(fa) {
     override fun getItemCount(): Int = 4

     override fun createFragment(position: Int): Fragment {
         return when(position){
             0-> RecyclerFragment()
             1-> Pager2Fragment()
             2-> Pager3Fragment()
             else-> Pager4Fragment()
         }
     }


 }