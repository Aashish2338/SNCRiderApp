package com.amsys.sncriderapp.ResponseModel;

import com.amsys.sncriderapp.Model.CountData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderCountResponse {
    @SerializedName("data")
    @Expose
    private CountData data;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("errorMessage")
    @Expose
    private Object errorMessage;

    public CountData getData() {
        return data;
    }

    public void setData(CountData data) {
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
