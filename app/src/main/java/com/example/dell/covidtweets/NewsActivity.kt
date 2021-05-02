package com.example.dell.covidtweets

import android.content.IntentSender
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dell.covidtweets.topheadlines.TopHeadlinesAdapter
import com.example.dell.covidtweets.topheadlines.TopHeadlinesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.*


const val BASE_URL ="https://api.currentsapi.services/"

class NewsActivity : AppCompatActivity() {

    lateinit var countDownTimer: CountDownTimer
    private var titlesList= mutableListOf<String>()
    private var descList= mutableListOf<String>()
    private var imagesList= mutableListOf<String>()
    private var linksList= mutableListOf<String>()
    var rv_recyclerView:RecyclerView?=null
    var v_blackScreen:View?=null
    var progressBar:ProgressBar?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        rv_recyclerView=findViewById<View>(R.id.rv_recyclerView) as RecyclerView
        v_blackScreen=findViewById<View>(R.id.v_blackScreen)
        progressBar=findViewById<View>(R.id.progressBar) as ProgressBar

        makeAPIRequest()

    }

    private fun fadeInFromBlack(){
        v_blackScreen!!.animate().apply {
            alpha(0f)
            duration=3000

        }.start()
    }

    private fun setUpRecyclerView(){
        rv_recyclerView!!.layoutManager= LinearLayoutManager(applicationContext)
        rv_recyclerView!!.adapter= NewsAdapter(titlesList,descList,imagesList,linksList)
    }

    private  fun addToList(title:String,descp:String,image:String,link:String){
        titlesList.add(title)
        descList.add(descp)
        imagesList.add(image)
        linksList.add(link)
    }

    private fun makeAPIRequest() {
        progressBar!!.visibility=View.VISIBLE

        val api= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIRequest::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getNews()

                for(article in response.news){
                    Log.i("NewsActivity","Result = $article")
                    addToList(article.title,article.description,article.image,article.url)
                }
                withContext(Dispatchers.Main){
                    setUpRecyclerView()
                    fadeInFromBlack()
                    progressBar!!.visibility=View.GONE

                }

            }catch (e: Exception){
                Log.e("NewsActivity",e.toString())

                withContext(Dispatchers.Main){
                    attemptRequestAgain()
                }
            }
        }

    }

    private fun attemptRequestAgain() {
        countDownTimer= object :CountDownTimer(5*1000,1000){
            override fun onTick(p0: Long) {
                Log.i("NewsActivity","Could not retrieve data.... Trying again in ${p0/1000} seconds.")

            }

            override fun onFinish() {

                makeAPIRequest()
                countDownTimer.cancel()
            }

        }
        countDownTimer.start()
    }
}