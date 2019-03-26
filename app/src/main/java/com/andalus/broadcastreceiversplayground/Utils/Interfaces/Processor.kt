package com.andalus.broadcastreceiversplayground.Utils.Interfaces

import android.app.Application

interface Processor {
    fun startProcessing(
        application: Application,
        incomingNumber: String?
    )
}