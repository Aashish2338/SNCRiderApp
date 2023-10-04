package com.amsys.sncriderapp.ResponseModel;

import com.amsys.sncriderapp.Model.ConfirmOrderData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfirmOrderResponse {
    @SerializedName("data")
    @Expose
    private ConfirmOrderData data;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("errorMessage")
    @Expose
    private Object errorMessage;

    public ConfirmOrderData getData() {
        return data;
    }

    public void setData(ConfirmOrderData data) {
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
