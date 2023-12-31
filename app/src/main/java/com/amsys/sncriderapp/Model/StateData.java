package com.amsys.sncriderapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StateData {
    @SerializedName("stateId")
    @Expose
    private Integer stateId;
    @SerializedName("stateName")
    @Expose
    private String stateName;

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
