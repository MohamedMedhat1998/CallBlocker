package com.andalus.broadcastreceiversplayground.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ContactObject::class], version = 1)
abstract class BlockedListDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "blocked-list"

        private var database: BlockedListDatabase? = null

        fun getDatabaseInstance(context: Context): BlockedListDatabase? {
            if (database == null) {
                database = Room.databaseBuilder(context, BlockedListDatabase::class.java, DATABASE_NAME).build()
            }
            return database
        }
    }

    abstract fun blockedListDao(): BlockedListDao
}