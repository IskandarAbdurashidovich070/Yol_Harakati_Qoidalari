package com.example.yolharakatiqoidalari.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.yolharakatiqoidalari.R
import com.example.yolharakatiqoidalari.databinding.ItemRvBinding
import com.example.yolharakatiqoidalari.db.MyDbHelper
import com.example.yolharakatiqoidalari.models.User

class MyRvAdapter(var list: ArrayList<User>, var click: Click) : RecyclerView.Adapter<MyRvAdapter.Vh>() {

    inner class Vh(var rvItemBinding: ItemRvBinding): RecyclerView.ViewHolder(rvItemBinding.root){
        fun onBind(user: User, position: Int ){
            rvItemBinding.image.setImageURI(user.image.toUri())
            rvItemBinding.name.text = user.name
            if (user.like == 0){
                rvItemBinding.unliked.setImageResource(R.drawable.likeing)
            }else{
                rvItemBinding.unliked.setImageResource(R.drawable.liked)
            }

            rvItemBinding.unliked.setOnClickListener {
                click.onLiked(user, position,rvItemBinding.unliked)
            }

            rvItemBinding.delete.setOnClickListener {
                click.delete(user, position)
            }
            rvItemBinding.edit.setOnClickListener {
                click.edit(user, position)
            }

            rvItemBinding.card.setOnClickListener {
                click.OnClick(user, position)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context) , parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

}

interface Click{
    fun onLiked(user: User, position: Int,view: ImageView)
    fun delete(user: User, position: Int)
    fun edit(user: User, position: Int)
    fun OnClick(user: User, position: Int)
}