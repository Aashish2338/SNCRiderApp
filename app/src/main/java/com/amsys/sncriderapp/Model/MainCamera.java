package com.amsys.sncriderapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainCamera {
    @SerializedName("okayFlawless")
    @Expose
    private Boolean okayFlawless;
    @SerializedName("scratched")
    @Expose
    private Boolean scratched;
    @SerializedName("blur")
    @Expose
    private Boolean blur;
    @SerializedName("cracked")
    @Expose
    private Boolean cracked;
    @SerializedName("broken")
    @Expose
    private Boolean broken;

    public Boolean getOkayFlawless() {
        return okayFlawless;
    }

    public void setOkayFlawless(Boolean okayFlawless) {
        this.okayFlawless = okayFlawless;
    }

    public Boolean getScratched() {
        return scratched;
    }

    public void setScratched(Boolean scratched) {
        this.scratched = scratched;
    }

    public Boolean getBlur() {
        return blur;
    }

    public void setBlur(Boolean blur) {
        this.blur = blur;
    }

    public Boolean getCracked() {
        return cracked;
    }

    public void setCracked(Boolean cracked) {
        this.cracked = cracked;
    }

    public Boolean getBroken() {
        return broken;
    }

    public void setBroken(Boolean broken) {
        this.broken = broken;
    }
}