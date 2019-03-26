package com.andalus.broadcastreceiversplayground.Utils

import android.app.Application
import android.content.Intent
import com.andalus.broadcastreceiversplayground.Utils.Interfaces.Processor

class DialogProcessor : Processor {
    override fun startProcessing(application: Application, incomingNumber: String?) {
        val intent = Intent(Constants.BLOCK_DIALOG_ACTION)
        intent.putExtra(Constants.INCOMING_NUMBER_KEY, incomingNumber)
        application.startActivity(intent)
    }
}