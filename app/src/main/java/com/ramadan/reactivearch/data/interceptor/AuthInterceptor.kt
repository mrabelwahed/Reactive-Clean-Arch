package com.ramadan.reactivearch.data.interceptor

import android.content.Context
import com.ramadan.reactivearch.R
import com.ramadan.reactivearch.core.common.AppConst.CLIENT_ID
import com.ramadan.reactivearch.core.common.AppConst.CLIENT_SECRET
import com.ramadan.reactivearch.core.common.AppConst.CURRENT_VERSION
import com.ramadan.reactivearch.core.common.AppConst.DEFAULT_QUERY
import com.ramadan.reactivearch.core.common.AppConst.QUERY
import com.ramadan.reactivearch.core.common.AppConst.VERSION
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val appContext : Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request =  chain.request()
       val originalUrl =  request.url()
       val modifiedUrl =  originalUrl.newBuilder()
            .addQueryParameter(CLIENT_ID ,appContext.getString(R.string.foursquare_cleint_id))
            .addQueryParameter(CLIENT_SECRET,appContext.getString(R.string.foursquare_client_secret))
            .addQueryParameter(VERSION, CURRENT_VERSION)
            .addQueryParameter(QUERY, DEFAULT_QUERY)
            .build()

       val modifiedRequest =  request.newBuilder().url(modifiedUrl).build()
        return chain.proceed(modifiedRequest)
    }
}