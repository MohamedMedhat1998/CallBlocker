package com.andalus.broadcastreceiversplayground.DataLayer

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.andalus.broadcastreceiversplayground.Objects.BlockedContact
import com.andalus.broadcastreceiversplayground.Objects.Contact

@Dao
interface BlockedContactsDao {

    @Insert
    fun insertAllContacts(vararg contacts: BlockedContact)

    @Query("SELECT * FROM BlockedContact")
    fun loadLiveData(): LiveData<List<BlockedContact>>

    @Query("SELECT * FROM BlockedContact")
    fun loadAll(): List<BlockedContact>

}