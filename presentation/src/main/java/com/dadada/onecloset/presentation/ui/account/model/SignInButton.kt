package com.dadada.onecloset.presentation.ui.account.model

import androidx.compose.runtime.Composable

data class SignInButton(val id: Int, val description: String, val action: @Composable () -> Unit)