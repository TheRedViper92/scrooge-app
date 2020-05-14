package com.spartancode.prm_projekt_01

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.spartancode.prm_projekt_01.db.Borrower
import com.spartancode.prm_projekt_01.db.Database
import kotlinx.android.synthetic.main.add_borrower_activity.*
import kotlin.concurrent.thread

class AddBorrower : AppCompatActivity() {
    private lateinit var borrower: Borrower
    val db by lazy {
        Database.getInstance(applicationContext).database
    }
    private val borrowerId: Int by lazy { intent.getIntExtra("BORROWER_ID", 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_borrower_activity)
        if (borrowerId > 0) {
            thread {
                setBorrowerData(db.borrowers().getById(borrowerId))
            }
        }
    }

    fun addBorrower(view: View) {
        borrower = Borrower(
            borrowerId,
            firstNameValue.text.toString(),
            lastNameValue.text.toString(),
            debtValue.text.toString().toDouble()
        )
        thread {
            when (borrowerId) {
                0 -> db.borrowers().insert(borrower)
                else -> {
                    db.borrowers().update(borrower)
                }
            }
        }

        this.finish()
    }

    private fun setBorrowerData(borrower: Borrower) {
        firstNameValue.setText(borrower.firstName)
        lastNameValue.setText(borrower.lastName)
        debtValue.setText(borrower.debt.toString())
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        db.close()
//    }
}

