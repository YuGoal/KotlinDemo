package io.caoyu.com.kotlindemo

import android.app.Application

/**
 * Created by caoyu on 2017/8/11.
 */
class App : Application() {
    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        lateinit var instance: App
    }
}