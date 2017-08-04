package com.hyundeee.app.usersearch.dto

/**
 * Created by jeonghyeonji on 2017. 8. 2..
 */
data class User (
        val login: String,
        val id: Int,
        val avatar_url: String,
        val gravatar_id: String,
        val url: String,
        val html_url: String,
        val followers_url: String,
        val subscriptions_url: String,
        val organizations_url: String,
        val repos_url: String,
        val received_events_url: String,
        val type: String,
        val score: Double,
        var isLike: Boolean = false
)


