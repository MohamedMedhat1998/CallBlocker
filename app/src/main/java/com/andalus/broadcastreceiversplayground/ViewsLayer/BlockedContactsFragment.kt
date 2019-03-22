package com.andalus.broadcastreceiversplayground.ViewsLayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.andalus.broadcastreceiversplayground.Adapters.ContactsAdapter
import com.andalus.broadcastreceiversplayground.Objects.BlockedContact
import com.andalus.broadcastreceiversplayground.R
import com.andalus.broadcastreceiversplayground.ViewModelLayer.BlockedContactsViewModel
import com.andalus.broadcastreceiversplayground.ViewModelLayer.BlockedContactsViewModelFactory
import kotlinx.android.synthetic.main.fragment_blocked_contacts.view.*

class BlockedContactsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_blocked_contacts, container, false)
        val rvBlockedList = view.rvBlockedList
        val adapter = ContactsAdapter<BlockedContact>()

        rvBlockedList.layoutManager = LinearLayoutManager(context)
        rvBlockedList.adapter = adapter

        val viewModel =
            ViewModelProviders.of(this, BlockedContactsViewModelFactory(activity?.application!!)).get(BlockedContactsViewModel::class.java)

        viewModel.blockedContactsLiveData?.observe(this, Observer {
            adapter.data = it.toMutableList()
            adapter.notifyDataSetChanged()
            Toast.makeText(context,"Observed",Toast.LENGTH_SHORT).show()
        })

        return view
    }
}
