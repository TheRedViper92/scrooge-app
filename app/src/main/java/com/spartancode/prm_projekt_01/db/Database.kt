package com.spartancode.prm_projekt_01.db

import android.content.Context
import androidx.room.Room

class Database private constructor(val context: Context) {
    companion object {
        private var database: Database? = null
        fun getInstance(context: Context): Database {
            return database ?: Database(context).also { database = it }
        }
    }

    val database by lazy {
        Room.databaseBuilder(context, BorrowerDb::class.java, "borrowers.db")
            .allowMainThreadQueries()
            .build()
//        Room.databaseBuilder(context, BorrowerDb::class.java, "borrowers.db").build()
    }
}