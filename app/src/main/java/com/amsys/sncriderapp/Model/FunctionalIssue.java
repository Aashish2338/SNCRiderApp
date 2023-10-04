package com.amsys.sncriderapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FunctionalIssue {
    @SerializedName("volume_Button")
    @Expose
    private Boolean volumeButton;
    @SerializedName("power_Home_Button")
    @Expose
    private Boolean powerHomeButton;
    @SerializedName("wifi_Bluetooth_GPS")
    @Expose
    private Boolean wifiBluetoothGPS;
    @SerializedName("charging_Defect")
    @Expose
    private Boolean chargingDefect;
    @SerializedName("battery_Faulty")
    @Expose
    private Boolean batteryFaulty;
    @SerializedName("speakers_Faulty")
    @Expose
    private Boolean speakersFaulty;
    @SerializedName("microphone_Faulty")
    @Expose
    private Boolean microphoneFaulty;
    @SerializedName("gsM_Faulty")
    @Expose
    private Boolean gsMFaulty;
    @SerializedName("earphone_Jack_Faulty")
    @Expose
    private Boolean earphoneJackFaulty;
    @SerializedName("fingerprint_Sensor_Faulty")
    @Expose
    private Boolean fingerprintSensorFaulty;
    @SerializedName("camera_Faulty")
    @Expose
    private Boolean cameraFaulty;

    public Boolean getVolumeButton() {
        return volumeButton;
    }

    public void setVolumeButton(Boolean volumeButton) {
        this.volumeButton = volumeButton;
    }

    public Boolean getPowerHomeButton() {
        return powerHomeButton;
    }

    public void setPowerHomeButton(Boolean powerHomeButton) {
        this.powerHomeButton = powerHomeButton;
    }

    public Boolean getWifiBluetoothGPS() {
        return wifiBluetoothGPS;
    }

    public void setWifiBluetoothGPS(Boolean wifiBluetoothGPS) {
        this.wifiBluetoothGPS = wifiBluetoothGPS;
    }

    public Boolean getChargingDefect() {
        return chargingDefect;
    }

    public void setChargingDefect(Boolean chargingDefect) {
        this.chargingDefect = chargingDefect;
    }

    public Boolean getBatteryFaulty() {
        return batteryFaulty;
    }

    public void setBatteryFaulty(Boolean batteryFaulty) {
        this.batteryFaulty = batteryFaulty;
    }

    public Boolean getSpeakersFaulty() {
        return speakersFaulty;
    }

    public void setSpeakersFaulty(Boolean speakersFaulty) {
        this.speakersFaulty = speakersFaulty;
    }

    public Boolean getMicrophoneFaulty() {
        return microphoneFaulty;
    }

    public void setMicrophoneFaulty(Boolean microphoneFaulty) {
        this.microphoneFaulty = microphoneFaulty;
    }

    public Boolean getGsMFaulty() {
        return gsMFaulty;
    }

    public void setGsMFaulty(Boolean gsMFaulty) {
        this.gsMFaulty = gsMFaulty;
    }

    public Boolean getEarphoneJackFaulty() {
        return earphoneJackFaulty;
    }

    public void setEarphoneJackFaulty(Boolean earphoneJackFaulty) {
        this.earphoneJackFaulty = earphoneJackFaulty;
    }

    public Boolean getFingerprintSensorFaulty() {
        return fingerprintSensorFaulty;
    }

    public void setFingerprintSensorFaulty(Boolean fingerprintSensorFaulty) {
        this.fingerprintSensorFaulty = fingerprintSensorFaulty;
    }

    public Boolean getCameraFaulty() {
        return cameraFaulty;
    }

    public void setCameraFaulty(Boolean cameraFaulty) {
        this.cameraFaulty = cameraFaulty;
    }
}
