package com.akbarprojec.foodmarket_kotlin.networks

import com.akbarprojec.foodmarket_kotlin.BuildConfig
import com.akbarprojec.foodmarket_kotlin.FoodMarket
import com.akbarprojec.foodmarket_kotlin.utils.Helpers
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HttpClient {

    private var client: Retrofit? = null
    private var endpoint: Endpoint? = null

    companion object {
        private var minstance: HttpClient = HttpClient()
        @Synchronized
        fun getInstance(): HttpClient {
            return minstance
        }
    }

    init {
        buildRetrofitClient()
    }

    fun getApi(): Endpoint? {
        if (endpoint == null) {
            endpoint = client!!.create(Endpoint::class.java)
        }
        return endpoint
    }

    private fun buildRetrofitClient() {
        val token = FoodMarket.getApp().getToken()
        buildRetrofitClient(token)
    }

    fun buildRetrofitClient(token: String?) {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(2, TimeUnit.MINUTES)
        builder.readTimeout(2, TimeUnit.MINUTES)
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)

            //chuck interceptor
            builder.addInterceptor(ChuckInterceptor(FoodMarket.getApp()))
        }

        if (token != null) {
            builder.addInterceptor(getInteceptorWithHeader("Authorization", "Bearer ${token}"))
        }

        val okHttpClient=builder.build()
        client=Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL+"api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(Helpers.getDefaultGson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        endpoint=null

        //Log.v("tokenRegister", token!!)


    }

    private fun getInteceptorWithHeader(headerName: String, headerValue: String): Interceptor {
        val header = HashMap<String, String>()
        header.put(headerName,headerValue)
        return getInteceptorWithHeader(header)
    }

    private fun getInteceptorWithHeader(headers: Map<String,String> ): Interceptor {
        return Interceptor {
            val original=it.request()
            val builder=original.newBuilder()
            for ((key, value) in headers) {
                builder.addHeader(key,value)
            }
            builder.method(original.method, original.body)
            it.proceed(builder.build())
        }
    }

}