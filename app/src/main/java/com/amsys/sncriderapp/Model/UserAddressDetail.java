package com.amsys.sncriderapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserAddressDetail {
    @SerializedName("addressId")
    @Expose
    private Integer addressId;
    @SerializedName("districtName")
    @Expose
    private String districtName;
    @SerializedName("stateName")
    @Expose
    private String stateName;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("flatDetail")
    @Expose
    private String flatDetail;
    @SerializedName("areaDetail")
    @Expose
    private String areaDetail;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("districtId")
    @Expose
    private Integer districtId;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFlatDetail() {
        return flatDetail;
    }

    public void setFlatDetail(String flatDetail) {
        this.flatDetail = flatDetail;
    }

    public String getAreaDetail() {
        return areaDetail;
    }

    public void setAreaDetail(String areaDetail) {
        this.areaDetail = areaDetail;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }
}
