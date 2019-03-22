package com.andalus.broadcastreceiversplayground.Repositories

import android.content.Context
import android.database.Cursor
import android.os.AsyncTask
import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.andalus.broadcastreceiversplayground.Objects.Contact
import com.andalus.broadcastreceiversplayground.Objects.NormalContact
import java.util.*

class ContactsProvider {

    companion object {
        private val globalContactsList = MutableLiveData<MutableList<NormalContact>>()
        fun getContactsList(context: Context): LiveData<MutableList<NormalContact>> {
            ContactsAsyncTask().execute(context)
            return globalContactsList
        }
    }

    private class ContactsAsyncTask : AsyncTask<Context, Unit, Cursor?>() {
        override fun doInBackground(vararg p0: Context?): Cursor? {
            val projection = arrayOf(
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
            )
            val selection =
                "${ContactsContract.CommonDataKinds.Phone.TYPE} = ${ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE}"
            return p0[0]?.contentResolver?.query(
                ContactsContract.Data.CONTENT_URI,
                projection,
                selection,
                null,
                ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME
            )
        }

        override fun onPostExecute(result: Cursor?) {
            super.onPostExecute(result)
            globalContactsList.value = result?.toContactsList() ?: mutableListOf()
        }
    }
}

fun Cursor.toContactsList(): MutableList<NormalContact> {
    val contactsList = mutableListOf<NormalContact>()
    while (this.moveToNext()) {
        contactsList.add(
            NormalContact(
                Calendar.getInstance().timeInMillis,
                this.getString(0),
                this.getString(1)
            )
        )
    }
    return contactsList
}

