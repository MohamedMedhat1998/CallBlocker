package com.andalus.broadcastreceiversplayground.Objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andalus.broadcastreceiversplayground.DataLayer.BlockedContactsDatabase

@Entity
data class BlockedContact(
    @PrimaryKey var uId: Long,
    @ColumnInfo(name = BlockedContactsDatabase.COLUMN_NAME) var name: String,
    @ColumnInfo(name = BlockedContactsDatabase.COLUMN_NUMBER) var number: String
) : Contact(uId, name, number)