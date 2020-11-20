package com.eg.libraryhelperandroid.visitlibrary.bean.visitlibrary;


import com.eg.libraryhelperandroid.bookdetail.bean.PositionResponse;

import java.util.List;

/**
 * 一个cell的info
 * 包括当前cell有哪些书的id
 * 还包括能不能往上下左右移动，如果能，给出他们的position
 */
public class CellInfo {
    private List<String> bookIdList;
    private PositionResponse current;
    private PositionResponse up;
    private PositionResponse down;
    private PositionResponse left;
    private PositionResponse right;

    public List<String> getBookIdList() {
        return bookIdList;
    }

    public void setBookIdList(List<String> bookIdList) {
        this.bookIdList = bookIdList;
    }

    public PositionResponse getCurrent() {
        return current;
    }

    public void setCurrent(PositionResponse current) {
        this.current = current;
    }

    public PositionResponse getUp() {
        return up;
    }

    public void setUp(PositionResponse up) {
        this.up = up;
    }

    public PositionResponse getDown() {
        return down;
    }

    public void setDown(PositionResponse down) {
        this.down = down;
    }

    public PositionResponse getLeft() {
        return left;
    }

    public void setLeft(PositionResponse left) {
        this.left = left;
    }

    public PositionResponse getRight() {
        return right;
    }

    public void setRight(PositionResponse right) {
        this.right = right;
    }
}
