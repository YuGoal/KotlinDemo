package io.caoyu.com.kotlindemo.api

import io.caoyu.com.kotlindemo.bean.FuckGoods
import io.caoyu.com.kotlindemo.bean.JsonResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import java.util.*

/**
 * 接口
 * Created by caoyu on 2017/8/11.
 */
interface ToDayApi {

    /**
     * Android所有数据
     */
    @GET("japi/toh")
    fun toDay(@QueryMap map:Map<String,String>): Observable<JsonResult<List<FuckGoods>>>
}
