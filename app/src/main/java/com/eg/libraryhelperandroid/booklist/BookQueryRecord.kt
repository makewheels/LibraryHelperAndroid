package com.eg.libraryhelperandroid.booklist

/**
 * @time 2020-04-17 20:57
 */
class BookQueryRecord {
    val id: String? = null
    var isbn: String? = null            //isbn从图书馆网站爬下来，带横杠
    var isbn13: String? = null          //没有横杠，纯数字
    val callno: String? = null          //索书号
    val bookrecno: String? = null       //书的id，例如：127796
    var coverImageUrl: String? = null   //封面图片url
    var title: String? = null           //书名
    var subtitle: String? = null        //子书名
    var authorList: List<String>? = null//作者列表
    var publisher: String? = null       //出版社
    var publishDate: String? = null     //出版日期
}
