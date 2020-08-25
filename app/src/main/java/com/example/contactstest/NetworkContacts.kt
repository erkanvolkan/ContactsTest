package com.example.contactstest

import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.NullPointerException
import java.net.HttpURLConnection
import java.net.URL

/**
 * Query and return contactResponse
 */
class NetworkContacts(val url: String) {

    fun executeNetworkCall(): ContactResponse{
        var contactURL = URL(url)
        var httpURLConnection: HttpURLConnection = contactURL.openConnection() as HttpURLConnection
        httpURLConnection.connectTimeout = 10000
        httpURLConnection.readTimeout = 15000
        httpURLConnection.requestMethod = "GET"
        httpURLConnection.doInput = true
        //execute the connection
        httpURLConnection.connect()
        //receive data
        val inputStream = httpURLConnection.inputStream
        //receive server response
        val responseCode = httpURLConnection.responseCode
        val stringResponse = parseIstoString(inputStream)
        val contactResponse: ContactResponse = parsePoko(stringResponse)

        return contactResponse
    }

    private fun parsePoko(stringResponse: String?): ContactResponse {
        if(stringResponse == null)
         throw NullPointerException()
        val jsonResponse: JSONObject = JSONObject(stringResponse)
        val jsonArray = jsonResponse.getJSONArray("contacts")

        var phoneItem: PhoneItem
        var contactItem: ContactItem
        val listofContactItem = mutableListOf<ContactItem>()

        // 0 to n
        //0 to n-1
        for(index in 0 until jsonArray.length()){
            val jsonItem = jsonArray.get(index) as JSONObject
            val jsonItemPhone = jsonItem.getJSONObject("phone")
            phoneItem = PhoneItem(
                jsonItemPhone.getString("mobile"),
                jsonItemPhone.getString("home"),
                jsonItemPhone.getString("office")
            )

            contactItem = ContactItem(
                phone = phoneItem,
                id = jsonItem.getString("id"),
                name = jsonItem.getString("name"),
                gender = jsonItem.getString("gender"),
                email = jsonItem.getString("email"),
                address = jsonItem.getString("address")
            )

            listofContactItem.add(contactItem)
        }

        return ContactResponse(listofContactItem)
    }

    private fun parseIstoString(inputStream: InputStream): String? {
        val builder = StringBuilder()
        val reader = BufferedReader(InputStreamReader(inputStream))
        var line: String? = reader.readLine()
        while (line != null) {
            builder.append("$line\n")
            line = reader.readLine()
        }
        if (builder.length == 0) {
            return null
        }
        return builder.toString()
    }

}
