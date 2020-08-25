package com.example.contactstest

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContactResponse(val contacts: List <ContactItem>) : Parcelable

@Parcelize
data class ContactItem(
    val id: String,
    val name: String,
    val email: String,
    val gender: String,
    val address: String,
    val phone: PhoneItem
): Parcelable

@Parcelize
data class PhoneItem(
    val mobile: String,
    val home: String,
    val office: String
): Parcelable

//api.androidhive.info/contacts/