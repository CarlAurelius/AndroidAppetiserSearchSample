package com.appetiser.itunessearch.manager

import android.content.Context
import com.appetiser.itunessearch.R
import com.appetiser.itunessearch.api.SearchApi
import com.appetiser.itunessearch.data.SearchResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Class use to handle network request and a setup for retrofit
 **/
class AppRequestManager(var context: Context) {

    var service: SearchApi

    init {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.connectTimeout(40, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)


        val retrofit = Retrofit.Builder()
            .baseUrl(context.getString(R.string.base_url_live))
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(SearchApi::class.java)
    }
}