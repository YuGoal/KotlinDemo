package io.caoyu.com.kotlindemo.bean

/**
 * Created by caoyu on 2017/8/28.
 */
data class Date(var prev : String, var curr:String, var next:String)
data class Data(var author:String , var content:String , var date:Date, var digest:String, var title:String, var wc:String)
data class Article(var data:Data)


data class DBArticle(val title: String, val author: String, val content:String ,val wc: String, val prev:String, val curr:String, val next:String)