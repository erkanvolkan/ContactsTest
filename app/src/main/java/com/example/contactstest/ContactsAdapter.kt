package com.example.contactstest

import android.service.autofill.Dataset
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*

class ContactsAdapter(val dataset: ContactResponse): RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {

    class ContactsViewHolder(contactsItem: View):
    RecyclerView.ViewHolder(contactsItem){
        val tvItemName: TextView = contactsItem.tv_item_name
        val tvItemEmail: TextView = contactsItem.tv_item_email

    }

    /**
     * Creates the specific ViewHolder
     * defined in the Parent type
     * @return the viewHOlder defines in adapter
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
       val view: View = LayoutInflater.from(parent.context)
           .inflate(R.layout.item_layout, parent, false)
        val contactsViewHolder = ContactsViewHolder(view)
        return contactsViewHolder

    }

    /**
     * Hold tthe reference on N items
     * passed to the adapter
     */
    override fun getItemCount(): Int {
        return dataset.contacts.size
    }

    /**
     * Connects the data with specific view
     */
    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.tvItemName.text = dataset.contacts[position].name
        holder.tvItemEmail.text = dataset.contacts[position].email
    }
}