package com.amsys.sncriderapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VariantDetail {
    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("brandName")
    @Expose
    private String brandName;
    @SerializedName("baseProductName")
    @Expose
    private String baseProductName;
    @SerializedName("tupc")
    @Expose
    private String tupc;
    @SerializedName("baseProductId")
    @Expose
    private Integer baseProductId;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("storage")
    @Expose
    private String storage;
    @SerializedName("productImgUrl1")
    @Expose
    private String productImgUrl1;
    @SerializedName("productImgUrl2")
    @Expose
    private String productImgUrl2;
    @SerializedName("productImgUrl3")
    @Expose
    private String productImgUrl3;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBaseProductName() {
        return baseProductName;
    }

    public void setBaseProductName(String baseProductName) {
        this.baseProductName = baseProductName;
    }

    public String getTupc() {
        return tupc;
    }

    public void setTupc(String tupc) {
        this.tupc = tupc;
    }

    public Integer getBaseProductId() {
        return baseProductId;
    }

    public void setBaseProductId(Integer baseProductId) {
        this.baseProductId = baseProductId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getProductImgUrl1() {
        return productImgUrl1;
    }

    public void setProductImgUrl1(String productImgUrl1) {
        this.productImgUrl1 = productImgUrl1;
    }

    public String getProductImgUrl2() {
        return productImgUrl2;
    }

    public void setProductImgUrl2(String productImgUrl2) {
        this.productImgUrl2 = productImgUrl2;
    }

    public String getProductImgUrl3() {
        return productImgUrl3;
    }

    public void setProductImgUrl3(String productImgUrl3) {
        this.productImgUrl3 = productImgUrl3;
    }
}
