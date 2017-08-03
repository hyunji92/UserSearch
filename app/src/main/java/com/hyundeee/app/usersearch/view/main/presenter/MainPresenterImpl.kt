package com.hyundeee.app.usersearch.view.main.presenter

import android.support.design.widget.BottomNavigationView
import com.hyundeee.app.usersearch.R
import com.hyundeee.app.usersearch.api.client.UserSearchClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * Created by jeonghyeonji on 2017. 8. 2..
 */
class MainPresenterImpl @Inject constructor(val view:MainPresenter.View, val client: UserSearchClient): MainPresenter{
    override fun getUserList(q: String) {
        client.githubService.getUserList(q,"repositories","desc")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { view.onDataLoaded(it) }
    }

}