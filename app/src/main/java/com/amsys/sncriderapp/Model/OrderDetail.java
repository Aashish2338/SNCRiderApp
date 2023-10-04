package com.amsys.sncriderapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetail {
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("tupc")
    @Expose
    private String tupc;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("diagnosisBy")
    @Expose
    private Integer diagnosisBy;
    @SerializedName("addressId")
    @Expose
    private Integer addressId;
    @SerializedName("ageing")
    @Expose
    private Object ageing;
    @SerializedName("orderNumber")
    @Expose
    private String orderNumber;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTupc() {
        return tupc;
    }

    public void setTupc(String tupc) {
        this.tupc = tupc;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDiagnosisBy() {
        return diagnosisBy;
    }

    public void setDiagnosisBy(Integer diagnosisBy) {
        this.diagnosisBy = diagnosisBy;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Object getAgeing() {
        return ageing;
    }

    public void setAgeing(Object ageing) {
        this.ageing = ageing;
    }
}
