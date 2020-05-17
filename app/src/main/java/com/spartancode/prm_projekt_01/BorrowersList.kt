package com.spartancode.prm_projekt_01

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spartancode.prm_projekt_01.db.Borrower
import com.spartancode.prm_projekt_01.db.Database
import kotlin.concurrent.thread

class BorrowersList : AppCompatActivity(), BorrowerAdapter.OnBorrowerClickListener {
    val db by lazy { Database.getInstance(applicationContext).database }
    private val borrowers by lazy {
        db.borrowers().getAll()
    }

    private val adapter by lazy { BorrowerAdapter(borrowers as ArrayList<Borrower>, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.borrower_list_activity)
        fetchData()
    }

    override fun onResume() {
        super.onResume()
        val borrowers = db.borrowers().getAll()
        adapter.updateBorrowersList(borrowers)
    }

    fun openNewBorrowerActivity(view: View) {
        val addBorrowerIntent = Intent(this, AddBorrower::class.java)
        startActivity(addBorrowerIntent)
    }

    private fun fetchData() {
        val rvBorrower = findViewById<View>(R.id.borrowers_list) as RecyclerView
        rvBorrower.adapter = adapter
        rvBorrower.layoutManager = LinearLayoutManager(this)
    }

    override fun onBorrowerClick(position: Int) {
        val editBorrowerIntent = Intent(this, AddBorrower::class.java)
        editBorrowerIntent.putExtra("BORROWER_ID", borrowers[position].id)
        startActivity(editBorrowerIntent)
    }
}