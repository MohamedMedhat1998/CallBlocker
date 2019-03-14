package com.andalus.broadcastreceiversplayground.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ContactObject(
    @PrimaryKey var uid: Long,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "number") var number: String
)