package com.andalus.broadcastreceiversplayground.BroadcastReceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import com.andalus.broadcastreceiversplayground.CallBlocker

class PhoneReceiver : BroadcastReceiver() {
    companion object {
        private const val TAG = "PhoneReceiver"
    }

    override fun onReceive(context: Context?, intent: Intent?) {

        //Log.d(TAG, intent?.action.toString())
        if (intent?.action == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            val bundle = intent.extras
            if (bundle != null) {
                val state = bundle.getString(TelephonyManager.EXTRA_STATE)
                val incomingNumber = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)
                CallBlocker.startProcessing(state = state, incomingNumber = incomingNumber)

                /*Toast.makeText(context, incomingNumber, Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Incoming Number: $incomingNumber")

                Toast.makeText(context, state, Toast.LENGTH_SHORT).show()
                Log.d(TAG, "state: $state")*/
            }
        }
    }
}