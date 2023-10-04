package com.amsys.sncriderapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfirmOrderData {

    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("creationDate")
    @Expose
    private String creationDate;
    @SerializedName("statusMessage")
    @Expose
    private Object statusMessage;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Object getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(Object statusMessage) {
        this.statusMessage = statusMessage;
    }
}
