package com.example.achieveit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val insertFragment = Insert();
    private val listViewFragment = ListView();
    private val updateFragment = Update();
    private val deleteFragment = Delete();
    private val fm: FragmentManager = supportFragmentManager;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            showFragment(listViewFragment);
        }
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation);
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.addDetails -> {
                    showFragment(insertFragment);
                    true;
                }

                R.id.view -> {
                    showFragment(listViewFragment);
                    true;
                }

                R.id.updateDetails -> {
                    showFragment(updateFragment);
                    true;
                }

                R.id.deleteDetails -> {
                    showFragment(deleteFragment);
                    true;
                }

                else -> false;
            }
        }

    }
    private fun showFragment(fragment: Fragment) {
        val tm: FragmentTransaction = fm.beginTransaction();
        tm.replace(R.id.container, fragment);
        tm.commit();
    }
}