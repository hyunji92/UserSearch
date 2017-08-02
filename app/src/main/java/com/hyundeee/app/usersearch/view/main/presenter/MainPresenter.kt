package com.hyundeee.app.usersearch.view.main.presenter

import com.hyundeee.app.usersearch.dto.SearchResponse
import com.hyundeee.app.usersearch.dto.User

/**
 * Created by jeonghyeonji on 2017. 8. 2..
 */
interface MainPresenter {
    fun getUserList(q: String)


    interface View {
        fun onRefreshListView()
        fun onDataLoaded(storeResponse: SearchResponse)
    }
}