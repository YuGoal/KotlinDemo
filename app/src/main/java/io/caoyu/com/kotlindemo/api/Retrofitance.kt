package io.caoyu.com.kotlindemo.api

import android.content.Context
import android.util.AndroidException
import io.caoyu.com.kotlindemo.App
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * retrofit 网络请求
 * Created by caoyu on 2017/8/28.
 */
class RetrofitManager private constructor(url: String)  {

    //短缓存有效期为10分钟
    val CACHE_STALE_SHORT = 60 * 10
    //长缓存有效期为7天
    val CACHE_STALE_LONG = "60 * 60 * 24 * 7"
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    val CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_LONG
    //查询网络的Cache-Control设置，头部Cache-Control设为max-age=0时则不会使用缓存而请求服务器
    val CACHE_CONTROL_NETWORK = "max-age=0"

    var mOkHttpClient: OkHttpClient? = null
    var service: Api? = null

    //初始化
    init {
        initOkHttpclient()
        var retrofit = Retrofit.Builder()
                .baseUrl(url)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        service = retrofit.create(Api::class.java)

    }
    companion object {
        fun builder(url: String): RetrofitManager {
            println(RetrofitManager.javaClass.classes)
            return RetrofitManager(url)
        }
    }


    //配置缓存策略
    fun initOkHttpclient() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        if (mOkHttpClient == null) {
            mOkHttpClient = OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .addNetworkInterceptor(this)
                    .addInterceptor(this)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .build()
        }
    }



    fun getBanner(): Observable<List<Banner>> = service!!.getHot()

}