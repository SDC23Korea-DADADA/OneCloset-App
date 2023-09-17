package com.dadada.onecloset.presentation.viewmodel.account

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dadada.onecloset.domain.model.AccountInfo
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.model.Token
import com.dadada.onecloset.domain.usecase.account.AccountUseCase
import com.dadada.onecloset.domain.usecase.account.LogInKaKaoUseCase
import com.dadada.onecloset.presentation.viewmodel.launchNetworkTask
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "Account"

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountUseCase: AccountUseCase,
    private val logInKaKaoUseCase: LogInKaKaoUseCase
) : ViewModel() {
    @Inject
    lateinit var googleSignInClient: GoogleSignInClient

    val accountInfo = accountUseCase.getAccountInfo()

    private val _accountTokenState = MutableStateFlow<NetworkResult<Token>>(NetworkResult.Idle)
    val accountTokenState = _accountTokenState.asStateFlow()

    private val _accountInfoState = MutableStateFlow<NetworkResult<AccountInfo>>(NetworkResult.Idle)
    val accountInfoState = _accountInfoState.asStateFlow()

    fun signIn(accountInfo: AccountInfo) = viewModelScope.launch {
        accountUseCase.signIn(accountInfo)
    }

    fun signOutGoogle() = viewModelScope.launch {
        accountUseCase.signOut()
    }

    fun getAccountInfo() = viewModelScope.launch {
        _accountInfoState.emit(accountUseCase.getAccountInfoFromRemote())
    }

    fun getAccountInfoOnce() : AccountInfo? {
        return accountInfo.value
    }

    fun logInKakao(token: String) = viewModelScope.launch {
        Log.d(TAG, "logInKakao: $token")
        _accountTokenState.emit(logInKaKaoUseCase.invoke(token))
    }
}