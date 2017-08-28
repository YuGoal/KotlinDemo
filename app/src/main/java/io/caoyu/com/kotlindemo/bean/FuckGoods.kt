package io.caoyu.com.kotlindemo.bean

/**
 * 历史上的今天
 * Created by caoyu on 2017/8/11.
 */
class FuckGoods(
        var reason: String,
        var error_code: Int = 0,
        var result: List<ResultBean>? = null
)

class ResultBean {
    var _id: String? = null
    var title: String? = null
    var pic: String? = null
    var year: Int = 0
    var month: Int = 0
    var day: Int = 0
    var des: String? = null
    var lunar: String? = null
}