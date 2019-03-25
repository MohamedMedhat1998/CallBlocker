package com.andalus.broadcastreceiversplayground.Utils

import android.app.Application
import android.content.Context
import com.andalus.broadcastreceiversplayground.DatabaseOperations.QueryAllOperation
import com.andalus.broadcastreceiversplayground.Objects.Contact
import com.andalus.broadcastreceiversplayground.Repositories.BlockedContactsRepository
import com.andalus.broadcastreceiversplayground.Utils.Interfaces.Processor


class CallBlocker<T> : Processor<T> {


    override fun startProcessing(application: Application, incomingNumber: String?) {
        takeAction(BlockedContactsRepository(application), application, incomingNumber)
    }

    private fun <Object : Contact> takeAction(
        query: QueryAllOperation<Object>,
        application: Application,
        incomingNumber: String?
    ) {
        return query.getAll {
            if (it != null) {
                for (item in it) {
                    if (item.contactNumber.contains(incomingNumber.toString(), true) ||
                        incomingNumber?.contains(item.contactNumber, true) == true
                    ) {
                        block(application)
                        break
                    }
                }
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


