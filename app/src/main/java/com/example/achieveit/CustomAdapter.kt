
package com.example.achieveit
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.achieveit.R

class CustomAdapter(context: Context,private val logDate : MutableList<String>, private val reflectionNote: MutableList<String> ) : ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,logDate) {

    private var dateList : MutableList<String> = ArrayList(logDate);
    private var contentList : MutableList<String> = ArrayList(reflectionNote);


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context);
        val view : View = convertView ?: inflater.inflate(R.layout.row_item,parent,false);

        val dateItem = view.findViewById<TextView>(R.id.date);
        val contentItem = view.findViewById<TextView>(R.id.content);

        dateItem.text = dateList[position];
        contentItem.text = contentList[position];

        return view;

    }

    override fun getCount(): Int {
        return dateList.size;
    }

    fun filter(query : String){
        if(query.isEmpty()){
            dateList = ArrayList(logDate);
            contentList = ArrayList(reflectionNote);
        }
        else{
            dateList = ArrayList();
            contentList = ArrayList();

            for(i in reflectionNote.indices){
                if(reflectionNote[i].lowercase().contains(query.lowercase(),true)){
                    dateList.add(logDate[i]);
                    contentList.add(reflectionNote[i]);
                }
            }
        }

        notifyDataSetChanged();
    }
}