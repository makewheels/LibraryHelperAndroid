package com.eg.libraryhelperandroid.visitlibrary.bean.basicinfo;

public class BookBasicInfo {
    private String mongoId;
    private String bookId;
    private String isbn;            //从图书馆网站爬下来，带横杠
    private String callno;          //索书号，例如：K837.127/57
    private String bookrecno;       //书的id，例如：127796

    private String title;           //书名
    private String author;          //作者列表
    private String publisher;       //出版社
    private String publishDate;     //出版日期
    private String coverUrl;        //封面图片

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCallno() {
        return callno;
    }

    public void setCallno(String callno) {
        this.callno = callno;
    }

    public String getBookrecno() {
        return bookrecno;
    }

    public void setBookrecno(String bookrecno) {
        this.bookrecno = bookrecno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
