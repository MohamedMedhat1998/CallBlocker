package com.andalus.broadcastreceiversplayground

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast

class PhoneReceiver : BroadcastReceiver() {
    private val TAG = "PhoneReceiver"
    override fun onReceive(context: Context?, intent: Intent?) {

        Log.d(TAG, intent?.action.toString())

        val bundle = intent?.extras

        val incomingNumber = bundle?.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)
        Toast.makeText(context, incomingNumber, Toast.LENGTH_SHORT).show()
        Log.d(TAG,"Incoming Number: $incomingNumber")

        val state = bundle?.getString(TelephonyManager.EXTRA_STATE)
        Toast.makeText(context, state, Toast.LENGTH_SHORT).show()
        Log.d(TAG,"state: $state")

        if (intent != null && intent.extras != null) {
            for (key in intent.extras!!.keySet()) {
                Log.d(TAG,key)
            }
        }
    }

}