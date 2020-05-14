package com.spartancode.prm_projekt_01

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spartancode.prm_projekt_01.db.Database
import kotlinx.android.synthetic.main.borrower_list_activity.*
import kotlin.concurrent.thread

class BorrowersList : AppCompatActivity(), BorrowerAdapter.OnBorrowerListener {
    val db by lazy { Database.getInstance(applicationContext).database }
    val borrowers by lazy {
        db.borrowers().getAll()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.borrower_list_activity)
        fetchData()
    }

    override fun onResume() {
        super.onResume()
    }

    fun openNewBorrowerActivity(view: View) {
        val addBorrowerIntent = Intent(this, AddBorrower::class.java)
        startActivity(addBorrowerIntent)
    }

    private fun fetchData() {
        thread {
            val rvBorrower = findViewById<View>(R.id.borrowers_list) as RecyclerView
            val adapter = BorrowerAdapter(borrowers, this)
            runOnUiThread {
                rvBorrower.adapter = adapter
                rvBorrower.layoutManager = LinearLayoutManager(this)
            }
        }
    }

    override fun onBorrowerClick(position: Int) {
        val editBorrowerIntent = Intent(this, AddBorrower::class.java)
        editBorrowerIntent.putExtra("BORROWER_ID", borrowers[position].id)
        startActivity(editBorrowerIntent)
    }
}