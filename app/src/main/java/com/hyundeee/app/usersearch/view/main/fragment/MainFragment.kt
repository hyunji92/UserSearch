package com.hyundeee.app.usersearch.view.main.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.hyundeee.app.usersearch.dto.SearchResponse
import com.hyundeee.app.usersearch.dto.User
import com.hyundeee.app.usersearch.view.main.presenter.MainPresenter
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.hyundeee.app.usersearch.R
import com.hyundeee.app.usersearch.view.main.adapter.FragmentsAdapter
import com.hyundeee.app.usersearch.view.main.di.DaggerMainUserListComponent
import com.hyundeee.app.usersearch.view.main.di.MainUserListModule
import com.hyundeee.app.usersearch.view.main.fragment.adpter.UserListAdapter
import com.hyundeee.app.usersearch.view.main.fragment.listener.OnItemClickListener
import kotlinx.android.synthetic.main.list_item.view.*


/**
 * Created by jeonghyeonji on 2017. 8. 3..
 */
class MainFragment : Fragment(), MainPresenter.View {

    private var index: Int = 0

    @Inject
    lateinit var presenter: MainPresenter

    val adpter: FragmentsAdapter by lazy { FragmentsAdapter(activity.supportFragmentManager) }
    val subject: PublishSubject<String> = PublishSubject.create()
    val items by lazy { ArrayList<User>() }

    val userAdapter by lazy { UserListAdapter(context, items) }

    val clickListener = object : OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {

            Log.d("user like list test", "user like list position   :  " + position)
            Log.d("user like list test", "user like list state   :  " + userAdapter.items[position].isLike)
            userAdapter.items[position].run {
                if (!isLike) {
                    isLike = true
                    view.like_button.isSelected = isLike
                    userAdapter.userLikeList = items.filter { it.isLike }.toList()
                    Log.d("user like list test", "user like test 3  :  " + userAdapter.userLikeList.toString())

                }
            }
        }

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val rv = inflater?.inflate(R.layout.fragment_list, container, false) as RecyclerView
        rv.apply {
            val linearLayout = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            layoutManager = linearLayout
            adapter = userAdapter

            userAdapter.setItemClickListener(clickListener)
        }

        DaggerMainUserListComponent.builder()
                .mainUserListModule(MainUserListModule(this))
                .build()
                .inject(this)
        return rv
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt("Index", index)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onRefreshListView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDataLoaded(storeResponse: SearchResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDataFailed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDataComplete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun searchUser(searchWord: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}