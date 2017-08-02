package com.hyundeee.app.usersearch.api.service

import com.hyundeee.app.usersearch.dto.SearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by jeonghyeonji on 2017. 8. 2..
 */
public interface GitHubService {

    @GET("/search/users")
    fun getUserList(
            @Query("q") searchword: String,
            @Query("sort") sort: String,
            @Query("order") order:String
    ): Observable<SearchResponse>
}