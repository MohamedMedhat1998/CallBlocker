package com.andalus.broadcastreceiversplayground.Data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ContactObject::class],version = 1)
abstract class BlockedListDatabase : RoomDatabase() {
}