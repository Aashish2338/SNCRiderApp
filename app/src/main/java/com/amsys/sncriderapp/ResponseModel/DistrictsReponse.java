package com.amsys.sncriderapp.ResponseModel;

import com.amsys.sncriderapp.Model.DistrictsData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DistrictsReponse {
    @SerializedName("data")
    @Expose
    private List<DistrictsData> data;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("errorMessage")
    @Expose
    private Object errorMessage;

    public List<DistrictsData> getData() {
        return data;
    }

    public void setData(List<DistrictsData> data) {
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
