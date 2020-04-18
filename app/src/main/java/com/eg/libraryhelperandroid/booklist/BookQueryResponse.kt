package com.eg.libraryhelperandroid.booklist


/**
 * 相应给客户端的，查询书的列表
 *
 * @time 2020-04-17 20:50
 */
class BookQueryResponse {
    var q: String? = null
    var bookRecordList: List<BookQueryRecord>? = null
    var amount: Int = 0
}

