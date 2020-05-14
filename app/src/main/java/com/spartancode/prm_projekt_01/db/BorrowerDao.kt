package com.spartancode.prm_projekt_01.db

import android.database.Cursor
import androidx.room.*

@Dao
interface BorrowerDao {
    @Query("SELECT * FROM Borrower")
    fun getAll(): List<Borrower>

    @Query("SELECT * FROM Borrower WHERE id between :from and :to")
    fun getAll(from: Int, to: Int): Cursor

    @Query("SELECT * FROM Borrower WHERE id = :id")
    fun getById(id: Int): Borrower

    @Insert
    fun insert(borrower: Borrower)

    @Update
    fun update(borrower: Borrower)

    @Delete
    fun delete(borrower: Borrower)
}