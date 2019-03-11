package com.andalus.broadcastreceiversplayground.Data

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class ContactObject(
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "number") var number: String?
)