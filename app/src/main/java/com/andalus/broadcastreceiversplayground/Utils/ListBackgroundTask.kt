package com.andalus.broadcastreceiversplayground.Utils

import android.os.AsyncTask

class ListBackgroundTask<Object>(
    private val task: () -> List<Object>?,
    private val onComplete: (List<Object>?) -> Unit
) : AsyncTask<Unit, Unit, List<Object>?>() {

    override fun doInBackground(vararg p0: Unit?): List<Object>? {
        return task()
    }

    override fun onPostExecute(result: List<Object>?) {
        super.onPostExecute(result)
        onComplete(result)
    }
}