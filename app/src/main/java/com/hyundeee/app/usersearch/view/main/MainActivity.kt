package com.hyundeee.app.usersearch.view.main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.hyundeee.app.usersearch.R
import com.hyundeee.app.usersearch.dto.SearchResponse
import com.hyundeee.app.usersearch.dto.User
import com.hyundeee.app.usersearch.view.main.adapter.FragmentsAdapter
import com.hyundeee.app.usersearch.view.main.di.DaggerMainUserListComponent
import com.hyundeee.app.usersearch.view.main.di.MainUserListModule
import com.hyundeee.app.usersearch.view.main.fragment.MainFragment
import com.hyundeee.app.usersearch.view.main.fragment.adpter.UserListAdapter
import com.hyundeee.app.usersearch.view.main.presenter.MainPresenter
import io.reactivex.subjects.PublishSubject

import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainPresenter.View {
    @Inject
    lateinit var presenter: MainPresenter
    val subject: PublishSubject<String> = PublishSubject.create()

    val adpter: FragmentsAdapter by lazy { FragmentsAdapter(supportFragmentManager) }

    lateinit var likedUsers: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        subject.debounce(1000, TimeUnit.MILLISECONDS).subscribe { searchUser(it) }

        DaggerMainUserListComponent.builder()
                .mainUserListModule(MainUserListModule(this))
                .build()
                .inject(this)

        initView()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val searchView = menu?.findItem(R.id.search_bar)?.actionView as? SearchView

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(s: String): Boolean {
                Log.d("test", "searchView setOnQueryTextListener 1------")
                //s?.let { subject.onNext(it) }
                subject.onNext(s)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                Log.d("test", "searchView onQueryTextSubmit 2------")
                //presenter.getUserList(query as String)
                subject.onNext(query)
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("ResourceAsColor")
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_search -> {
                recyclerViewPager.scrollToPosition(0)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_like -> {
                Log.d("testtest", " mOnNavigationItemSelectedListener 2------")
                recyclerViewPager.smoothScrollToPosition(1)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    fun initView() {
        // RecyclerView setting
        recyclerViewPager.apply {
            setHasFixedSize(true)
            layoutManager = object : LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) {
                @Override
                override fun canScrollHorizontally(): Boolean {
                    return true
                }
            }
            adapter = adpter
        }


    }

    fun toast(message: String) = Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()

    override fun searchUser(searchWord: String) {
        Log.d("test", "search User 1------")
        if (searchWord.isNullOrBlank()) {

            toast("검색된 이미지가 없습니다")
            (adpter.fragmentCache[0] as MainFragment).userAdapter.items.clear()
            (adpter.fragmentCache[0] as MainFragment).userAdapter.notifyDataSetChanged()


        } else {
            Log.d("test", "search User 2------")
            presenter.getUserList(searchWord)

        }
    }

    override fun onRefreshListView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDataLoaded(storeResponse: SearchResponse) {

        Log.d("test", "onDataLoaded ------")
//        val searchListView: UserListAdapter = (adpter.fragmentCache[0] as MainFragment).userAdapter
        //val likeListView: UserListAdapter = (adpter.fragmentCache[1] as MainFragment).userAdapter
        (adpter.fragmentCache[0] as MainFragment).userAdapter.apply {
            type = UserListAdapter.Type.SEARCH
            items.clear()
            items.addAll(storeResponse.items)
            notifyDataSetChanged()
        }


//        (adpter.fragmentCache[1] as MainFragment).userAdapter.type = UserListAdapter.Type.LIKE
//        (adpter.fragmentCache[1] as MainFragment).userAdapter.items.clear()
//        Log.d("user like list test", "user like test 4  :  " + storeResponse.items.filter { it.isLike }.toList())
//        (adpter.fragmentCache[1] as MainFragment).userAdapter.items.addAll(storeResponse.items.filter { it.isLike }.toList())
//        (adpter.fragmentCache[1] as MainFragment).userAdapter.notifyDataSetChanged()

    }

    fun setLikeStatus(searchList: ArrayList<User>) {
        for (searchUser in searchList) {
            for (likedUser in likedUsers) {
                if (searchUser.login.equals(likedUser.login)) {
                    searchUser.isLike = true
                    break
                }
            }
        }
    }

    override fun onDataFailed() {
        Log.d("test", "onDataFailed ------")
        toast("리스트를 불러오는데 문제가 발생했습니다. 다시 시도해 주세요.")
        (adpter.fragmentCache[0] as MainFragment).userAdapter.apply {
            items.clear()
            notifyDataSetChanged()
        }
    }


    override fun onDataComplete() {
        Log.d("test", "onDataComplete ------")
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}


