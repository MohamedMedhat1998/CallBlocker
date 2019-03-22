package com.andalus.broadcastreceiversplayground.DatabaseOperations

interface QueryByNumberOperation<Object> {
    fun getByNumber(number: String?, onQueryCompleted: (List<Object>?) -> Unit)
}