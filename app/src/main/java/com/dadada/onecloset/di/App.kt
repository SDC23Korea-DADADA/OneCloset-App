package com.dadada.onecloset.di

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.navercorp.nid.NaverIdLoginSDK
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "60555c92b85f69553c0a3cbbebc671a9")
        NaverIdLoginSDK.initialize(this, "NArN4_z7QdoVeJ1ffFtv", "nFKvIyKQxO", "One Closet")
    }
}