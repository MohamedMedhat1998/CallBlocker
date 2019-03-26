package com.andalus.broadcastreceiversplayground.Repositories

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.andalus.broadcastreceiversplayground.DataLayer.BlockedContactsDao
import com.andalus.broadcastreceiversplayground.DataLayer.BlockedContactsDatabase
import com.andalus.broadcastreceiversplayground.DatabaseOperations.*
import com.andalus.broadcastreceiversplayground.Objects.BlockedContact
import com.andalus.broadcastreceiversplayground.Utils.ListBackgroundTask
import com.andalus.broadcastreceiversplayground.Utils.UnitBackgroundTask

class BlockedContactsRepository(application: Application) : InsertOperation<BlockedContact>,
    QueryLiveDataOperation<BlockedContact>,
    QueryAllOperation<BlockedContact> {

    private val blockedContactsDao: BlockedContactsDao? =
        BlockedContactsDatabase.getDatabaseInstance(application)?.blockedListDao()

    val allBlockedContacts: LiveData<List<BlockedContact>>?

    init {
        allBlockedContacts = blockedContactsDao?.loadLiveData()
    }

    override fun getAll(onComplete: (List<BlockedContact>?) -> Unit) {
        ListBackgroundTask({ blockedContactsDao?.loadAll() }, onComplete).execute()
    }

    override fun getLiveData(): LiveData<List<BlockedContact>>? {
        return allBlockedContacts
    }

    override fun insert(vararg items: BlockedContact) {
        items.forEach {
            UnitBackgroundTask {
                blockedContactsDao?.insertAllContacts(it)
            }.execute()
        }
    }

}