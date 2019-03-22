package com.andalus.broadcastreceiversplayground.DataLayer

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andalus.broadcastreceiversplayground.Objects.BlockedContact
import com.andalus.broadcastreceiversplayground.Objects.Contact

@Database(entities = [BlockedContact::class], version = 1)
abstract class BlockedContactsDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "blocked-list-database"
        const val COLUMN_NAME = "name"
        const val COLUMN_NUMBER = "number"

        private var database: BlockedContactsDatabase? = null

        fun getDatabaseInstance(context: Context): BlockedContactsDatabase? {
            if (database == null) {
                database = Room.databaseBuilder(context, BlockedContactsDatabase::class.java, DATABASE_NAME).build()
            }
            return database
        }
    }

    abstract fun blockedListDao(): BlockedContactsDao
}