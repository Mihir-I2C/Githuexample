package com.example.api_call;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class model {
    int page;
    int per_page;
    int total;
    int total_pages;

    @SerializedName("data")
    ArrayList<data>dataArrayList;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<data> getDataArrayList() {
        return dataArrayList;
    }

    public void setDataArrayList(ArrayList<data> dataArrayList) {
        this.dataArrayList = dataArrayList;
    }
}
