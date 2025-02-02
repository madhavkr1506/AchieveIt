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
import android.widget.TextView

class Update : Fragment() {

    private lateinit var reflectionNoteEdit: EditText
    private lateinit var editDate: EditText
    private lateinit var dbHelper: DB
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_update, container, false)
        val calendar = view.findViewById<CalendarView>(R.id.calendar);
        calendar.visibility = View.GONE;
        val reflectionNote = view.findViewById<TextView>(R.id.reflectionNote);
        reflectionNote.visibility = View.GONE;

        reflectionNoteEdit = view.findViewById<EditText>(R.id.reflectionNoteEdit);
        reflectionNoteEdit.visibility = View.GONE;

        editDate = view.findViewById(R.id.logDateEdit)
        dbHelper = DB(requireContext())

        val calendarButton = view.findViewById<ImageButton>(R.id.calendarButton)
        calendarButton.setOnClickListener {
            calendar.visibility = View.VISIBLE
        }

        calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val date = "$dayOfMonth/${month}/$year"
            editDate.setText(date)
            calendar.visibility = View.GONE
            reflectionNote.visibility = View.VISIBLE
            reflectionNoteEdit.visibility = View.VISIBLE

            dbHelper.onSendContent(date, this@Update)
        }

        val update = view.findViewById<Button>(R.id.updateDetails)
        update.setOnClickListener {
            val date = editDate.text.toString()
            val note = reflectionNoteEdit.text.toString()

            if (date.isNotEmpty() && note.isNotEmpty()) {
                dbHelper.onUpdate(date, note)
                dbHelper.onSendContent(date, this@Update)
            }

            editDate.text.clear();
            reflectionNoteEdit.text.clear();
        }
        return view;
    }

    fun getContent(content: String) {
        requireActivity().runOnUiThread {
            reflectionNoteEdit.setText(content)
        }
    }
}

