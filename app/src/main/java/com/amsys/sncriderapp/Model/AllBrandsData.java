package com.amsys.sncriderapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllBrandsData {

    @SerializedName("brandId")
    @Expose
    private Integer brandId;
    @SerializedName("brandName")
    @Expose
    private String brandName;
    @SerializedName("brandImgUrl")
    @Expose
    private String brandImgUrl;

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

    public String getBrandImgUrl() {
        return brandImgUrl;
    }

    public void setBrandImgUrl(String brandImgUrl) {
        this.brandImgUrl = brandImgUrl;
    }
}
