package com.eg.libraryhelperandroid.bookdetail.bean

/**
 * 返回给客户端的，书的详情页的数据
 *
 * @time 2020-04-18 19:24
 */
class BookDetailResponse {
    val mangoId: String? = null

    //一次图书馆爬虫所拿到的：
    val isbn: String? = null            //isbn从图书馆网站爬下来，带横杠
    val callno: String? = null          //索书号
    val bookrecno: String? = null       //书的id，例如：127796

    //豆瓣api拿到的：
    val isbn13: String? = null          //没有横杠，纯数字
    val coverUrl: String? = null        //封面图片url
    val title: String? = null           //书名
    val subtitle: String? = null        //子书名
    val originTitle: String? = null     //原书名
    val authorList: List<String>? = null//作者列表
    val publisher: String? = null       //出版社
    val publishDate: String? = null     //出版日期
    val price: String? = null           //价格
    val pages: String? = null           //页码数
    val catalog: String? = null         //目录
    val summary: String? = null         //简述

    val position: Position? = null        //位置
}
