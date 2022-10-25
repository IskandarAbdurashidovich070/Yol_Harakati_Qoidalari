package com.example.yolharakatiqoidalari.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.yolharakatiqoidalari.models.User
import com.google.android.material.tabs.TabLayout.Tab

class MyDbHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION),
    MyDbInterface {

    companion object {

        var DB_NAME = "label_app"

        var DB_VERSION = 1

        var TABLE = "label"

        var ID = "id"
        var IMAGE = "image"
        var NAME = "name"
        var ABOUT = "about"
        var TYPE = "type"
        var LIKE = "like"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val query =
            "create table $TABLE ($ID integer not null primary key autoincrement unique , $IMAGE text not null, $NAME text not null, $ABOUT text not null, $TYPE text not null, $LIKE integer not null)"


        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    override fun addLabel(user: User) {
        val database = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(IMAGE, user.image)
        contentValues.put(NAME, user.name)
        contentValues.put(ABOUT, user.about)
        contentValues.put(TYPE, user.type)
        contentValues.put(LIKE, user.like)
        database.insert(TABLE, null, contentValues)
        database.close()
    }

    override fun getLabel(): List<User> {
        val list = ArrayList<User>()
        val query = "select * from  $TABLE"
        val database = readableDatabase
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    User(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5)
                    )
                )
            } while (cursor.moveToNext())
        }


        return list
    }

    override fun updateLabel(user: User) {
        val database = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, user.id)
        contentValues.put(IMAGE, user.image)
        contentValues.put(NAME, user.name)
        contentValues.put(ABOUT, user.about)
        contentValues.put(TYPE, user.type)
        contentValues.put(LIKE, user.like)
        database.update(TABLE, contentValues, "$ID = ?", arrayOf(user.id.toString()))
        database.close()
    }

    override fun deleteLabel(user: User) {
        val database = writableDatabase
        database.delete(TABLE, "$ID = ?", arrayOf(user.id.toString()))
        database.close()
    }

}