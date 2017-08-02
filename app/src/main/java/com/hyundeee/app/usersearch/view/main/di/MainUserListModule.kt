package com.hyundeee.app.usersearch.view.main.di

import com.hyundeee.app.usersearch.di.module.ClientModule
import com.hyundeee.app.usersearch.view.main.presenter.MainPresenter
import com.hyundeee.app.usersearch.view.main.presenter.MainPresenterImpl
import dagger.Module
import dagger.Provides

/**
 * Created by jeonghyeonji on 2017. 8. 3..
 */
@Module(includes = arrayOf(ClientModule::class))
class MainUserListModule(val view: MainPresenter.View) {

    @Provides
    fun provideMainPresenter(presenter: MainPresenterImpl): MainPresenter {
        return presenter
    }
    @Provides
    fun provideMainView(): MainPresenter.View {
        return view
    }
}