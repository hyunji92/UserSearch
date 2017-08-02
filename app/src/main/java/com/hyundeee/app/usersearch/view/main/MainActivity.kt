package com.hyundeee.app.usersearch.view.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.hyundeee.app.usersearch.R
import com.hyundeee.app.usersearch.dto.SearchResponse
import com.hyundeee.app.usersearch.view.main.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainPresenter.View {

    @Inject
    lateinit var presenter: MainPresenter

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

    override fun onRefreshListView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDataLoaded(storeResponse: SearchResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
