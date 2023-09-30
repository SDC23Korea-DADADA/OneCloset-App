package com.dadada.onecloset.presentation.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.common.GalaxyLoadingView
import com.dadada.onecloset.presentation.ui.common.LoadingView
import com.dadada.onecloset.presentation.viewmodel.MainViewModel

private const val TAG = "NetworkResultHandler"

@Composable
fun <T> NetworkResultHandler(
    state: NetworkResult<T>,
    loadingType: String = "",
    mainViewModel: MainViewModel,
    action: (data: T) -> Unit
) {
    var showToast by remember { mutableStateOf(false) }

    if (showToast) {
        ShowToast(text = "네트워크 연결을 확인해주세요.")
    }

    LaunchedEffect(state) {
        when (state) {
            is NetworkResult.Error -> {
                mainViewModel.setLoadingState(false, loadingType)
                showToast = true
            } // Handle the error here.
            NetworkResult.Idle -> {
                mainViewModel.setLoadingState(false, loadingType)
                showToast = false
            } // Handle the idle state here.
            NetworkResult.Loading -> {
                mainViewModel.setLoadingState(true, loadingType)
                showToast = false
            }

            is NetworkResult.Success -> {
                mainViewModel.setLoadingState(false, loadingType)
                action(state.data)
                showToast = false
            }

            else -> {}
        }
    }
}