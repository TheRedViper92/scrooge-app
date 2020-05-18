package com.spartancode.prm_projekt_01

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spartancode.prm_projekt_01.db.Borrower
import com.spartancode.prm_projekt_01.db.Database
import kotlinx.android.synthetic.main.borrower_list_activity.*

class BorrowersList : AppCompatActivity(), BorrowerAdapter.OnBorrowerClickListener {
    val db by lazy { Database.getInstance(applicationContext).database }
    private val borrowers by lazy {
        db.borrowers().getAll()
    }

    private val adapter by lazy { BorrowerAdapter(borrowers as ArrayList<Borrower>, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.borrower_list_activity)
        initializeData()
        setTotalDebts()
    }

    override fun onResume() {
        super.onResume()
        val borrowers = db.borrowers().getAll()
        adapter.updateBorrowersList(borrowers)
        setTotalDebts()
    }

    fun openNewBorrowerActivity(view: View) {
        val addBorrowerIntent = Intent(this, EditBorrower::class.java)
        startActivity(addBorrowerIntent)
    }

    private fun initializeData() {
        val rvBorrower = findViewById<View>(R.id.borrowers_list) as RecyclerView
        rvBorrower.adapter = adapter
        rvBorrower.layoutManager = LinearLayoutManager(this)
    }

    private fun setTotalDebts() {
        totalDebtValue.text = "${db.borrowers().getDebtsSum()} zł"
    }

    override fun onBorrowerClick(position: Int) {
        val editBorrowerIntent = Intent(this, EditBorrower::class.java)
        editBorrowerIntent.putExtra("BORROWER_ID", borrowers[position].id)
        startActivity(editBorrowerIntent)
    }
}