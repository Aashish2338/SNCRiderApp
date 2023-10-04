package com.amsys.sncriderapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RiderOrderConfirmPickupData {
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("orderNumber")
    @Expose
    private String orderNumber;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("productImgUrl1")
    @Expose
    private String productImgUrl1;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("completeAddress")
    @Expose
    private String completeAddress;
    @SerializedName("exactPriceByEndUser")
    @Expose
    private Integer exactPriceByEndUser;
    @SerializedName("exactPriceByRider")
    @Expose
    private Object exactPriceByRider;
    @SerializedName("baseProductId")
    @Expose
    private Integer baseProductId;
    @SerializedName("baseProductName")
    @Expose
    private String baseProductName;
    @SerializedName("brandId")
    @Expose
    private Integer brandId;
    @SerializedName("brandName")
    @Expose
    private String brandName;
    @SerializedName("districtId")
    @Expose
    private Integer districtId;
    @SerializedName("lastOrderStatusValue")
    @Expose
    private Integer lastOrderStatusValue;
    @SerializedName("lastStatus")
    @Expose
    private Object lastStatus;

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImgUrl1() {
        return productImgUrl1;
    }

    public void setProductImgUrl1(String productImgUrl1) {
        this.productImgUrl1 = productImgUrl1;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getCompleteAddress() {
        return completeAddress;
    }

    public void setCompleteAddress(String completeAddress) {
        this.completeAddress = completeAddress;
    }

    public Integer getExactPriceByEndUser() {
        return exactPriceByEndUser;
    }

    public void setExactPriceByEndUser(Integer exactPriceByEndUser) {
        this.exactPriceByEndUser = exactPriceByEndUser;
    }

    public Object getExactPriceByRider() {
        return exactPriceByRider;
    }

    public void setExactPriceByRider(Object exactPriceByRider) {
        this.exactPriceByRider = exactPriceByRider;
    }

    public Integer getBaseProductId() {
        return baseProductId;
    }

    public void setBaseProductId(Integer baseProductId) {
        this.baseProductId = baseProductId;
    }

    public String getBaseProductName() {
        return baseProductName;
    }

    public void setBaseProductName(String baseProductName) {
        this.baseProductName = baseProductName;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getLastOrderStatusValue() {
        return lastOrderStatusValue;
    }

    public void setLastOrderStatusValue(Integer lastOrderStatusValue) {
        this.lastOrderStatusValue = lastOrderStatusValue;
    }

    public Object getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(Object lastStatus) {
        this.lastStatus = lastStatus;
    }
}