package com.amsys.sncriderapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountData {
    @SerializedName("totalOpenOrders")
    @Expose
    private Integer totalOpenOrders;
    @SerializedName("totalConfirmedOrders")
    @Expose
    private Integer totalConfirmedOrders;

    public Integer getTotalOpenOrders() {
        return totalOpenOrders;
    }

    public void setTotalOpenOrders(Integer totalOpenOrders) {
        this.totalOpenOrders = totalOpenOrders;
    }

    public Integer getTotalConfirmedOrders() {
        return totalConfirmedOrders;
    }

    public void setTotalConfirmedOrders(Integer totalConfirmedOrders) {
        this.totalConfirmedOrders = totalConfirmedOrders;
    }
}
