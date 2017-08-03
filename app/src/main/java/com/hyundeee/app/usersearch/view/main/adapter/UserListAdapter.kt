package com.hyundeee.app.usersearch.view.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hyundeee.app.usersearch.R
import com.hyundeee.app.usersearch.dto.User
import com.hyundeee.app.usersearch.view.main.holder.UserListViewHolder

/**
 * Created by jeonghyeonji on 2017. 8. 4..
 */
class UserListAdapter(var c: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val userList = ArrayList<User>()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as? UserListViewHolder)?.bindData(userList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(c).inflate(R.layout.list_item, parent, false)
        return UserListViewHolder(v)
    }

    override fun getItemCount(): Int = userList.size
}