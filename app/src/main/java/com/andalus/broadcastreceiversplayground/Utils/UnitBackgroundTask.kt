package com.andalus.broadcastreceiversplayground.Utils

import android.os.AsyncTask

class UnitBackgroundTask(private val task: () -> Unit) : AsyncTask<Unit,Unit,Unit>() {
    override fun doInBackground(vararg p0: Unit?) {
        task()
    }
}