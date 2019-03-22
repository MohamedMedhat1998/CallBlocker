package com.andalus.broadcastreceiversplayground.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andalus.broadcastreceiversplayground.Objects.Contact
import com.andalus.broadcastreceiversplayground.R
import kotlinx.android.synthetic.main.contacts_list_item.view.*

class ContactsAdapter<T : Contact>(var data: MutableList<T> = mutableListOf()) :
    RecyclerView.Adapter<ContactsAdapter.ContactHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.contacts_list_item, parent, false)
        return ContactHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.tvNameItem.text = data[position].contactName
        holder.tvNumberItem.text = data[position].contactNumber
    }

    class ContactHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNameItem: TextView = view.tvNameItem
        val tvNumberItem: TextView = view.tvNumberItem
    }
}

