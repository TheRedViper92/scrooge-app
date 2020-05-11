package com.spartancode.prm_projekt_01.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Borrower::class])
abstract class BorrowerDb: RoomDatabase() {
    abstract fun borrowers(): BorrowerDao
}