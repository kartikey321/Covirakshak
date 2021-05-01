package com.example.dell.covidtweets.fragments

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.dell.covidtweets.R
import com.example.dell.covidtweets.SearchActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.sql.Timestamp


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    var searchbtn: ImageView?=null
    var callbtn: ImageView?=null
    var cityedittext:EditText?=null
    var addressedittext:EditText?=null
    var restype:EditText?=null
    var ressubtype:EditText?=null
    var contact_name:EditText?=null
    var contact_no:EditText?=null
    var submit:Button?=null
    var details:EditText?=null






    var listSpinner = ArrayList<String>()

    // to store the city and state in the format : City , State. Eg: New Delhi , India
    var listAll = ArrayList<String>()

    // for listing all states
    var listState = ArrayList<String>()

    // for listing all cities
    var listCity = ArrayList<String>()

    // access all auto complete text views
    var act: AutoCompleteTextView? = null
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_search, container, false)
        searchbtn=v.findViewById<View>(R.id.imageView3) as ImageView
        callbtn=v.findViewById<View>(R.id.imageView4) as ImageView
        cityedittext=v.findViewById<View>(R.id.cityedittext)as EditText
        addressedittext=v.findViewById<View>(R.id.addressedittext)as EditText
        restype=v.findViewById<View>(R.id.restype)as EditText
        ressubtype=v.findViewById<View>(R.id.ressubtype)as EditText
        details=v.findViewById<View>(R.id.details)as EditText

        contact_name=v.findViewById<View>(R.id.contact_name)as EditText
        contact_no=v.findViewById<View>(R.id.contact_no)as EditText
        submit=v.findViewById<View>(R.id.submitbtn)as Button




        val db = FirebaseFirestore.getInstance()








        callbtn!!.setOnClickListener{
            val callIntent= Intent(Intent.ACTION_CALL)
            callIntent.setData(Uri.parse("tel:" + "1075"))
            startActivity(callIntent)
        }
        val autotextView = v.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView2)
        val states = resources.getStringArray(R.array.States)
        val adapter = context?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_list_item_1, states
            )
        }
        autotextView.setAdapter(adapter)

        submit!!.setOnClickListener {
            val state =  autotextView.getText().toString()
            val city= cityedittext!!.text.toString()
            val address=addressedittext!!.text.toString()
            val resource= restype!!.text.toString()
            val subresources= ressubtype!!.text.toString()
            val name=contact_name!!.text.toString()
            val phoneno= contact_no!!.text.toString()
            val details= details!!.text.toString()

            val timestamp = Timestamp(System.currentTimeMillis())
            val entreddata = hashMapOf(
                "State" to state,
                "City" to city,
                "address" to address,
                "resource type" to resource,
                "resource subtype" to subresources,
                    "details" to details,
                "name" to name,
                "contact number" to phoneno,
                "timestamp" to timestamp

            )

            if(state!=null && city!=null && address!=null && resource!=null && subresources!=null && details!=null && name!=null && phoneno!=null ) {

                db.collection("data")
                        .add(entreddata)
                        .addOnSuccessListener { documentReference ->
                            Toast.makeText(activity, "Thank you for sharing this information with us", Toast.LENGTH_SHORT).show()
                            val intent = Intent(activity, HomeFragment::class.java)
                            startActivity(intent)
                            Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding document", e)
                        }
            }
            else{
                Toast.makeText(activity, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
        }



        searchbtn!!.setOnClickListener{
            val intent = Intent(activity, SearchActivity::class.java)
            startActivity(intent)        }


        return v    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}