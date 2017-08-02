package com.hyundeee.app.usersearch.view.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.hyundeee.app.usersearch.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_search -> {
                message.setText(R.string.search)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_like -> {
                message.setText(R.string.like)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
