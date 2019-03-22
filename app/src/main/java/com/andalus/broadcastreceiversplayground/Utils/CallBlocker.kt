package com.andalus.broadcastreceiversplayground.Utils

import android.app.Application
import android.content.Context
import android.widget.Toast
import com.andalus.broadcastreceiversplayground.DataLayer.BlockedContactsDatabase
import com.andalus.broadcastreceiversplayground.Repositories.BlockedContactsRepository


class CallBlocker(val application: Application) {

    companion object {

        fun startProcessing(application: Application, incomingNumber: String?) {
            BlockedContactsRepository(application).getByNumber(incomingNumber) {
                val found = it != null && it.isNotEmpty()
                if (found) {
                    block(application)
                }
            }
        }

        private fun block(application: Application) {
            val telephonyManager = application.applicationContext.getSystemService(Context.TELEPHONY_SERVICE)
            val getITelephony = telephonyManager::class.java.getDeclaredMethod("getITelephony")
            getITelephony.isAccessible = true
            val iTelephony = getITelephony.invoke(telephonyManager)
            val silenceRinger = iTelephony::class.java.getDeclaredMethod("silenceRinger")
            val endCall = iTelephony::class.java.getDeclaredMethod("endCall")
            silenceRinger.invoke(iTelephony)
            endCall.invoke(iTelephony)
        }
    }
}


