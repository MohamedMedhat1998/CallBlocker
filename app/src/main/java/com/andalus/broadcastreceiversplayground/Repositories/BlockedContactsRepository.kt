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
    QueryByNumberOperation<BlockedContact>,
    QueryLiveDataOperation<BlockedContact>,
    QueryAllOperation<BlockedContact> {

    private val application = application

    private val blockedContactsDao: BlockedContactsDao? =
        BlockedContactsDatabase.getDatabaseInstance(application)?.blockedListDao()

    val allBlockedContacts: LiveData<List<BlockedContact>>?

    init {
        allBlockedContacts = blockedContactsDao?.loadLiveData()
    }

    override fun getAll(onComplete: (List<BlockedContact>?) -> Unit) {
        ListBackgroundTask({blockedContactsDao?.loadAll()},onComplete).execute()
    }

    override fun getLiveData(): LiveData<List<BlockedContact>>? {
        Toast.makeText(application.baseContext, "I'm here", Toast.LENGTH_SHORT).show()
        if (allBlockedContacts?.value == null) {
            Toast.makeText(application.baseContext, "allBlockedContacts.value is null", Toast.LENGTH_SHORT).show()
        }
        return allBlockedContacts
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