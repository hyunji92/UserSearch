package com.hyundeee.app.usersearch

import com.hyundeee.app.usersearch.dto.User
import io.reactivex.subjects.PublishSubject

/**
 * Created by jeonghyeonji on 2017. 8. 4..
 */
class Yame {

    companion object {
        val subject: PublishSubject<User> = PublishSubject.create()
    }
}