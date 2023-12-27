package com.riofuad.suitmediakmtest.utils

import android.content.Context
import com.riofuad.suitmediakmtest.data.repository.UserRepository
import com.riofuad.suitmediakmtest.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository(apiService)
    }
}