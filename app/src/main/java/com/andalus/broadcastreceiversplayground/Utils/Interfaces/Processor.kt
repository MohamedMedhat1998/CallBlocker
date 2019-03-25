package com.andalus.broadcastreceiversplayground.Utils.Interfaces

import android.app.Application

interface Processor<Object> {
    fun startProcessing(
        application: Application,
        incomingNumber: String?
    )
}