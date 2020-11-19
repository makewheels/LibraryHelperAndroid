package com.eg.libraryhelperandroid.bookdetail.bean;

import java.io.Serializable
import java.util.*

/**
 * barCode位置
 *
 * @time 2020-04-22 22:30
 */
class Position : Serializable {
    val message: String? = null      //图书未上架，无法定位！ 非自助借还(RFID)图书，无法定位！
    val provider: String? = null

    val position: String? = null      //01040201300402|文献借阅二室 7排A面4架2层
    val coordinate: String? = null     //坐标：01040201300402
    val room: String? = null          //文献借阅一室
    val detailPosition: String? = null  //7排A面4架2层

    val row: Int? = null        //排
    val side: String? = null     //面
    val shelf: Int? = null      //架
    val level: Int? = null       //层

    val createTime: Date? = null
    val updateTime: Date? = null
}
