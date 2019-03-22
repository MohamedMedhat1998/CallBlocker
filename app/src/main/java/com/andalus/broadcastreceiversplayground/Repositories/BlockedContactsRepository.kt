package com.andalus.broadcastreceiversplayground.Repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.andalus.broadcastreceiversplayground.DataLayer.BlockedContactsDao
import com.andalus.broadcastreceiversplayground.DataLayer.BlockedContactsDatabase
import com.andalus.broadcastreceiversplayground.DatabaseOperations.QueryByNumberOperation
import com.andalus.broadcastreceiversplayground.DatabaseOperations.InsertOperation
import com.andalus.broadcastreceiversplayground.Objects.BlockedContact
import com.andalus.broadcastreceiversplayground.Utils.ListBackgroundTask
import com.andalus.broadcastreceiversplayground.Utils.UnitBackgroundTask

class BlockedContactsRepository(application: Application) : InsertOperation<BlockedContact>,
    QueryByNumberOperation<BlockedContact> {

    private val blockedContactsDao: BlockedContactsDao? =
        BlockedContactsDatabase.getDatabaseInstance(application)?.blockedListDao()

    val allBlockedContacts: LiveData<List<BlockedContact>>?

    init {
        allBlockedContacts = blockedContactsDao?.loadAll()
    }

    override fun insert(vararg items: BlockedContact) {
        items.forEach {
            UnitBackgroundTask {
                blockedContactsDao?.insertAllContacts(it)
            }.execute()
        }
    }

    override fun getByNumber(number: String?, onQueryCompleted: (List<BlockedContact>?) -> Unit) {
        ListBackgroundTask({
            blockedContactsDao?.getByNumber(number)
        }, onQueryCompleted).execute()
    }

}