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
    var type: Type = Type.SEARCH


    var userLikeList = listOf<User>()

    enum class Type{
        SEARCH,
        LIKE
    }

    override fun onBindViewHolder(holder: UserListViewHolder?, position: Int) {
        holder?.run {
            when (type) {
                Type.SEARCH -> items[position].let {
                    bindView(it)
                    setLikeState(it.isLike)
                    Log.d("user like list test", "user like test  1 :  " + userLikeList.toString())
                }
                Type.LIKE -> userLikeList[position].let {
                    bindView(it)
                    setLikeState(it.isLike)
                    Log.d("user like list test", "user like test  2 :  " + userLikeList.toString())
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UserListViewHolder {
        return UserListViewHolder(LayoutInflater.from(c).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int = items.filter { type == Type.SEARCH || it.isLike }.size

    fun setItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

    inner class UserListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun setLikeState(isLike: Boolean){
            Log.d("test like", "like state  :  " + isLike)
            view.like_button.isSelected = isLike
        }

        fun bindView(userListItem: User) {


            with(view){
                user_name.text = userListItem.login
            }
            view.user_name.text = userListItem.login
            view.user_repo_url.text = userListItem.repos_url
            Glide.with(itemView.context)
                    .load(userListItem.avatar_url)
                    .fitCenter()
                    .into(itemView.user_avatar_image)

            view.setOnClickListener { view -> onItemClickListener.onItemClick(view, adapterPosition) }
        }

    }
}