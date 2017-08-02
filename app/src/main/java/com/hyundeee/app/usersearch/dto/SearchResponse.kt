package com.hyundeee.app.usersearch.dto

import java.util.*

/**
 * Created by jeonghyeonji on 2017. 8. 2..
 */
data class SearchResponse(
        val total_count: Int,
        val incomplete_results: Boolean,
        val items: ArrayList<User>
)
