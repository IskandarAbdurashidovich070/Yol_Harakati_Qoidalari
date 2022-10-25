package com.example.yolharakatiqoidalari.db

import com.example.yolharakatiqoidalari.models.User

interface MyDbInterface {

    fun addLabel(user: User)

    fun getLabel(): List<User>

    fun updateLabel(user: User)

    fun deleteLabel(user: User)

}