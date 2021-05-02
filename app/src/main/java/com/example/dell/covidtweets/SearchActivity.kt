package com.example.dell.covidtweets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import com.example.dell.covidtweets.R
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class SearchActivity : AppCompatActivity() {
    var searchbtn: Button?=null
    var cityet:EditText?=null
    var resourcetype:EditText?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchbtn=findViewById<View>(R.id.search)as Button
        cityet=findViewById<View>(R.id.cityet)as EditText
        resourcetype=findViewById<View>(R.id.resourcetype)as EditText



        val autotextView = findViewById<AutoCompleteTextView>(R.id.statelist)
        val states = resources.getStringArray(R.array.States)
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, states)
        autotextView.setAdapter(adapter)

        searchbtn!!.setOnClickListener{
            val state =  autotextView.getText().toString()
            val city = cityet!!.text.toString()
            val resources= resourcetype!!.text.toString()

            val ref: Query = FirebaseFirestore.getInstance().collection("data")
                .whereEqualTo("State", state)
                .whereEqualTo("City", city)
                .whereEqualTo("resource subtype", resources)

            val intent = Intent(this, SearchResultsActivity::class.java).apply {
                putExtra("state",state)
                putExtra("city",city)
                putExtra("resources",resources)
                putExtra("ref",ref.toString())
            }
            startActivity(intent)

        }
    }
}