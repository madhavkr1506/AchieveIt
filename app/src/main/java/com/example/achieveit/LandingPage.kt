package com.example.achieveit


import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.achieveit.MainActivity
import com.example.achieveit.R

class LandingPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_landing_page)

        val cardView = findViewById<CardView>(R.id.cardView);
        val animation : Animation = android.view.animation.AnimationUtils.loadAnimation(this,R.anim.zoomanimation);
        cardView.startAnimation(animation);


        val register = findViewById<Button>(R.id.register);
        val name = findViewById<EditText>(R.id.editName);
        val email = findViewById<EditText>(R.id.emailEdit);
        val contact = findViewById<EditText>(R.id.editContact);

        register.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java));
        }
    }
}