package com.example.dell.covidtweets.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.dell.covidtweets.*
import me.relex.circleindicator.CircleIndicator3

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private var titlesList= mutableListOf<String>()
    private var imagesList= mutableListOf<Int>()
    var viewpager2:ViewPager2?=null

    var searchbtn:ImageView?=null
    var callbtn:ImageView?=null
    var button3:Button?=null
    var button4:Button?=null
    var button5:Button?=null
    var button6:Button?=null
    var button7:Button?=null
    var button8:Button?=null

    var medicine:Button?=null
    var beds:Button?=null
    var plasma:Button?=null
    var icu:Button?=null
    var oxyzen:Button?=null
    var catering:Button?=null







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
        postToList()

        val v = inflater.inflate(R.layout.fragment_home, container, false)
        viewpager2=v.findViewById(R.id.viewpager2) as ViewPager2
        viewpager2!!.adapter= ViewPagerAdapter(titlesList,imagesList)
        viewpager2!!.orientation= ViewPager2.ORIENTATION_HORIZONTAL
        var indicator=v.findViewById<CircleIndicator3>(R.id.indicator)
        indicator.setViewPager(viewpager2)
        searchbtn=v.findViewById<View>(R.id.imageView) as ImageView
        callbtn=v.findViewById<View>(R.id.imageView2) as ImageView
        button3=v.findViewById<View>(R.id.button3) as Button
        button4=v.findViewById<View>(R.id.button4) as Button
        button5=v.findViewById<View>(R.id.button5) as Button
        button6=v.findViewById<View>(R.id.button6) as Button
        button7=v.findViewById<View>(R.id.button7) as Button
        button8=v.findViewById<View>(R.id.button8) as Button

        medicine=v.findViewById<View>(R.id.medicine) as Button
        beds=v.findViewById<View>(R.id.beds) as Button
        plasma=v.findViewById<View>(R.id.plasma) as Button
        icu=v.findViewById<View>(R.id.icu) as Button
        oxyzen=v.findViewById<View>(R.id.oxyzen) as Button
        catering=v.findViewById<View>(R.id.catering) as Button







        button3!!.setOnClickListener{
            val intent = Intent(v.context, SearchResultsActivity::class.java).apply {
                putExtra("state"," ")
                putExtra("city","Delhi")
                putExtra("resources"," ")
            }
            startActivity(intent)
        }
        button4!!.setOnClickListener{
            val intent = Intent(v.context, SearchResultsActivity::class.java).apply {
                putExtra("state"," ")
                putExtra("city","Noida")
                putExtra("resources"," ")
            }

            startActivity(intent)
        }
        button5!!.setOnClickListener{
            val intent = Intent(v.context, SearchResultsActivity::class.java).apply {
                putExtra("state"," ")
                putExtra("city","Gurgaon")
                putExtra("resources"," ")
            }
            startActivity(intent)
        }
        button6!!.setOnClickListener{
            val intent = Intent(v.context, SearchResultsActivity::class.java).apply {
                putExtra("state"," ")
                putExtra("city","Mumbai")
                putExtra("resources"," ")
            }
            startActivity(intent)
        }
        button7!!.setOnClickListener{
            val intent = Intent(v.context, SearchResultsActivity::class.java).apply {
                putExtra("state"," ")
                putExtra("city","Bangalore")
                putExtra("resources"," ")
            }
            startActivity(intent)
        }
        button8!!.setOnClickListener{
            val intent = Intent(v.context, SearchResultsActivity::class.java).apply {
                putExtra("state"," ")
                putExtra("city","Hyderabad")
                putExtra("resources"," ")
            }
            startActivity(intent)
        }

        medicine!!.setOnClickListener {
            val intent = Intent(v.context, SearchResultsActivity::class.java).apply {
                putExtra("state"," ")
                putExtra("city"," ")
                putExtra("resources","medicine")
            }
            startActivity(intent)

        }
        beds!!.setOnClickListener {
            val intent = Intent(v.context, SearchResultsActivity::class.java).apply {
                putExtra("state"," ")
                putExtra("city"," ")
                putExtra("resources","beds")
            }
            startActivity(intent)

        }

        plasma!!.setOnClickListener {
            val intent = Intent(v.context, SearchResultsActivity::class.java).apply {
                putExtra("state"," ")
                putExtra("city"," ")
                putExtra("resources","plasma")
            }
            startActivity(intent)

        }

        icu!!.setOnClickListener {
            val intent = Intent(v.context, SearchResultsActivity::class.java).apply {
                putExtra("state"," ")
                putExtra("city"," ")
                putExtra("resources","icu")
            }
            startActivity(intent)

        }

        oxyzen!!.setOnClickListener {
            val intent = Intent(v.context, SearchResultsActivity::class.java).apply {
                putExtra("state"," ")
                putExtra("city"," ")
                putExtra("resources","oxyzen")
            }
            startActivity(intent)

        }

        catering!!.setOnClickListener {
            val intent = Intent(v.context, SearchResultsActivity::class.java).apply {
                putExtra("state"," ")
                putExtra("city"," ")
                putExtra("resources","catering")
            }
            startActivity(intent)

        }


        callbtn!!.setOnClickListener{
            val callIntent= Intent(Intent.ACTION_CALL)
            callIntent.setData(Uri.parse("tel:"+"1075"))
            startActivity(callIntent)
        }

        searchbtn!!.setOnClickListener{
            val intent = Intent(activity, SearchActivity::class.java)
            startActivity(intent)        }

        return v
    }
    private fun addToList(title:String,image:Int){
        titlesList.add(title)
        imagesList.add(image)


    }

    private fun postToList(){
            addToList("Jordan detects three cases of Indian COVID-19 variant, reports minister",R.drawable.imgslider1)
            addToList("Apply the law to accelerate Covaxin output: Legal experts say sufficient legal provisions to ask several private companies to make the vaccine",R.drawable.imageslider2)
        addToList("U.S. administers 243.5 million doses of COVID-19 vaccines, reports CDC",R.drawable.imageslider3)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}