package com.dadada.onecloset.presentation.ui.account.component

import com.dadada.onecloset.presentation.R

sealed class AccountText(val id: Int) {

    object ACCOUNT: AccountText(R.string.account_title_account)

    object PERSONAL: AccountText(R.string.account_title_personal)
    object PERMISSION: AccountText(R.string.account_permission)
    object LOGOUT: AccountText(R.string.account_log_out)
    object WITHDRAWAL: AccountText(R.string.account_withdrawal)

    object MODEL: AccountText(R.string.account_title_virtual_model)
    object GENDER: AccountText(R.string.account_gender)

    object APP: AccountText(R.string.account_title_app)
    object VERSION: AccountText(R.string.account_version)
    object LICENSE: AccountText(R.string.account_license)

    object MODEL_REGISTER: AccountText(R.string.model_register)
    object MODEL_GET: AccountText(R.string.model_list)
    companion object {
        val personalInfoContents = listOf(PERMISSION.id, LOGOUT.id, WITHDRAWAL.id)
        val appInfoContents = listOf(VERSION.id, LICENSE.id)
        val appModelContents = listOf(MODEL_REGISTER.id)
    }

}