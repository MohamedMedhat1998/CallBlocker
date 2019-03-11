package com.andalus.broadcastreceiversplayground.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andalus.broadcastreceiversplayground.Data.ContactObject
import com.andalus.broadcastreceiversplayground.R
import kotlinx.android.synthetic.main.contacts_list_item.view.*

class ContactsAdapter(var data: MutableList<ContactObject> = mutableListOf()) :
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
        holder.tvNameItem.text = data[position].name
        holder.tvNumberItem.text = data[position].number
    }

    class ContactHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNameItem: TextView = view.tvNameItem
        val tvNumberItem: TextView = view.tvNumberItem
    }
}