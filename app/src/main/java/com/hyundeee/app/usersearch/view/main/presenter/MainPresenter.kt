package com.hyundeee.app.usersearch.view.main.presenter

import com.hyundeee.app.usersearch.dto.SearchResponse

/**
 * Created by jeonghyeonji on 2017. 8. 2..
 */
interface MainPresenter {
    fun getUserList(q: String)

    interface View {
        fun onDataLoaded(storeResponse: SearchResponse)
        fun onDataFailed()
        fun onDataComplete()
        fun searchUser(searchWord: String)
    }
}