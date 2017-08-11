package android.xc.com.kotlindemo.api

import android.xc.com.kotlindemo.bean.FuckGoods
import android.xc.com.kotlindemo.bean.JsonResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 接口
 * Created by caoyu on 2017/8/11.
 */
interface GankApi {

    /**
     * Android所有数据
     */
    @GET("data/Android/10/{page}")
    fun getAndroidData(@Path("page") page:Int): Observable<JsonResult<List<FuckGoods>>>
}
