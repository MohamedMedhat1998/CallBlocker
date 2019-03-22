package com.andalus.broadcastreceiversplayground.ViewModelLayer

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.andalus.broadcastreceiversplayground.DataLayer.BlockedContactsDatabase
import com.andalus.broadcastreceiversplayground.Objects.Contact
import com.andalus.broadcastreceiversplayground.Objects.NormalContact
import com.andalus.broadcastreceiversplayground.Repositories.ContactsProvider

class ContactsViewModel(application: Application) : AndroidViewModel(application) {

    private val privateContactsLiveData: MutableLiveData<MutableList<NormalContact>> = MutableLiveData()
    private val privateBlockedContactsLiveData: MutableLiveData<BlockedContactsDatabase> = MutableLiveData()

    init {
        privateContactsLiveData.value = mutableListOf()
        privateBlockedContactsLiveData.value = BlockedContactsDatabase.getDatabaseInstance(application.applicationContext)
    }

    val contactsLiveData: LiveData<MutableList<NormalContact>> = Transformations.switchMap(privateContactsLiveData) {
        Log.d("LiveData", "works")
        ContactsProvider.getContactsList(application.applicationContext)
    }

}