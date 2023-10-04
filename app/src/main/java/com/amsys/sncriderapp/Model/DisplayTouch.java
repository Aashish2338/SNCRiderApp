package com.amsys.sncriderapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DisplayTouch {
    @SerializedName("flawless")
    @Expose
    private Boolean flawless;
    @SerializedName("minorScratchesTwoOrThree")
    @Expose
    private Boolean minorScratchesTwoOrThree;
    @SerializedName("shaded_WhiteDots")
    @Expose
    private Boolean shadedWhiteDots;
    @SerializedName("broken_Dead")
    @Expose
    private Boolean brokenDead;

    public Boolean getFlawless() {
        return flawless;
    }

    public void setFlawless(Boolean flawless) {
        this.flawless = flawless;
    }

    public Boolean getMinorScratchesTwoOrThree() {
        return minorScratchesTwoOrThree;
    }

    public void setMinorScratchesTwoOrThree(Boolean minorScratchesTwoOrThree) {
        this.minorScratchesTwoOrThree = minorScratchesTwoOrThree;
    }

    public Boolean getShadedWhiteDots() {
        return shadedWhiteDots;
    }

    public void setShadedWhiteDots(Boolean shadedWhiteDots) {
        this.shadedWhiteDots = shadedWhiteDots;
    }

    public Boolean getBrokenDead() {
        return brokenDead;
    }

    public void setBrokenDead(Boolean brokenDead) {
        this.brokenDead = brokenDead;
    }
}
