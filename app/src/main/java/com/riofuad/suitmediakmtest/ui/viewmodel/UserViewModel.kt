package com.riofuad.suitmediakmtest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.riofuad.suitmediakmtest.data.repository.UserRepository
import com.riofuad.suitmediakmtest.data.response.DataItem

class UserViewModel(userRepository: UserRepository) : ViewModel() {
    val user: LiveData<PagingData<DataItem>> = userRepository.getUser().cachedIn(viewModelScope)
}