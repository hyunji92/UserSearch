package com.hyundeee.app.usersearch.view.main

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.hyundeee.app.usersearch.R
import com.hyundeee.app.usersearch.dto.SearchResponse
import com.hyundeee.app.usersearch.dto.User
import com.hyundeee.app.usersearch.view.main.fragment.adpter.FragmentsAdapter
import com.hyundeee.app.usersearch.view.main.adapter.UserListAdapter
import com.hyundeee.app.usersearch.view.main.di.MainUserListModule
import com.hyundeee.app.usersearch.view.main.di.DaggerMainUserListComponent
import com.hyundeee.app.usersearch.view.main.presenter.MainPresenter
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainPresenter.View {

    @Inject
    lateinit var presenter: MainPresenter

    val subject: PublishSubject<String> = PublishSubject.create()

    val items by lazy { ArrayList<User>() }
    val adpter by lazy { FragmentsAdapter(supportFragmentManager) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        subject
                .subscribe { searchUser(it) }
                .apply { dispose() }

        DaggerMainUserListComponent.builder().mainUserListModule(MainUserListModule(this)).build().inject(this)

        initView()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val searchView = menu?.findItem(R.id.search_bar)?.actionView as? SearchView

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(s: String?): Boolean {

                //s?.let { subject.onNext(it) }
                subject.onNext(s as String)
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {

                //presenter.getUserList(query as String)
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
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

    fun initView() {
        // RecyclerView setting
        recyclerView.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            layoutManager = linearLayout
            clearOnScrollListeners()
        }

        recyclerView.adapter = adpter

    }

    fun toast(message: String) = Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()

    override fun searchUser(searchWord: String) {
        if (searchWord.isNullOrBlank()) {

            toast("검색된 이미지가 없습니다")
            (recyclerView.adapter as UserListAdapter).userList.clear()
            (recyclerView.adapter as UserListAdapter).notifyDataSetChanged()

        } else {
            presenter.getUserList(searchWord)

            /* imageRestClient.client.getImage(apiKey, searchWord)
                     .subscribeOn(Schedulers.io())
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribe(
                             */

        }
    }

    override fun hideKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onRefreshListView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDataLoaded(storeResponse: SearchResponse) {
         /*subscribe(
                {
                    // onNext
                    (recyclerView.adapter as ImageAdapter).imageList.clear()
                    (recyclerView.adapter as ImageAdapter).imageList.addAll(it.channel.item)
                    (recyclerView.adapter as ImageAdapter).notifyDataSetChanged()

                },
                {
                    Log.d("Test", "onError $it")
                    toast("이미지를 불러오는데 문제가 발생했습니다. 다시 시도해 주세요.")
                    (recyclerView.adapter as ImageAdapter).imageList.clear()
                    (recyclerView.adapter as ImageAdapter).notifyDataSetChanged()

                },
                {
                    //onComplete
                    hideKeyBoard()

                })*/
    }
}


