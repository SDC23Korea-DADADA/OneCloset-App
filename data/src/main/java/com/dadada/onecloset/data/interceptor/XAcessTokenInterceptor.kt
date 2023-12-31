package com.bonobono.data.interceptor

import android.util.Log
import com.dadada.onecloset.data.datasource.local.PreferenceDataSource
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

private const val TAG = "XAcessTokenInterceptor"
class XAccessTokenInterceptor @Inject constructor(
    private val dataSource: PreferenceDataSource
): Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        try {
            dataSource.getAccountInfo()?.accessToken.let { token ->
                token?.let {
                    builder.addHeader("Authorization", "Bearer $it")
                    Log.d(TAG, "intercept: 헤더 담김 ${it}")
                    return chain.proceed(builder.build())
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, "intercept: 헤더 안담김")
            //return chain.proceed(chain.request())
        }
        Log.d(TAG, "intercept: Intercept!!!!!")
        return chain.proceed(builder.build())
    }
}