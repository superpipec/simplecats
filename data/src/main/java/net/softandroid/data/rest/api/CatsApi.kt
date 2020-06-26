package net.softandroid.data.rest.api

import net.softandroid.data.rest.api.entities.GetCatsEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 *  STUB CATS API ENDPOINT
 */

const val LOAD_COUNT = 5

interface CatsApi {

    @GET("/v1/images/search")
    suspend fun getList(
        @Header(Headers.AUTH) authToken: String,
        @Query("page") page: Int?,
        @Query("limit") limit: Int? = LOAD_COUNT
    ): Response<List<GetCatsEntity>>
}