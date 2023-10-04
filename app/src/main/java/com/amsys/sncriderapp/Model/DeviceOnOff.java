package com.amsys.sncriderapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeviceOnOff {
    @SerializedName("isDeviceOn")
    @Expose
    private Boolean isDeviceOn;

    public Boolean getIsDeviceOn() {
        return isDeviceOn;
    }

    public void setIsDeviceOn(Boolean isDeviceOn) {
        this.isDeviceOn = isDeviceOn;
    }
}
