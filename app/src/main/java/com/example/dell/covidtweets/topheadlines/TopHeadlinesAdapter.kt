package com.example.dell.covidtweets.topheadlines

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dell.covidtweets.NewsActivity
import com.example.dell.covidtweets.R

class TopHeadlinesAdapter(
    topHeadlinesModelList: MutableList<TopHeadlinesModel>,
    newsActivity: NewsActivity
) : RecyclerView.Adapter<TopHeadlinesAdapter.ExampleViewHolder>() {
    private var context: Context?=null
    private var topHeadlinesModelList: List<TopHeadlinesModel?>? = null

    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title:TextView=itemView.findViewById(R.id.top_headline_title)
        val descp:TextView=itemView.findViewById(R.id.top_headline_descp)
        val source:TextView=itemView.findViewById(R.id.top_headline_source)


        }

    fun TopHeadlinesAdapter(topHeadlinesModelList: List<TopHeadlinesModel?>?, context: Context?) {
        this.topHeadlinesModelList =topHeadlinesModelList
        this.context=context
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.top_headlines_item_layout,parent,false)
        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        holder.title.setText(topHeadlinesModelList!!.get(position)!!.title)
        holder.descp.setText(topHeadlinesModelList!!.get(position)!!.descp)
        holder.source.setText(topHeadlinesModelList!!.get(position)!!.source)


    }

    override fun getItemCount() = topHeadlinesModelList!!.size
}