package com.example.dell.covidtweets

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dell.covidtweets.topheadlines.TopHeadlinesAdapter
import com.example.dell.covidtweets.topheadlines.TopHeadlinesModel
import java.util.*


class NewsActivity : AppCompatActivity() {
    private var topheadlinesRecyclerView: RecyclerView? = null
    private var topHeadlinesAdapter: TopHeadlinesAdapter? = null
    private val topHeadlinesModelList: MutableList<TopHeadlinesModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        topheadlinesRecyclerView = findViewById(R.id.top_headlines_recyclerview)
        topHeadlinesModelList.add(TopHeadlinesModel("News1", "Absemtee Ballot", "Absentee Ballot"))
        topHeadlinesModelList.add(TopHeadlinesModel("News1", "Absemtee Ballot", "Absentee Ballot"))
        topHeadlinesModelList.add(TopHeadlinesModel("News1", "Absemtee Ballot", "Absentee Ballot"))
        topHeadlinesModelList.add(TopHeadlinesModel("News1", "Absemtee Ballot", "Absentee Ballot"))
        topHeadlinesModelList.add(TopHeadlinesModel("News1", "Absemtee Ballot", "Absentee Ballot"))
        topHeadlinesModelList.add(TopHeadlinesModel("News1", "Absemtee Ballot", "Absentee Ballot"))
        topHeadlinesModelList.add(TopHeadlinesModel("News1", "Absemtee Ballot", "Absentee Ballot"))

        topHeadlinesAdapter = TopHeadlinesAdapter(topHeadlinesModelList, this@NewsActivity)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL)
        topheadlinesRecyclerView!!.setLayoutManager(layoutManager)
        topheadlinesRecyclerView!!.setAdapter(topHeadlinesAdapter)
        topHeadlinesAdapter!!.notifyDataSetChanged()
    }
}