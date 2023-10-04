package com.amsys.sncriderapp.ResponseModel;

import com.amsys.sncriderapp.Model.RiderOrderConfirmPickupData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RiderOrderConfirmPickupResponse {

    @SerializedName("data")
    @Expose
    private List<RiderOrderConfirmPickupData> data;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("errorMessage")
    @Expose
    private Object errorMessage;

    public List<RiderOrderConfirmPickupData> getData() {
        return data;
    }

    public void setData(List<RiderOrderConfirmPickupData> data) {
        this.data = data;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }
}