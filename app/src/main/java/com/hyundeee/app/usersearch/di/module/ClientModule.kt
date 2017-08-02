
package com.hyundeee.app.usersearch.di.module

import com.hyundeee.app.usersearch.api.client.UserSearchClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by jeonghyeonji on 2017. 8. 2..
 */
@Module
class ClientModule{
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor())
                .addInterceptor(logging)
                .build()
    }

    @Provides
    fun provideUserSearchClient(): UserSearchClient {
        return UserSearchClient(provideOkHttpClient())
    }
}
