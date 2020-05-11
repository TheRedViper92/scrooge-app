package com.spartancode.prm_projekt_01

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.spartancode.prm_projekt_01.db.Borrower
import com.spartancode.prm_projekt_01.db.Database
import kotlinx.android.synthetic.main.add_borrower_activity.*
import kotlin.concurrent.thread

class AddBorrower : AppCompatActivity() {

    val db by lazy {
        Database.getInstance(applicationContext).database
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_borrower_activity)

    }

    fun addBorrower(view: View) {
        val borrower = Borrower(
            0,
            firstNameValue.text.toString(),
            lastNameValue.text.toString(),
            debtValue.text.toString().toDouble()
        )
        thread {
            db.borrowers().insert(borrower)
        }

        this.finish()
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        db.close()
//    }
}

