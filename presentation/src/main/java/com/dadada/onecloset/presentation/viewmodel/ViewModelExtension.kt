package com.dadada.onecloset.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dadada.onecloset.domain.model.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

fun <T> ViewModel.launchNetworkTask(networkState: MutableStateFlow<NetworkResult<T>>, task: suspend () -> NetworkResult<T>) {
    viewModelScope.launch {
        networkState.value = NetworkResult.Loading
        networkState.emit(task.invoke())
    }
}