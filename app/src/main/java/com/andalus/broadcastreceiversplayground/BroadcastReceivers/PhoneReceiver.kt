package com.andalus.broadcastreceiversplayground.BroadcastReceivers

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import com.andalus.broadcastreceiversplayground.Repositories.BlockedContactsRepository
import com.andalus.broadcastreceiversplayground.Utils.CallBlockerProcessor
import com.andalus.broadcastreceiversplayground.Utils.BlockDialogProcessor
import com.andalus.broadcastreceiversplayground.Utils.Interfaces.Processor

class PhoneReceiver : BroadcastReceiver() {

    companion object {
        private var LAST_STATE: String? = TelephonyManager.EXTRA_STATE_IDLE
    }

    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent?.action == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {

            val bundle = intent.extras

            if (bundle != null) {

                val state = bundle.getString(TelephonyManager.EXTRA_STATE)
                val incomingNumber = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)
                val isRinging = state == TelephonyManager.EXTRA_STATE_RINGING
                val canViewDialog =
                    ((state == TelephonyManager.EXTRA_STATE_IDLE && LAST_STATE == TelephonyManager.EXTRA_STATE_RINGING) || (state == TelephonyManager.EXTRA_STATE_IDLE && LAST_STATE == TelephonyManager.EXTRA_STATE_OFFHOOK))

                if (canViewDialog) {

                    takeAction(
                        BlockDialogProcessor(BlockedContactsRepository(context?.applicationContext as Application)),
                        context.applicationContext as Application,
                        incomingNumber
                    )

                } else if (isRinging) {

                    takeAction(
                        CallBlockerProcessor(BlockedContactsRepository(context?.applicationContext as Application)),
                        context.applicationContext as Application,
                        incomingNumber
                    )

                }
                LAST_STATE = state

            }
        }
    }

    private fun takeAction(processor: Processor, application: Application, number: String?) {
        processor.startProcessing(application, number)
    }
}