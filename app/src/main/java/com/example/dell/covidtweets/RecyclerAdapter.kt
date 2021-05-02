package com.example.dell.covidtweets

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.coroutineContext
class RecyclerAdapter(private var titles: List<String>, private var addresses: List<String>, private var phonenos:List<String>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
        inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val itemTitle:TextView=itemView.findViewById(R.id.infotv)
            val address:TextView=itemView.findViewById(R.id.addresstv)
            val phoneno:TextView=itemView.findViewById(R.id.phonenotv)
            val time:TextView=itemView.findViewById(R.id.timetv)
            val call:ImageView=itemView.findViewById(R.id.imageView5)

            init {
                itemView.setOnClickListener{v: View ->
                    val position:Int=adapterPosition
                    Toast.makeText(itemView.context, "you clicked an item # ${position+1}", Toast.LENGTH_SHORT).show()

                }

            }



        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.item_data,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text=titles[position]
        holder.address.text=addresses[position]
        holder.phoneno.text=phonenos[position]
//      holder.time.text=currenttime[position]
        holder.call.setOnClickListener{
            val callIntent= Intent(Intent.ACTION_CALL)
            callIntent.setData(Uri.parse("tel:" + "1075"))
        }
    }

    override fun getItemCount(): Int {
        return titles.size
    }

}