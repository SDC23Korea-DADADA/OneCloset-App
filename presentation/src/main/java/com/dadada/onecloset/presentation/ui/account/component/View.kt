package com.dadada.onecloset.presentation.ui.account.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun SignInButtonView(signInButtons: List<SignInButton>) {
    signInButtons.forEach {
        Image(
            modifier = Modifier.padding(horizontal = 16.dp).clickable(onClick = it.action),
            painter = painterResource(id = it.id),
            contentDescription = it.description
        )
        Spacer(modifier = Modifier.size(12.dp))
    }
}