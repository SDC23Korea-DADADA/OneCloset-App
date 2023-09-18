package com.dadada.onecloset.presentation.ui.utils

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.presentation.ui.common.GalaxyLoadingView

private const val TAG = "NetworkResultHandler"
@Composable
fun <T> NetworkResultHandler(state: NetworkResult<T>, action: (data: T) -> Unit) {
    var isLoading by remember { mutableStateOf(false) }
    if(isLoading) {
        GalaxyLoadingView()
    }
    
    LaunchedEffect(state) {
        if(state is NetworkResult.Idle) return@LaunchedEffect
        when (state) {
            is NetworkResult.Error -> { } // Handle the error here.
            NetworkResult.Idle -> { } // Handle the idle state here.
            NetworkResult.Loading -> { isLoading = true }
            is NetworkResult.Success -> {
                isLoading = false
                action(state.data)
            }
        }
    }
}