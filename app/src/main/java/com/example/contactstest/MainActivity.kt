package com.example.contactstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    val urlContacts: String =  "https://api.androidhive.info/contacts/"
    val urlBaseUrl = "https://api.androidhive.info/"
    val urlEndpoint = "contacts"

    val displayFragment: FragmentDisplay by lazy{
        FragmentDisplay()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val policy =
            StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        getMeContacts()
    }

    private fun getMeContacts(){
        val network = NetworkContacts(urlContacts)
        val contactResponse = network.executeNetworkCall()
//        val counter = 0
val displayFragment = FragmentDisplay.newInstance(contactResponse)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_fragment_container,
                displayFragment)
            .commit()
        displayFragment.passData(contactResponse)


//        Toast.makeText(this, contactResponse.toString(), Toast.LENGTH_LONG).show()
    }
}