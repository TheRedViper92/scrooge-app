package com.spartancode.prm_projekt_01

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spartancode.prm_projekt_01.db.Borrower

class BorrowerAdapter(
    private val borrowers: List<Borrower>,
    onBorrowerListener: OnBorrowerListener
) :
    RecyclerView.Adapter<BorrowerAdapter.MyViewHolder>() {

    private val onBorrowerListener: OnBorrowerListener = onBorrowerListener

    inner class MyViewHolder(listItemView: View, onBorrowerListener: OnBorrowerListener) :
        RecyclerView.ViewHolder(listItemView), View.OnClickListener {
        val borrowerNameTextView: TextView = listItemView.findViewById(R.id.borrower_name)
        val debtTextView: TextView = listItemView.findViewById(R.id.debt)
        val idTextView: TextView = listItemView.findViewById(R.id.borrower_id)
        var onBorrowerListener: OnBorrowerListener = onBorrowerListener

        init {
            listItemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onBorrowerListener.onBorrowerClick(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val borrowerView = inflater.inflate(R.layout.borrower_list_item, parent, false)
        return MyViewHolder(borrowerView, onBorrowerListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val borrower: Borrower = borrowers[position]
        val firstNameTextView = holder.borrowerNameTextView;
        val debtTextView = holder.debtTextView;
        val idTextView = holder.idTextView

        firstNameTextView.text = borrower.firstName
        debtTextView.text = borrower.debt.toString()
        idTextView.text = borrower.id.toString()
    }

    override fun getItemCount() = borrowers.size

    public interface OnBorrowerListener {
        fun onBorrowerClick(position: Int)
    }
}
