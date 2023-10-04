package com.amsys.sncriderapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Accessories {
    @SerializedName("earphone")
    @Expose
    private Boolean earphone;
    @SerializedName("box_with_same_IMEI")
    @Expose
    private Boolean boxWithSameIMEI;
    @SerializedName("original_Charger")
    @Expose
    private Boolean originalCharger;

    public Boolean getEarphone() {
        return earphone;
    }

    public void setEarphone(Boolean earphone) {
        this.earphone = earphone;
    }

    public Boolean getBoxWithSameIMEI() {
        return boxWithSameIMEI;
    }

    public void setBoxWithSameIMEI(Boolean boxWithSameIMEI) {
        this.boxWithSameIMEI = boxWithSameIMEI;
    }

    public Boolean getOriginalCharger() {
        return originalCharger;
    }

    public void setOriginalCharger(Boolean originalCharger) {
        this.originalCharger = originalCharger;
    }
}
