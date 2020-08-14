package com.jkcq.base.net

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.jkcq.base.app.BaseApp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 *  Created by BeyondWorlds
 *  on 2020/7/23
 */
abstract class BaseRetrofitHelper {
    private val cookieJar by lazy {
        PersistentCookieJar(
            SetCookieCache(), SharedPrefsCookiePersistor(
                BaseApp.sApplicaton
            )
        )
    }

    companion object {
        val TIME_OUT = 10L
        val IS_HTTP_DEBUG = true
        val BASE_URL = "https://test.gateway.spinning.fitalent.com.cn/app/"
    }

    /**
     * 不同的library可能需要传入不同的ApiService
     */
    fun <S> getService(service: Class<S>, retrofit: Retrofit): S {
        return retrofit.create(service)
    }


    protected val okHttpClient: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            if (IS_HTTP_DEBUG) {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
            }
            builder
//                .cookieJar(cookieJar)
                .addInterceptor(httpLoggingInterceptor)
//                .addInterceptor(CookieInterceptor())
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            //             .retryOnConnectionFailure(true) //连接失败重连
            return builder.build()
        }

}