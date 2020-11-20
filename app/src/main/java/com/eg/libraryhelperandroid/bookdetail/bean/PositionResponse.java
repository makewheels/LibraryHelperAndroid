package com.eg.libraryhelperandroid.bookdetail.bean;


import java.io.Serializable;

public class PositionResponse implements Serializable {
    private String room;         //文献借阅一室
    private String detailPosition;//7排A面4架2层

    private int row;        //排
    private String side;    //面
    private int shelf;      //架
    private int level;      //层

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDetailPosition() {
        return detailPosition;
    }

    public void setDetailPosition(String detailPosition) {
        this.detailPosition = detailPosition;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public int getShelf() {
        return shelf;
    }

    public void setShelf(int shelf) {
        this.shelf = shelf;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
