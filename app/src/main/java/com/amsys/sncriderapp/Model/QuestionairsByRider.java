package com.amsys.sncriderapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionairsByRider {
    @SerializedName("deviceOnOff")
    @Expose
    private Object deviceOnOff;
    @SerializedName("displayTouch")
    @Expose
    private Object displayTouch;
    @SerializedName("accessories")
    @Expose
    private Object accessories;
    @SerializedName("functionalIssue")
    @Expose
    private Object functionalIssue;
    @SerializedName("repair")
    @Expose
    private Object repair;
    @SerializedName("warranty_Utilized")
    @Expose
    private Object warrantyUtilized;
    @SerializedName("device_Body")
    @Expose
    private Object deviceBody;
    @SerializedName("silver_Frame")
    @Expose
    private Object silverFrame;
    @SerializedName("main_Camera")
    @Expose
    private Object mainCamera;

    public Object getDeviceOnOff() {
        return deviceOnOff;
    }

    public void setDeviceOnOff(Object deviceOnOff) {
        this.deviceOnOff = deviceOnOff;
    }

    public Object getDisplayTouch() {
        return displayTouch;
    }

    public void setDisplayTouch(Object displayTouch) {
        this.displayTouch = displayTouch;
    }

    public Object getAccessories() {
        return accessories;
    }

    public void setAccessories(Object accessories) {
        this.accessories = accessories;
    }

    public Object getFunctionalIssue() {
        return functionalIssue;
    }

    public void setFunctionalIssue(Object functionalIssue) {
        this.functionalIssue = functionalIssue;
    }

    public Object getRepair() {
        return repair;
    }

    public void setRepair(Object repair) {
        this.repair = repair;
    }

    public Object getWarrantyUtilized() {
        return warrantyUtilized;
    }

    public void setWarrantyUtilized(Object warrantyUtilized) {
        this.warrantyUtilized = warrantyUtilized;
    }

    public Object getDeviceBody() {
        return deviceBody;
    }

    public void setDeviceBody(Object deviceBody) {
        this.deviceBody = deviceBody;
    }

    public Object getSilverFrame() {
        return silverFrame;
    }

    public void setSilverFrame(Object silverFrame) {
        this.silverFrame = silverFrame;
    }

    public Object getMainCamera() {
        return mainCamera;
    }

    public void setMainCamera(Object mainCamera) {
        this.mainCamera = mainCamera;
    }
}
