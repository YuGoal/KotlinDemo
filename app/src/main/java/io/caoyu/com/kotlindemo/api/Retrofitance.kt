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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * retrofit 网络请求
 * Created by caoyu on 2017/8/28.
 */
class Retrofitance private constructor() {
    val BASE_URL = "https://interface.meiriyiwen.com/article/"
    val TIMEOUT: Long = 20
    private var mRetrofit: Retrofit? = null
    private var mArticleApi: ArticleApi? = null

    init {
        if (mRetrofit == null) createRetrofit(App.instance)
    }

    companion object {
        fun getInstance(): Retrofitance {
            return Single.Instance
        }
    }

    private object Single {
        val Instance = Retrofitance()
    }

    /**
     * 配置OkHttpClient、Retrofit、NetService三个关键对象
     * @param context
     */
    fun createRetrofit(context: Context) {
        mRetrofit = Retrofit.Builder().client(constructClient(context)).baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        mArticleApi = mRetrofit?.create(ArticleApi::class.java)
    }

    /**
     * 构造OkHttpClient
     * @param context
     * *
     * @return
     */
    private fun constructClient(context: Context): OkHttpClient {
        val cacheSize: Long = 10 * 1024 * 1024
        val file = context.externalCacheDir
        val client = OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS).readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .cache(Cache(file, cacheSize))
                .retryOnConnectionFailure(true)
                .build()
        return client
    }

    fun getToDayArticle(observer: Observer<Article>, dev: Int): Unit {
        mArticleApi?.let {
            it.getTodayArticle(dev)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer)
        }
    }

}