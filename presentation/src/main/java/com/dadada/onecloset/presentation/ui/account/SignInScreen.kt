package com.dadada.onecloset.presentation.ui.account

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.dadada.onecloset.domain.model.AccountInfo
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.viewmodel.AccountViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

private const val TAG = "LogInScreen"

@Composable
fun SignInScreen(
    accountViewModel: AccountViewModel = hiltViewModel(),
    googleSignInClient: GoogleSignInClient
) {
    val accountInfo by accountViewModel.accountInfo.collectAsState()
    val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    val context = LocalContext.current
    val startForResult =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val indent = result.data
                if (indent != null) {
                    val task: Task<GoogleSignInAccount> =
                        GoogleSignIn.getSignedInAccountFromIntent(indent)
                    handleSignInResult(context, task, accountViewModel, firebaseAuth)
                }
            }
        }
    val kakaoCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        when {
            error != null -> {
                Log.d(TAG, "LogInScreen: 로그인 실패")
            }

            token != null -> {
                Log.d(TAG, "LogInScreen: 로그인 성공")
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (accountInfo != null) {
            // 자동 로그인 처리
        }

        Image(painter = painterResource(id = R.drawable.ic_google), contentDescription = "구글 로그인")
        Image(painter = painterResource(id = R.drawable.ic_kakao), contentDescription = "카카오 로그인")
        Image(painter = painterResource(id = R.drawable.ic_naver), contentDescription = "네이버 로그인")
        Button(onClick = { startForResult.launch(googleSignInClient.signInIntent) }) {
            Text(text = "구글 로그인")
        }
        Button(onClick = { logInKakao(context, kakaoCallback) }) {
            Text(text = "카카오 로그인")
        }
    }
}

private fun logInKakao(context: Context, callback: (OAuthToken?, Throwable?) -> Unit) {
    // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
    if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                Log.e(TAG, "카카오톡으로 로그인 실패", error)

                // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                if ((error is ClientError) && (error.reason == ClientErrorCause.Cancelled)) {
                    Log.d(TAG, "logInKakao: ")
                    return@loginWithKakaoTalk
                }

                // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                //UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
            } else if (token != null) {
                Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
            }
        }
    } else {
        UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
    }
}

private fun handleSignInResult(
    context: Context,
    accountTask: Task<GoogleSignInAccount>,
    accountViewModel: AccountViewModel,
    firebaseAuth: FirebaseAuth
) {
    try {
        val account = accountTask.result ?: return
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(context as Activity) { task ->
                if (task.isSuccessful) {
                    accountViewModel.signInGoogle(
                        AccountInfo(
                            account.idToken.orEmpty(),
                            account.displayName.orEmpty(),
                            AccountInfo.Type.GOOGLE,
                            //account.photoUrl.toString()
                        )
                    )
                } else {
                    accountViewModel.signOutGoogle()
                    firebaseAuth.signOut()
                }
            }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}