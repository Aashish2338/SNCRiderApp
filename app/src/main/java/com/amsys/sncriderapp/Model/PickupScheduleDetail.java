package com.amsys.sncriderapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PickupScheduleDetail {
    @SerializedName("pickupScheduleId")
    @Expose
    private Integer pickupScheduleId;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("scheduleTime")
    @Expose
    private String scheduleTime;

    public Integer getPickupScheduleId() {
        return pickupScheduleId;
    }

    public void setPickupScheduleId(Integer pickupScheduleId) {
        this.pickupScheduleId = pickupScheduleId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }
}
