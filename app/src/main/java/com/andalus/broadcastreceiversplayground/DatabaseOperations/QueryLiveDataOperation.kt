package com.andalus.broadcastreceiversplayground.DatabaseOperations

import androidx.lifecycle.LiveData

interface QueryLiveDataOperation<Object> {
    fun getLiveData(): LiveData<List<Object>>?
}