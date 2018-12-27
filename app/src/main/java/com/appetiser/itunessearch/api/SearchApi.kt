package com.appetiser.itunessearch.api

import com.appetiser.itunessearch.data.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * List of endpoints to be requested in the API
 */

@JvmSuppressWildcards
interface SearchApi {

    // Auth
    @GET("search")
    fun search(@QueryMap params: Map<String, Any>): Call<SearchResponse>
}