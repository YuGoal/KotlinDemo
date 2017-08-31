package io.caoyu.com.kotlindemo

import android.app.Application

/**
 * Created by caoyu on 2017/8/11.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }


    companion object {
        lateinit var instance: App
    }

    fun  getInstance():App{
        return instance
    }
}