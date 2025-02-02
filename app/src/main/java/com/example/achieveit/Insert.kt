package com.example.achieveit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.achieveit.R



class Insert : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_insert, container, false)

        val calendarView = view.findViewById<CalendarView>(R.id.calendar);
        calendarView.visibility = View.GONE

        val submit = view.findViewById<Button>(R.id.submit);
        val dbHelper = DB(requireContext());

        val logDateEdit = view.findViewById<EditText>(R.id.logDateEdit);
        val reflectionNoteEdit = view.findViewById<EditText>(R.id.reflectionNoteEdit);

        val calendarButton = view.findViewById<ImageButton>(R.id.calendarButton);


        calendarButton.setOnClickListener{
            calendarView.visibility = View.VISIBLE;
        }

        calendarView.setOnDateChangeListener{_,year,month,DayOfMonth ->
            val date = "$DayOfMonth/$month/$year";
            logDateEdit.setText(date);
            calendarView.visibility = View.GONE;

        }
        submit.setOnClickListener {
            val name = logDateEdit.text.toString();
            val contact = reflectionNoteEdit.text.toString();
            logDateEdit.text.clear();
            reflectionNoteEdit.text.clear();
            if (name.isNotEmpty() && contact.isNotEmpty()) {
                dbHelper.onInsert(name, contact);
            }

        }
        return view;
    }

}