package com.amsys.sncriderapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeviceBody {
    @SerializedName("flawless")
    @Expose
    private Boolean flawless;
    @SerializedName("scratched")
    @Expose
    private Boolean scratched;
    @SerializedName("broken")
    @Expose
    private Boolean broken;
    @SerializedName("loose")
    @Expose
    private Boolean loose;
    @SerializedName("missing")
    @Expose
    private Boolean missing;

    public Boolean getFlawless() {
        return flawless;
    }

    public void setFlawless(Boolean flawless) {
        this.flawless = flawless;
    }

    public Boolean getScratched() {
        return scratched;
    }

    public void setScratched(Boolean scratched) {
        this.scratched = scratched;
    }

    public Boolean getBroken() {
        return broken;
    }

    public void setBroken(Boolean broken) {
        this.broken = broken;
    }

    public Boolean getLoose() {
        return loose;
    }

    public void setLoose(Boolean loose) {
        this.loose = loose;
    }

    public Boolean getMissing() {
        return missing;
    }

    public void setMissing(Boolean missing) {
        this.missing = missing;
    }
}
