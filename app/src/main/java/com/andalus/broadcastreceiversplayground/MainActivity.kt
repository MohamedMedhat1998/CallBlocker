package com.andalus.broadcastreceiversplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.andalus.broadcastreceiversplayground.Adapters.ContactsAdapter
import com.andalus.broadcastreceiversplayground.ArchitectureComponents.ContactsModel
import com.andalus.broadcastreceiversplayground.ArchitectureComponents.ContactsModelFactory
import kotlinx.android.synthetic.main.activity_main.*

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
    }
}
