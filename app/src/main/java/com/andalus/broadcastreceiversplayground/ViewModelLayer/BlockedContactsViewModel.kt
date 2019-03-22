package com.andalus.broadcastreceiversplayground.ViewModelLayer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.andalus.broadcastreceiversplayground.Objects.BlockedContact
import com.andalus.broadcastreceiversplayground.Objects.Contact
import com.andalus.broadcastreceiversplayground.Repositories.BlockedContactsRepository

class BlockedContactsViewModel(application: Application) : AndroidViewModel(application) {

    private val liveData = BlockedContactsRepository(application).allBlockedContacts

    val blockedContactsLiveData: LiveData<List<BlockedContact>>? =
        Transformations.switchMap(liveData!!) {
            BlockedContactsRepository(application).allBlockedContacts
        }
}