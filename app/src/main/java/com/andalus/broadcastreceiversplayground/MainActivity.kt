package com.andalus.broadcastreceiversplayground

import android.content.IntentSender
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.andalus.broadcastreceiversplayground.Adapters.ContactsAdapter
import com.andalus.broadcastreceiversplayground.ArchitectureComponents.ContactsModel
import com.andalus.broadcastreceiversplayground.ArchitectureComponents.ContactsModelFactory
import com.andalus.broadcastreceiversplayground.Data.BlockedListDatabase
import com.andalus.broadcastreceiversplayground.Data.ContactObject
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvContactsList = rvContactsList
        rvContactsList.layoutManager = LinearLayoutManager(applicationContext)

        val adapter = ContactsAdapter()
        rvContactsList.adapter = adapter

        val viewModel = ViewModelProviders.of(this, ContactsModelFactory(application)).get(ContactsModel::class.java)
        viewModel.liveData.observe(this, Observer {
            adapter.data = it
            adapter.notifyDataSetChanged()
        })
        //----------------------------------------
        val etName = etName
        val etPhone = etPhone

        val btnAdd = btnAdd
        val btnUpdate = btnUpdate

        btnAdd.setOnClickListener {
            BackgroundWorker {
                Log.d("BackgroundWorker", "Add")
                val contactObject =
                    ContactObject(Calendar.getInstance().timeInMillis, etName.text.toString(), etPhone.text.toString())
                BlockedListDatabase.getDatabaseInstance(applicationContext)?.blockedListDao()
                    ?.insertAllContacts(contactObject)
            }.execute()
        }

        btnUpdate.setOnClickListener {
            BackgroundWorkerWithResult({
                BlockedListDatabase.getDatabaseInstance(applicationContext)?.blockedListDao()?.loadAll()
                    ?.toMutableList()
            }) {
                adapter.data = it ?: adapter.data
                adapter.notifyDataSetChanged()
            }.execute()
        }
    }

    class BackgroundWorker(private var task: () -> Unit) :
        AsyncTask<Unit, Unit, Unit>() {
        override fun doInBackground(vararg p0: Unit?) {
            Log.d("BackgroundWorker", "doInBackground")
            task()
        }
    }

    class BackgroundWorkerWithResult(
        private var task: () -> MutableList<ContactObject>?,
        private var onFinished: (MutableList<ContactObject>?) -> Unit
    ) : AsyncTask<Unit, Unit, MutableList<ContactObject>>() {
        override fun doInBackground(vararg p0: Unit?): MutableList<ContactObject>? {
            return task()
        }

        override fun onPostExecute(result: MutableList<ContactObject>?) {
            onFinished(result)
        }
    }
}
