package com.hyundeee.app.usersearch.view.main.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.hyundeee.app.usersearch.dto.User
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.hyundeee.app.usersearch.R
import com.hyundeee.app.usersearch.YameTest
import com.hyundeee.app.usersearch.view.main.fragment.adpter.UserListAdapter
import com.hyundeee.app.usersearch.view.main.fragment.listener.OnItemClickListener
import kotlinx.android.synthetic.main.list_item.view.*


/**
 * Created by jeonghyeonji on 2017. 8. 3..
 */
class MainFragment : Fragment() {

    private var index: Int = 0
    val items by lazy { ArrayList<User>() }
    val userAdapter by lazy { UserListAdapter(context, items) }
    val clickListener = object : OnItemClickListener {

        override fun onItemClick(view: View, position: Int) {
            Log.d("user like list test", "user like list state   :  " + userAdapter.items[position].isLike)
            userAdapter.items[position].run {
                if (!isLike) {
                    isLike = true
                    view.like_button.isSelected = isLike
                    userAdapter.userLikeList = items.filter { it.isLike }.toList()
                    Log.d("user like list test", "user like test 3  :  " + userAdapter.userLikeList.toString())
                }
                YameTest.testSubject.onNext(userAdapter.userLikeList)
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
        return rv
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt("Index", index)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
    }

}