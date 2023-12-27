package com.riofuad.suitmediakmtest.data.retrofit

import com.riofuad.suitmediakmtest.data.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun users(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): UserResponse
}