package com.amsys.sncriderapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WarrantyUtilized {
    @SerializedName("warranty_Utilized_Month")
    @Expose
    private Integer warrantyUtilizedMonth;
    @SerializedName("lessThanThreeMonths")
    @Expose
    private Boolean lessThanThreeMonths;
    @SerializedName("lessThanTenMonths")
    @Expose
    private Boolean lessThanTenMonths;
    @SerializedName("moreThanTenMonths")
    @Expose
    private Boolean moreThanTenMonths;
    @SerializedName("notAvailable")
    @Expose
    private Boolean notAvailable;

    public Integer getWarrantyUtilizedMonth() {
        return warrantyUtilizedMonth;
    }

    public void setWarrantyUtilizedMonth(Integer warrantyUtilizedMonth) {
        this.warrantyUtilizedMonth = warrantyUtilizedMonth;
    }

    public Boolean getLessThanThreeMonths() {
        return lessThanThreeMonths;
    }

    public void setLessThanThreeMonths(Boolean lessThanThreeMonths) {
        this.lessThanThreeMonths = lessThanThreeMonths;
    }

    public Boolean getLessThanTenMonths() {
        return lessThanTenMonths;
    }

    public void setLessThanTenMonths(Boolean lessThanTenMonths) {
        this.lessThanTenMonths = lessThanTenMonths;
    }

    public Boolean getMoreThanTenMonths() {
        return moreThanTenMonths;
    }

    public void setMoreThanTenMonths(Boolean moreThanTenMonths) {
        this.moreThanTenMonths = moreThanTenMonths;
    }

    public Boolean getNotAvailable() {
        return notAvailable;
    }

    public void setNotAvailable(Boolean notAvailable) {
        this.notAvailable = notAvailable;
    }
}
