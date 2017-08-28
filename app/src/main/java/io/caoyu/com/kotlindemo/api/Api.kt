package io.caoyu.com.kotlindemo.api

/**
 * 网络请求类
 * Created by caoyu on 2017/8/11.
 */
class Api {
    companion object {
        val CONNECT_TIMEOUT : Int = 5
        val READ_TIMEOUT : Int = 5
        val WRITE_TIMEOUT : Int = 5
        val instance: Api by lazy { Holder.INSTANCE }
    }

    private object Holder { val INSTANCE = Api() }

}