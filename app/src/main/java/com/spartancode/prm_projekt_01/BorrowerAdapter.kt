package com.spartancode.prm_projekt_01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spartancode.prm_projekt_01.db.Borrower

class BorrowerAdapter(private val borrowers: List<Borrower>) :
    RecyclerView.Adapter<BorrowerAdapter.MyViewHolder>() {

    inner class MyViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val borrowerNameTextView: TextView = listItemView.findViewById(R.id.borrower_name)
        val debtTextView: TextView = listItemView.findViewById(R.id.debt)
        val idTextView: TextView = listItemView.findViewById(R.id.borrower_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val borrowerView = inflater.inflate(R.layout.borrower_list_item, parent, false)
        return MyViewHolder(borrowerView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val borrower: Borrower = borrowers[position]
        val firstNameTextView = holder.borrowerNameTextView;
        val debtTextView = holder.debtTextView;
        val idTextView = holder.idTextView

        firstNameTextView.text = borrower.firstName
        debtTextView.text = borrower.debt.toString()
        idTextView.text = borrower.id as String
    }

    override fun getItemCount() = borrowers.size
}
