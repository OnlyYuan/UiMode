package com.example.uimode.wight

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.uimode.mode.ContactEntity
import com.example.uimode.mode.api.ContactDao

@Database(version = 1,entities = [ContactEntity::class])
abstract class AppDatabase : RoomDatabase() {
    abstract  fun  contactDao():ContactDao

    companion object{

        private var instance:AppDatabase ?=null

        fun getDataBase(context: Context):AppDatabase{
            instance?.let {
                return it
            }
            return  Room.databaseBuilder(context.applicationContext,
            AppDatabase::class.java,"m_app_database")
                    .build().apply {
                        instance =this
                    }

        }

        
    }
}