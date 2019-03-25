package com.andalus.broadcastreceiversplayground.DatabaseOperations

interface QueryAllOperation<Object> {
    fun getAll(onComplete: (List<Object>?) -> Unit)
}