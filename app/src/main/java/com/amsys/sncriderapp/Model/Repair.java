package com.amsys.sncriderapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Repair {
    @SerializedName("undergone_repairs")
    @Expose
    private Boolean undergoneRepairs;

    public Boolean getUndergoneRepairs() {
        return undergoneRepairs;
    }

    public void setUndergoneRepairs(Boolean undergoneRepairs) {
        this.undergoneRepairs = undergoneRepairs;
    }
}
