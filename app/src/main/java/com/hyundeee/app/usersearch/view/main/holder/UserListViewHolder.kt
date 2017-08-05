package com.hyundeee.app.usersearch.view.main.holder


/**
 * Created by jeonghyeonji on 2017. 8. 4..
 */
//class UserListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
//
//    var userLikeList = ArrayList<String>()
//
//    fun bindView(userListItem: User){
//
//        view.user_name.text = userListItem.login
//        view.user_repo_url.text = userListItem.repos_url
//        Glide.with(itemView.context)
//                .load(userListItem.avatar_url)
//                .fitCenter()
//                .into(itemView.user_avatar_image)
//
//        view.setOnClickListener {
//            Log.d("user like list test", "user like list position   :  " + adapterPosition)
//            userListItem.isLike = true
//            if (userListItem.isLike) {
//                view.like_button.setBackgroundResource(R.drawable.ic_like_on)
//                userLikeList.add(userListItem.login)
//            }
//        }
//        Log.d("user like list test", "user like list test   :  " + userLikeList.toString())
//    }
//
//}