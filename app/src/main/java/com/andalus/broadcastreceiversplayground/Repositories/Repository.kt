package com.andalus.broadcastreceiversplayground.Repositories

import androidx.lifecycle.LiveData

open class Repository<Object,Dao>(val allItems: LiveData<List<Object>>?,val dao :Dao) {

}