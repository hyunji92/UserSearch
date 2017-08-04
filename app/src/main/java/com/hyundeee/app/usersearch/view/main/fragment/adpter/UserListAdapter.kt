package com.hyundeee.app.usersearch.view.main.fragment.adpter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.hyundeee.app.usersearch.R
import com.hyundeee.app.usersearch.Yame
import com.hyundeee.app.usersearch.dto.User
import com.hyundeee.app.usersearch.view.main.holder.UserListViewHolder
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * Created by jeonghyeonji on 2017. 8. 4..
 */
class UserListAdapter(var c: Context, val items: ArrayList<User>) : RecyclerView.Adapter<UserListViewHolder>() {

    override fun onBindViewHolder(holder: UserListViewHolder?, position: Int) {
        val user = items[position]

        holder?.apply {

            Glide.with(holder.image.context)
                    .load(user.avatar_url)
                    .fitCenter()
                    .into(view.user_avatar_image)

            name.text = user.login
            repoUrl.text = user.repos_url

            itemView.setOnClickListener {
                Yame.subject.onNext(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UserListViewHolder {
        return UserListViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int = items.size
}