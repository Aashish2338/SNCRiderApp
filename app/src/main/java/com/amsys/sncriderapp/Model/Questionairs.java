package com.amsys.sncriderapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Questionairs {
    @SerializedName("deviceOnOff")
    @Expose
    private DeviceOnOff deviceOnOff;
    @SerializedName("displayTouch")
    @Expose
    private DisplayTouch displayTouch;
    @SerializedName("accessories")
    @Expose
    private Accessories accessories;
    @SerializedName("functionalIssue")
    @Expose
    private FunctionalIssue functionalIssue;
    @SerializedName("repair")
    @Expose
    private Repair repair;
    @SerializedName("warranty_Utilized")
    @Expose
    private WarrantyUtilized warrantyUtilized;
    @SerializedName("device_Body")
    @Expose
    private DeviceBody deviceBody;
    @SerializedName("silver_Frame")
    @Expose
    private SilverFrame silverFrame;
    @SerializedName("main_Camera")
    @Expose
    private MainCamera mainCamera;

    public DeviceOnOff getDeviceOnOff() {
        return deviceOnOff;
    }

    public void setDeviceOnOff(DeviceOnOff deviceOnOff) {
        this.deviceOnOff = deviceOnOff;
    }

    public DisplayTouch getDisplayTouch() {
        return displayTouch;
    }

    public void setDisplayTouch(DisplayTouch displayTouch) {
        this.displayTouch = displayTouch;
    }

    public Accessories getAccessories() {
        return accessories;
    }

    public void setAccessories(Accessories accessories) {
        this.accessories = accessories;
    }

    public FunctionalIssue getFunctionalIssue() {
        return functionalIssue;
    }

    public void setFunctionalIssue(FunctionalIssue functionalIssue) {
        this.functionalIssue = functionalIssue;
    }

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }

    public WarrantyUtilized getWarrantyUtilized() {
        return warrantyUtilized;
    }

    public void setWarrantyUtilized(WarrantyUtilized warrantyUtilized) {
        this.warrantyUtilized = warrantyUtilized;
    }

    public DeviceBody getDeviceBody() {
        return deviceBody;
    }

    public void setDeviceBody(DeviceBody deviceBody) {
        this.deviceBody = deviceBody;
    }

    public SilverFrame getSilverFrame() {
        return silverFrame;
    }

    public void setSilverFrame(SilverFrame silverFrame) {
        this.silverFrame = silverFrame;
    }

    public MainCamera getMainCamera() {
        return mainCamera;
    }

    public void setMainCamera(MainCamera mainCamera) {
        this.mainCamera = mainCamera;
    }
}