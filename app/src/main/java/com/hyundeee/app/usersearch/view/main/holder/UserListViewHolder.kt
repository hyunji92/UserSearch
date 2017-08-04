package com.hyundeee.app.usersearch.view.main.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.hyundeee.app.usersearch.R
import com.hyundeee.app.usersearch.Yame
import com.hyundeee.app.usersearch.dto.User
import kotlinx.android.synthetic.main.list_item.*


/**
 * Created by jeonghyeonji on 2017. 8. 4..
 */
class UserListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val image: ImageView = view.findViewById(R.id.user_avatar_image)
    val name: TextView = view.findViewById(R.id.user_name)
    val repoUrl: TextView = view.findViewById(R.id.user_repo_url)

}