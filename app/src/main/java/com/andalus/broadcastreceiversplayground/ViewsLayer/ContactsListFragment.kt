package com.andalus.broadcastreceiversplayground.ViewsLayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.andalus.broadcastreceiversplayground.Adapters.ContactsAdapter
import com.andalus.broadcastreceiversplayground.Objects.BlockedContact
import com.andalus.broadcastreceiversplayground.Objects.Contact
import com.andalus.broadcastreceiversplayground.Objects.NormalContact
import com.andalus.broadcastreceiversplayground.R
import com.andalus.broadcastreceiversplayground.Repositories.BlockedContactsRepository
import com.andalus.broadcastreceiversplayground.ViewModelLayer.ContactsViewModel
import com.andalus.broadcastreceiversplayground.ViewModelLayer.ContactsViewModelFactory
import kotlinx.android.synthetic.main.fragment_contacts_list.view.*
import java.util.*

class ContactsListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contacts_list, container, false)
        val rvContactsList = view.rvContactsList
        rvContactsList.layoutManager = LinearLayoutManager(view.context)

        val adapter = ContactsAdapter<NormalContact>()
        rvContactsList.adapter = adapter

        val viewModel =
            ViewModelProviders.of(this, ContactsViewModelFactory(activity?.application!!)).get(ContactsViewModel::class.java)
        viewModel.contactsLiveData.observe(this, Observer {
            adapter.data = it
            adapter.notifyDataSetChanged()
        })

        val btnAdd = view.btnAdd
        val etName = view.etName
        val etNumber = view.etPhone

        btnAdd.setOnClickListener {
            BlockedContactsRepository(activity?.application!!).insert(
                BlockedContact(
                    Calendar.getInstance().timeInMillis,
                    etName.text.toString(),
                    etNumber.text.toString()
                )
            )
        }

        return view
    }
}
