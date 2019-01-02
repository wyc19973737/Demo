package com.userMis.model;

public class PageModel {

    private int pageIndex;// 当前页
    private int pageLimit;// 每页几条
    private int rowStart;// 起始行
    private int rowCount;// 总行数
    private boolean pageOn;// 是否开启分页
    private String orderBy;// 排序字段

    public PageModel() {
        super();
    }

    @Override
    public String toString() {
        return "PageModel [pageIndex=" + pageIndex + ", pageLimit=" + pageLimit + ", rowStart=" + rowStart
                + ", rowCount=" + rowCount + ", pageOn=" + pageOn + ", orderBy=" + orderBy + "]";
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(int pageLimit) {
        this.pageLimit = pageLimit;
    }

    public int getRowStart() {
        rowStart = (pageIndex - 1) * pageLimit;
        return rowStart;
    }

    public void setRowStart(int rowStart) {
        this.rowStart = rowStart;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public boolean isPageOn() {
        return pageOn;
    }

    public void setPageOn(boolean pageOn) {
        this.pageOn = pageOn;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
