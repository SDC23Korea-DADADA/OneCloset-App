package com.dadada.onecloset.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.dadada.onecloset.presentation.ui.theme.BackGround
import com.dadada.onecloset.presentation.ui.theme.OneClosetTheme
import com.dadada.onecloset.presentation.viewmodel.account.AccountViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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
                    val startDestination = if(accountInfo == null) LogInNav.route else MainTabNav.route
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