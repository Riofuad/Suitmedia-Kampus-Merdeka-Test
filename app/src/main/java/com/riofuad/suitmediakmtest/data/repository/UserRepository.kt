package com.riofuad.suitmediakmtest.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.riofuad.suitmediakmtest.data.UserPagingSource
import com.riofuad.suitmediakmtest.data.response.DataItem
import com.riofuad.suitmediakmtest.data.retrofit.ApiService

class UserRepository(private val apiService: ApiService) {
    fun getUser(): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = { UserPagingSource(apiService) }
        ).liveData
    }
}