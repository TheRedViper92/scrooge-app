package com.spartancode.prm_projekt_01.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Borrower(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var firstName: String,
    var lastName: String,
    var debt: Double
)