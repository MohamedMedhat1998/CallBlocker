package com.andalus.broadcastreceiversplayground.DatabaseOperations

interface QueryAllOperation<Object> {
    fun getAll(): List<Object>
}