package com.hyundeee.app.usersearch

import com.hyundeee.app.usersearch.dto.User
import io.reactivex.subjects.PublishSubject

/**
 * Created by jeonghyeonji on 2017. 8. 8..
 */
class YameTest {
    companion object {
        val testSubject: PublishSubject<List<User>> = PublishSubject.create()
    }
}