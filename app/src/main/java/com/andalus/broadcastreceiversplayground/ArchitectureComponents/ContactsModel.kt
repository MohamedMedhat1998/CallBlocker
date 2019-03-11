package com.andalus.broadcastreceiversplayground.ArchitectureComponents

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.andalus.broadcastreceiversplayground.Data.ContactObject
import com.andalus.broadcastreceiversplayground.Providers.ContactsProvider

class ContactsModel(application: Application) : AndroidViewModel(application) {

    private val privateLiveData: MutableLiveData<MutableList<ContactObject>> = MutableLiveData()

    val liveData: LiveData<MutableList<ContactObject>> = Transformations.switchMap(privateLiveData) {
        Log.d("LiveData","works")
        ContactsProvider.getContactsList(application.applicationContext)
    }

    init {
        privateLiveData.value = mutableListOf()
    }


}