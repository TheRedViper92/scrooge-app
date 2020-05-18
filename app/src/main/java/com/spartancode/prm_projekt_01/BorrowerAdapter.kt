package com.spartancode.prm_projekt_01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spartancode.prm_projekt_01.db.Borrower

class BorrowerAdapter(
    private var borrowers: ArrayList<Borrower>,
    onBorrowerClickListener: OnBorrowerClickListener
) :
    RecyclerView.Adapter<BorrowerAdapter.MyViewHolder>() {

    private val onBorrowerClickListener: OnBorrowerClickListener = onBorrowerClickListener

    inner class MyViewHolder(listItemView: View, onBorrowerClickListener: OnBorrowerClickListener) :
        RecyclerView.ViewHolder(listItemView), View.OnClickListener {
        val borrowerNameTextView: TextView = listItemView.findViewById(R.id.borrower_name)
        val debtTextView: TextView = listItemView.findViewById(R.id.debt)
        val idTextView: TextView = listItemView.findViewById(R.id.borrower_id)
        var onBorrowerClickListener: OnBorrowerClickListener = onBorrowerClickListener

        init {
            listItemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onBorrowerClickListener.onBorrowerClick(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val borrowerView = inflater.inflate(R.layout.borrower_list_item, parent, false)
        return MyViewHolder(borrowerView, onBorrowerClickListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val borrower: Borrower = borrowers[position]
        val firstNameTextView = holder.borrowerNameTextView;
        val debtTextView = holder.debtTextView;
        val idTextView = holder.idTextView

        firstNameTextView.text = "${borrower.firstName} ${borrower.lastName}"
        debtTextView.text = "${borrower.debt} zł"
        idTextView.text = borrower.id.toString()
    }

    override fun getItemCount() = borrowers.size

    fun updateBorrowersList(newBorrowers: List<Borrower>){
        borrowers.clear()
        borrowers.addAll(newBorrowers)
        this.notifyDataSetChanged()
    }

    interface OnBorrowerClickListener {
        fun onBorrowerClick(position: Int)
    }
}
