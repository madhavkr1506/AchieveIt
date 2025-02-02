package com.example.achieveit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.ImageButton
import com.example.achieveit.R

class Delete : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_delete, container, false)

        val calendar = view.findViewById<CalendarView>(R.id.calendar);
        calendar.visibility = View.GONE;

        val calendarButton = view.findViewById<ImageButton>(R.id.calendarButton);
        calendarButton.setOnClickListener{
            calendar.visibility = View.VISIBLE;
        }

        val editDate = view.findViewById<EditText>(R.id.logDateEdit);

        calendar.setOnDateChangeListener{_,year, month, DayOfMonth ->
            val date = "$DayOfMonth/$month/$year";
            editDate.setText(date);
            calendar.visibility = View.GONE;
        }

        val dbHelper = DB(requireContext());
        val delete = view.findViewById<Button>(R.id.deleteDetails);
        delete.setOnClickListener{
            val date = editDate.text.toString();
            editDate.text.clear();
            dbHelper.onDelete(date);
        }
        return view;
    }
}