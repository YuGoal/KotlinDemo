package io.caoyu.com.kotlindemo.api

import io.caoyu.com.kotlindemo.bean.Data
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by caoyu on 2017/8/28.
 */
interface ArticleApi {

    @GET("today")
    fun getTodayArticle(@Query("dev") dev: Int): Observable<Article>
}

data class Article(var data: Data)
