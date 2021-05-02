package com.example.dell.covidtweets

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class SearchResultsActivity : AppCompatActivity() {

      var titlesList= mutableListOf<String>()
     var addressesList= mutableListOf<String>()
     var phonenos= mutableListOf<String>()
    var currentTime= mutableListOf<String>()
    private val db:FirebaseFirestore= FirebaseFirestore.getInstance()
    private val collectionReference:CollectionReference=db.collection("data")
    var recyclerAdapter:RecyclerAdapter?=null
    var recyclerView:RecyclerView?=null
    var datalist:ArrayList<String>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results)



        recyclerView=findViewById(R.id.firestore_list)
        val state=intent.getStringExtra("state")
        val city= intent.getStringExtra("city")
        val resources= intent.getStringExtra("resources")
        Toast.makeText(this, "" + state, Toast.LENGTH_SHORT).show()
        val db = FirebaseFirestore.getInstance()

//        db.collection("data")
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    Log.d("TAG", "${document.id} => ${document.data}")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d("TAG", "Error getting documents: ", exception)
//            }
        postToList()
        Toast.makeText(
            this,
            titlesList.toString() + "," + addressesList.toString(),
            Toast.LENGTH_SHORT
        ).show()

//        Toast.makeText(this,title+","+address+","+phoneno, Toast.LENGTH_SHORT).show()




    }
     fun addToList(title: String, address: String, phoneno: String){
        titlesList.add(title)
        addressesList.add(address)
        phonenos.add(phoneno)

        Toast.makeText(this, titlesList.toString(), Toast.LENGTH_SHORT).show()
         recyclerView!!.layoutManager=LinearLayoutManager(this)
         recyclerView!!.adapter=RecyclerAdapter(titlesList, addressesList, phonenos)
    }

    private fun postToList(){
        val state=intent.getStringExtra("state")
        val city= intent.getStringExtra("city")
        val resources= intent.getStringExtra("resources")
        if( state!=" " && city!=" " && resources!=" ") {
            db.collection("data")
                .whereEqualTo("State", state)
                .whereEqualTo("City", city)
               .whereEqualTo("resource subtype", resources)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        val title = document.getString("details")
                        val address = document.getString("address")
                        val phoneno = document.getString("contact number")
                        val currtime = document.getString("current time")
                        val dateFormat = SimpleDateFormat("hh:mm a")


//                    Toast.makeText(this,title+","+address+","+phoneno, Toast.LENGTH_SHORT).show()

                        if (title != null) {
                            if (address != null) {
                                if (phoneno != null) {
//                                Toast.makeText(this,document.data.toString(), Toast.LENGTH_SHORT).show()

                                    addToList(title, address, phoneno)
                                }
                            }
                        }

                        Log.d("TAG", "${document.id} => ${document.data}")
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "failiure", Toast.LENGTH_SHORT).show()

                    Log.w("TAG", "Error getting documents: ", exception)
                }
        }
       else if( state==" " && city!=" " && resources==" ") {
            db.collection("data")
               // .whereEqualTo("State", state)
                .whereEqualTo("City", city)
               // .whereEqualTo("resource subtype", resources)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        val title = document.getString("details")
                        val address = document.getString("address")
                        val phoneno = document.getString("contact number")
                        val currtime = document.getString("current time")
                        val dateFormat = SimpleDateFormat("hh:mm a")


//                    Toast.makeText(this,title+","+address+","+phoneno, Toast.LENGTH_SHORT).show()

                        if (title != null) {
                            if (address != null) {
                                if (phoneno != null) {
//                                Toast.makeText(this,document.data.toString(), Toast.LENGTH_SHORT).show()

                                    addToList(title, address, phoneno)
                                }
                            }
                        }

                        Log.d("TAG", "${document.id} => ${document.data}")
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "failiure", Toast.LENGTH_SHORT).show()

                    Log.w("TAG", "Error getting documents: ", exception)
                }
        }

     else if( state.equals(" ") && city.equals(" ") && !(resources.equals(""))) {
            db.collection("data")
               // .whereEqualTo("State", state)
              //  .whereEqualTo("City", city)
                .whereEqualTo("resource type", resources)
                .whereEqualTo("resource subtype", resources)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        val title = document.getString("details")
                        val address = document.getString("address")
                        val phoneno = document.getString("contact number")
                        val currtime = document.getString("current time")
                        val dateFormat = SimpleDateFormat("hh:mm a")


//                    Toast.makeText(this,title+","+address+","+phoneno, Toast.LENGTH_SHORT).show()

                        if (title != null) {
                            if (address != null) {
                                if (phoneno != null) {
//                                Toast.makeText(this,document.data.toString(), Toast.LENGTH_SHORT).show()

                                    addToList(title, address, phoneno)
                                }
                            }
                        }

                        Log.d("TAG", "${document.id} => ${document.data}")
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "failiure", Toast.LENGTH_SHORT).show()

                    Log.w("TAG", "Error getting documents: ", exception)
                }
        }

    }




}


