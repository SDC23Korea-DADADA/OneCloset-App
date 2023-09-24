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
    var showToast by remember { mutableStateOf(false) }
    if(isLoading) {
        GalaxyLoadingView()
    }

    if(showToast) {
        ShowToast(text = "네트워크 연결을 확인해주세요.")
    }

    LaunchedEffect(state) {
        Log.d(TAG, "NetworkResultHandler: ${state}")
        when (state) {
            is NetworkResult.Error -> {
                isLoading = false
                showToast = true
            } // Handle the error here.
            NetworkResult.Idle -> {
                isLoading = false
                showToast = false
            } // Handle the idle state here.
            NetworkResult.Loading -> {
                isLoading = true
                showToast = false
            }
            is NetworkResult.Success -> {
                isLoading = false
                action(state.data)
                showToast = false
            }

            else -> {}
        }
    }
}