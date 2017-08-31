package io.caoyu.com.kotlindemo.api


import retrofit2.http.GET
import java.util.*

/**
 * 接口
 * Created by caoyu on 2017/8/11.
 */
interface Api {

    @GET("/course_api/banner/query?type=1")
    fun getHot(): Observable<List<Banner>>
}
