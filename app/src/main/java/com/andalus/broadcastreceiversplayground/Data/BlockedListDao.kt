package com.andalus.broadcastreceiversplayground.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BlockedListDao{

    @Query("SELECT * FROM ContactObject where number = (:passedNumber)")
    fun loadByNumber(passedNumber: String) : List<ContactObject>

    @Insert
    fun insertAllContacts(vararg contacts : ContactObject)

    @Query("SELECT * FROM ContactObject")
    fun loadAll() : List<ContactObject>

}