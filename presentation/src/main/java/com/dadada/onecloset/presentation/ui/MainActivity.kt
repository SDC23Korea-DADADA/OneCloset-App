package com.dadada.onecloset.presentation.ui

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.browser.customtabs.CustomTabsClient.getPackageName
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.dadada.onecloset.presentation.ui.theme.BackGround
import com.dadada.onecloset.presentation.ui.theme.OneClosetTheme
import com.dadada.onecloset.presentation.viewmodel.account.AccountViewModel
import com.kakao.sdk.common.util.Utility
import dagger.hilt.android.AndroidEntryPoint
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


private const val TAG = "MainActivity"
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OneClosetTheme {
                // A surface container using the 'background' color from the theme
                val accountViewModel: AccountViewModel = hiltViewModel()
                val accountInfo = accountViewModel.getAccountInfoOnce()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BackGround
                ) {
                    var keyHash = Utility.getKeyHash(this)
                    Log.d(TAG, "onCreate: $keyHash")
                    val startDestination =
                        if (accountInfo == null) NavigationItem.LogInNav.route else NavigationItem.MainTabNav.route
                    MainScreen(startDestination = startDestination)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OneClosetTheme {
        Greeting("Android")
    }
}