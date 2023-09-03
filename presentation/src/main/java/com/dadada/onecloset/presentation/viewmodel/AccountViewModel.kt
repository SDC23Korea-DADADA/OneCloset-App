package com.dadada.onecloset.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dadada.onecloset.domain.model.AccountInfo
import com.dadada.onecloset.domain.usecase.AccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountUseCase: AccountUseCase
) : ViewModel() {

    val accountInfo = accountUseCase.getAccountInfo()


    fun signInGoogle(accountInfo: AccountInfo) = viewModelScope.launch {
        accountUseCase.signIn(accountInfo)
    }

    fun signOutGoogle() = viewModelScope.launch {
        accountUseCase.signOut()
    }
}