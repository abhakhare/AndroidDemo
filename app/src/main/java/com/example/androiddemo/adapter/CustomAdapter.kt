package com.example.androiddemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddemo.Model
import com.example.androiddemo.R
import java.util.*
import kotlin.collections.ArrayList





class CustomAdapter(var list: ArrayList<Model>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    var tempArrayList = ArrayList(list)
    // exampleListFull . exampleList

  /*  init {
       FilterList =list as ArrayList<Model>
    }*/

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(model.image)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = model.text
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
    }
    fun filter(text: String?) {


        //Our Search text
        val text = text!!.toLowerCase(Locale.getDefault())


        //Here We Clear Both ArrayList because We update according to Search query.
        list.clear()



        if (text.length == 0) {

            /*If Search query is Empty than we add all temp data into our main ArrayList

            We store Value in temp in Starting of Program.

            */

            list.addAll(tempArrayList)



        } else {


            for (i in 0..tempArrayList.size - 1) {

                /*
                If our Search query is not empty than we Check Our search keyword in Temp ArrayList.
                if our Search Keyword in Temp ArrayList than we add to our Main ArrayList
                */

                if (tempArrayList.get(i).text!!.toLowerCase(Locale.getDefault()).contains(text)) {

                    list.add(tempArrayList.get(i))



                }

            }
        }

        //This is to notify that data change in Adapter and Reflect the changes.
        notifyDataSetChanged()


    }

}

