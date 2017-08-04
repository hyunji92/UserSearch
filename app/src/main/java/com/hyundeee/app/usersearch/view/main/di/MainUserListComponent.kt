package com.hyundeee.app.usersearch.view.main.di

import com.hyundeee.app.usersearch.view.main.MainActivity
import com.hyundeee.app.usersearch.view.main.fragment.MainFragment
import dagger.Component

/**
 * Created by jeonghyeonji on 2017. 8. 3..
 */
@Component(modules = arrayOf(MainUserListModule::class))
interface MainUserListComponent {
    fun inject(Fragment: MainFragment)
    fun inject(Activity: MainActivity)
}