package com.spartancode.prm_projekt_01

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spartancode.prm_projekt_01.db.Database
import kotlin.concurrent.thread

class BorrowersList : AppCompatActivity() {
    val db by lazy { Database.getInstance(applicationContext).database }
    val borrowers by lazy {
        db.borrowers().getAll()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.borrower_list_activity)

        thread {
            val rvBorrower = findViewById<View>(R.id.borrowers_list) as RecyclerView
            val adapter = BorrowerAdapter(borrowers)
            runOnUiThread {
                rvBorrower.adapter = adapter
                rvBorrower.layoutManager = LinearLayoutManager(this)
            }
        }
    }

    fun openNewBorrowerActivity(view: View){
        val addBorrowerIntent = Intent(this, AddBorrower::class.java)
        startActivity(addBorrowerIntent)
    }
}