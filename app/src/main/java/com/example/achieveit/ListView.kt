package com.example.achieveit

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import com.example.achieveit.R
import com.facebook.shimmer.ShimmerFrameLayout


class ListView : Fragment() {

    private lateinit var logDate: MutableList<String>
    private lateinit var reflectionNote: MutableList<String>
    private lateinit var customAdapter: CustomAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View =  inflater.inflate(R.layout.fragment_list_view, container, false)

        val shimmerContainer = view.findViewById<ShimmerFrameLayout>(R.id.shimmerContainer);
        val listView = view.findViewById<android.widget.ListView>(R.id.listview);
        listView.visibility = View.INVISIBLE;
        shimmerContainer.startShimmer();

        logDate = mutableListOf();
        reflectionNote = mutableListOf();

        val dbHelper = DB(requireContext());
        val cursor : Cursor = dbHelper.onRead();

        if(cursor.moveToFirst()){
            do{
                logDate.add(cursor.getString(0));
                reflectionNote.add(cursor.getString(1));
            }while (cursor.moveToNext())
        }

        customAdapter = CustomAdapter(requireContext(),logDate,reflectionNote);
        listView.adapter = customAdapter;

        shimmerContainer.postDelayed({
            shimmerContainer.stopShimmer();
            listView.visibility = View.VISIBLE;
            shimmerContainer.visibility = View.INVISIBLE;
        },1000);

        val searchBar = view.findViewById<SearchView>(R.id.SearchBar);
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let{
                    customAdapter.filter(query);
                }
                return true;
            }

            override fun onQueryTextChange(query: String?): Boolean {
                query?.let {
                    customAdapter.filter(query);
                }
                return true;
            }
        })


        return view;
    }

}