package com.andalus.broadcastreceiversplayground

import android.telephony.TelephonyManager

class CallBlocker {

    companion object {
        fun startProcessing(state: String?, incomingNumber: String?) {
            if (state == TelephonyManager.EXTRA_STATE_RINGING) {
                if (incomingNumber.isInBlockedList()) {
                    block()
                }
            }
        }

        private fun block() {

        }
    }
}

private fun String?.isInBlockedList(): Boolean {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}
