package com.amsys.sncriderapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EndUserOrderPriceDetail {
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("diagnosisBy")
    @Expose
    private Integer diagnosisBy;
    @SerializedName("exactPrice")
    @Expose
    private Integer exactPrice;
    @SerializedName("tupc")
    @Expose
    private String tupc;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("addressId")
    @Expose
    private Integer addressId;
    @SerializedName("ageing")
    @Expose
    private String ageing;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getDiagnosisBy() {
        return diagnosisBy;
    }

    public void setDiagnosisBy(Integer diagnosisBy) {
        this.diagnosisBy = diagnosisBy;
    }

    public Integer getExactPrice() {
        return exactPrice;
    }

    public void setExactPrice(Integer exactPrice) {
        this.exactPrice = exactPrice;
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

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getAgeing() {
        return ageing;
    }

    public void setAgeing(String ageing) {
        this.ageing = ageing;
    }
}
