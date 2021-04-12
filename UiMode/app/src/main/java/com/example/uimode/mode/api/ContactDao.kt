package com.example.uimode.mode.api

import androidx.room.*
import com.example.uimode.mode.ContactEntity

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contactEntity: ContactEntity)

    @Update
    fun updateContact(contactEntity: ContactEntity)

    @Query("select * from ContactEntity")
    fun loadAllContact():List<ContactEntity>

    @Delete
    fun  deleteContact(contactEntity: ContactEntity)

//    @Query("delete from ContactEntity where name= :name")
//    fun deleteByName(name:String)



}