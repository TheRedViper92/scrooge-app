package com.spartancode.prm_projekt_01

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.spartancode.prm_projekt_01.db.Database

class BorrowersListItem : AppCompatActivity() {
    val db by lazy {
        Database.getInstance(applicationContext).database
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun editBorrower(view: View){

    }
}