package com.andalus.broadcastreceiversplayground.DatabaseOperations

interface InsertOperation<T> {
    fun insert(vararg items: T)
}