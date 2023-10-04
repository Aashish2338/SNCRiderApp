package com.amsys.sncriderapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SilverFrame {
    @SerializedName("okay")
    @Expose
    private Boolean okay;
    @SerializedName("discolored")
    @Expose
    private Boolean discolored;
    @SerializedName("dented")
    @Expose
    private Boolean dented;
    @SerializedName("broken")
    @Expose
    private Boolean broken;

    public Boolean getOkay() {
        return okay;
    }

    public void setOkay(Boolean okay) {
        this.okay = okay;
    }

    public Boolean getDiscolored() {
        return discolored;
    }

    public void setDiscolored(Boolean discolored) {
        this.discolored = discolored;
    }

    public Boolean getDented() {
        return dented;
    }

    public void setDented(Boolean dented) {
        this.dented = dented;
    }

    public Boolean getBroken() {
        return broken;
    }

    public void setBroken(Boolean broken) {
        this.broken = broken;
    }
}
