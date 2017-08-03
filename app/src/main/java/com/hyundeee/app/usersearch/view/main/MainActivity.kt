package com.hyundeee.app.usersearch.view.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.hyundeee.app.usersearch.R
import com.hyundeee.app.usersearch.dto.SearchResponse
import com.hyundeee.app.usersearch.dto.User
import com.hyundeee.app.usersearch.view.main.adapter.FragmentsAdapter
import com.hyundeee.app.usersearch.view.main.di.MainUserListModule
import com.hyundeee.app.usersearch.view.main.di.DaggerMainUserListComponent
import com.hyundeee.app.usersearch.view.main.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import sun.util.locale.provider.LocaleProviderAdapter.getAdapter



class MainActivity : AppCompatActivity(), MainPresenter.View {

    @Inject
    lateinit var presenter: MainPresenter

    val items by lazy { ArrayList<User>() }
    val adpter by lazy { FragmentsAdapter(supportFragmentManager) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerMainUserListComponent.builder().mainUserListModule(MainUserListModule(this)).build().inject(this)

        initView()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_search -> {

                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_like -> {

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    fun initView(){
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter =  adpter
    }

    override fun onRefreshListView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDataLoaded(storeResponse: SearchResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
