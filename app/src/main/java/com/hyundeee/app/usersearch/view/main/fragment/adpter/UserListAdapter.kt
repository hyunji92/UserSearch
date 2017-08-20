package com.hyundeee.app.usersearch.view.main.fragment.adpter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.hyundeee.app.usersearch.R
import com.hyundeee.app.usersearch.dto.User
import com.hyundeee.app.usersearch.view.main.fragment.listener.OnItemClickListener
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * Created by jeonghyeonji on 2017. 8. 4..
 */
class UserListAdapter(var c: Context, val items: ArrayList<User>) : RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {

    lateinit var onItemClickListener: OnItemClickListener
    var userLikeList = listOf<User>()

    override fun onBindViewHolder(holder: UserListViewHolder?, position: Int) {
        holder?.run {
            items[position].let {
                bindView(it)
                setLikeState(it.isLike)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UserListViewHolder {
        return UserListViewHolder(LayoutInflater.from(c).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int = items.size

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    inner class UserListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun setLikeState(isLike: Boolean) {
            Log.d("Set like", "like state  :  " + isLike)
            view.like_button.isSelected = isLike
        }

        fun bindView(userListItem: User) {
            with(view) {
                user_name.text = userListItem.login
                user_name.text = userListItem.login
                user_repo_url.text = userListItem.repos_url

                Glide.with(context)
                        .load(userListItem.avatar_url)
                        .fitCenter()
                        .into(user_avatar_image)

                setOnClickListener { view -> onItemClickListener.onItemClick(view, adapterPosition) }
            }
        }
    }
}