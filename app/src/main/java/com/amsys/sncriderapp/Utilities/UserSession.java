package com.amsys.sncriderapp.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSession {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;
    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "AmsysInfocom";
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";

    public UserSession(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String name, String password) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, password);
        editor.commit();
    }

    public void setUserId(int userId) {
        editor.putInt("userId", userId);
        editor.commit();
    }

    public int getUserId() {
        return pref.getInt("userId", 0);
    }

    public void setUserFullName(String fullName) {
        editor.putString("fullName", fullName);
        editor.commit();
    }

    public String getUserFullName() {
        return pref.getString("fullName", "");
    }

    public void setUserMobileNumber(String mobileNumber) {
        editor.putString("mobileNumber", mobileNumber);
        editor.commit();
    }

    public String getUserMobileNumber() {
        return pref.getString("mobileNumber", "");
    }

    public void setUserMail(String userMail) {
        editor.putString("userMail", userMail);
        editor.commit();
    }

    public String getUserMail() {
        return pref.getString("userMail", "");
    }

    public void setUserTypeDetail(String userTypeDetail) {
        editor.putString("userTypeDetail", userTypeDetail);
        editor.commit();
    }

    public String getUserTypeDetail() {
        return pref.getString("userTypeDetail", "");
    }

    public void setUserType(int userType) {
        editor.putInt("userType", userType);
        editor.commit();
    }

    public int getUserType() {
        return pref.getInt("userType", 0);
    }

    public void setToken(String token) {
        editor.putString("token", token);
        editor.commit();
    }

    public String getToken() {
        return pref.getString("token", "");
    }

    public void setOrderId(String orderId) {
        editor.putString("orderId", orderId);
        editor.commit();
    }

    public String getOrderId() {
        return pref.getString("orderId", "");
    }

    public void setOrderNumber(String orderNumber) {
        editor.putString("orderNumber", orderNumber);
        editor.commit();
    }

    public String getOrderNumber() {
        return pref.getString("orderNumber", "");
    }

    public void setExactPrice(int exactPrice) {
        editor.putInt("exactPrice", exactPrice);
        editor.commit();
    }

    public int getExactPrice() {
        return pref.getInt("exactPrice", 0);
    }

    public void setDistrictId(int districtId) {
        editor.putInt("districtId", districtId);
        editor.commit();
    }

    public int getDistrictId() {
        return pref.getInt("districtId", 0);
    }

    public void setAgeingId(String ageing) {
        editor.putString("ageing", ageing);
        editor.commit();
    }

    public String getAgeingId() {
        return pref.getString("ageing", "");
    }

    public void setTupcId(String tupc) {
        editor.putString("tupc", tupc);
        editor.commit();
    }

    public String getTupcId() {
        return pref.getString("tupc", "");
    }

    public void setAddressId(int addressId) {
        editor.putInt("addressId", addressId);
        editor.commit();
    }

    public int getAddressId() {
        return pref.getInt("addressId", 0);
    }

    public void setDeviceOnOffJson(String deviceOnOff) {
        editor.putString("deviceOnOff", deviceOnOff);
        editor.commit();
    }

    public String getDeviceOnOffJson() {
        return pref.getString("deviceOnOff", "");
    }

    public void setDeviceOnOffEndUserJson(String deviceOnOff) {
        editor.putString("deviceOnOff", deviceOnOff);
        editor.commit();
    }

    public String getDeviceOnOffEndUserJson() {
        return pref.getString("deviceOnOff", "");
    }

    public void setDisplayTouchJson(String displayTouch) {
        editor.putString("displayTouch", displayTouch);
        editor.commit();
    }

    public String getDisplayTouchJson() {
        return pref.getString("displayTouch", "");
    }

    public void setDisplayTouchEndUserJson(String displayTouch) {
        editor.putString("displayTouch", displayTouch);
        editor.commit();
    }

    public String getDisplayTouchEndUserJson() {
        return pref.getString("displayTouch", "");
    }

    public void setAvailableAccessoriesJson(String accessories) {
        editor.putString("accessories", accessories);
        editor.commit();
    }

    public String getAvailableAccessoriesJson() {
        return pref.getString("accessories", "");
    }

    public void setAvailableAccessoriesEndUserJson(String accessories) {
        editor.putString("accessories", accessories);
        editor.commit();
    }

    public String getAvailableAccessoriesEndUserJson() {
        return pref.getString("accessories", "");
    }

    public void setFunctionalIssueJson(String functionalIssue) {
        editor.putString("functionalIssue", functionalIssue);
        editor.commit();
    }

    public String getFunctionalIssueJson() {
        return pref.getString("functionalIssue", "");
    }

    public void setFunctionalIssueEndUserJson(String functionalIssue) {
        editor.putString("functionalIssue", functionalIssue);
        editor.commit();
    }

    public String getFunctionalIssueEndUserJson() {
        return pref.getString("functionalIssue", "");
    }

    public void setRepairDetailsJson(String repair) {
        editor.putString("repair", repair);
        editor.commit();
    }

    public String getRepairDetailsJson() {
        return pref.getString("repair", "");
    }

    public void setRepairDetailsEndUserJson(String repair) {
        editor.putString("repair", repair);
        editor.commit();
    }

    public String getRepairDetailsEndUserJson() {
        return pref.getString("repair", "");
    }

    public void setBrandWarrantyUtilizedJson(String warrantyUtilized) {
        editor.putString("warrantyUtilized", warrantyUtilized);
        editor.commit();
    }

    public String getBrandWarrantyUtilizedJson() {
        return pref.getString("warrantyUtilized", "");
    }

    public void setBrandWarrantyUtilizedEndUserJson(String warrantyUtilized) {
        editor.putString("warrantyUtilized", warrantyUtilized);
        editor.commit();
    }

    public String getBrandWarrantyUtilizedEndUserJson() {
        return pref.getString("warrantyUtilized", "");
    }

    public void setBodyInformationJson(String deviceBody) {
        editor.putString("deviceBody", deviceBody);
        editor.commit();
    }

    public String getBodyInformationJson() {
        return pref.getString("deviceBody", "");
    }

    public void setBodyInformationEndUserJson(String deviceBody) {
        editor.putString("deviceBody", deviceBody);
        editor.commit();
    }

    public String getBodyInformationEndUserJson() {
        return pref.getString("deviceBody", "");
    }

    public void setSilverFrameBezelJson(String silverFrame) {
        editor.putString("silverFrame", silverFrame);
        editor.commit();
    }

    public String getSilverFrameBezelJson() {
        return pref.getString("silverFrame", "");
    }

    public void setSilverFrameBezelEndUserJson(String silverFrame) {
        editor.putString("silverFrame", silverFrame);
        editor.commit();
    }

    public String getSilverFrameBezelEndUserJson() {
        return pref.getString("silverFrame", "");
    }

    public void setMainCameraJson(String mainCamera) {
        editor.putString("mainCamera", mainCamera);
        editor.commit();
    }

    public String getMainCameraJson() {
        return pref.getString("mainCamera", "");
    }

    public void setMainCameraEndUserJson(String mainCamera) {
        editor.putString("mainCamera", mainCamera);
        editor.commit();
    }

    public String getMainCameraEndUserJson() {
        return pref.getString("mainCamera", "");
    }

    //-----------------------For questionaries------------------------//
    public void setDeviceConditionYesNo(String deviceConditionYesNo) {
        editor.putString("deviceConditionYesNo", deviceConditionYesNo);
        editor.commit();
    }

    public String getDeviceConditionYesNo() {
        return pref.getString("deviceConditionYesNo", "");
    }

    /*----------------Display and touch screen---------*/
    public void setFlawlessValueOfDisplayAndTouchScreen(String flawlessValue) {
        editor.putString("flawlessValue", flawlessValue);
        editor.commit();
    }

    public String getFlawlessValueOfDisplayAndTouchScreen() {
        return pref.getString("flawlessValue", "");
    }

    public void setMinorScratchesOfDisplayAndTouchScreen(String minorScrathes) {
        editor.putString("minorScrathes", minorScrathes);
        editor.commit();
    }

    public String getMinorScratchesOfDisplayAndTouchScreen() {
        return pref.getString("minorScrathes", "");
    }

    public void setShadedWhiteDotsOfDisplayAndTouchScreen(String shadedWhiteDots) {
        editor.putString("shadedWhiteDots", shadedWhiteDots);
        editor.commit();
    }

    public String getShadedWhiteDotsOfDisplayAndTouchScreen() {
        return pref.getString("shadedWhiteDots", "");
    }

    public void setBrokenDeadPixelLiquidOfDisplayAndTouchScreen(String brokenDeadPixelLiquidMark) {
        editor.putString("brokenDeadPixelLiquidMark", brokenDeadPixelLiquidMark);
        editor.commit();
    }

    public String getBrokenDeadPixelLiquidOfDisplayAndTouchScreen() {
        return pref.getString("brokenDeadPixelLiquidMark", "");
    }

    /*---------------------Accessories Details----------------*/
    public void setEarphoneOfAccessoriesDetails(String earphone) {
        editor.putString("earphone", earphone);
        editor.commit();
    }

    public String getEarphoneOfAccessoriesDetails() {
        return pref.getString("earphone", "");
    }

    public void setBoxWithSameImeiOfAccessoriesDetails(String boxWithSameImei) {
        editor.putString("boxWithSameImei", boxWithSameImei);
        editor.commit();
    }

    public String getBoxWithSameImeiOfAccessoriesDetails() {
        return pref.getString("boxWithSameImei", "");
    }

    public void setOriginalChargerOfAccessoriesDetails(String originalCharger) {
        editor.putString("originalCharger", originalCharger);
        editor.commit();
    }

    public String getOriginalChargerOfAccessoriesDetails() {
        return pref.getString("originalCharger", "");
    }

    /*-------------------Issue Details-----------------*/
    public void setVolumeNotWorkingOfIssueDetails(String volumeButtonNotWorking) {
        editor.putString("volumeButtonNotWorking", volumeButtonNotWorking);
        editor.commit();
    }

    public String getVolumeNotWorkingOfIssueDetails() {
        return pref.getString("volumeButtonNotWorking", "");
    }

    public void setPowerHomeButtonFaultyOfIssueDetails(String powerHomeButtonFaultyOfIssueDetails) {
        editor.putString("powerHomeButtonFaultyOfIssueDetails", powerHomeButtonFaultyOfIssueDetails);
        editor.commit();
    }

    public String getPowerHomeButtonFaultyOfIssueDetails() {
        return pref.getString("powerHomeButtonFaultyOfIssueDetails", "");
    }

    public void setWifiBlueToothGPSOfIssueDetails(String wifiBlueToothGPSOfIssueDetails) {
        editor.putString("wifiBlueToothGPSOfIssueDetails", wifiBlueToothGPSOfIssueDetails);
        editor.commit();
    }

    public String getWifiBlueToothGPSOfIssueDetails() {
        return pref.getString("wifiBlueToothGPSOfIssueDetails", "");
    }

    public void setChargingDefectOfIssueDetails(String chargingDefectOfIssueDetails) {
        editor.putString("chargingDefectOfIssueDetails", chargingDefectOfIssueDetails);
        editor.commit();
    }

    public String getChargingDefectOfIssueDetails() {
        return pref.getString("chargingDefectOfIssueDetails", "");
    }

    public void setBatteryFaultyLowOfIssueDetails(String batteryFaultyLowOfIssueDetails) {
        editor.putString("batteryFaultyLowOfIssueDetails", batteryFaultyLowOfIssueDetails);
        editor.commit();
    }

    public String getBatteryFaultyLowOfIssueDetails() {
        return pref.getString("batteryFaultyLowOfIssueDetails", "");
    }

    public void setSpeakerNotWorkingOfIssueDetails(String speakerNotWorkingOfIssueDetails) {
        editor.putString("speakerNotWorkingOfIssueDetails", speakerNotWorkingOfIssueDetails);
        editor.commit();
    }

    public String getSpeakerNotWorkingOfIssueDetails() {
        return pref.getString("speakerNotWorkingOfIssueDetails", "");
    }

    public void setMicrophoneNotWorkingOfIssueDetails(String microphoneNotWorkingOfIssueDetails) {
        editor.putString("microphoneNotWorkingOfIssueDetails", microphoneNotWorkingOfIssueDetails);
        editor.commit();
    }

    public String getMicrophoneNotWorkingOfIssueDetails() {
        return pref.getString("microphoneNotWorkingOfIssueDetails", "");
    }

    public void setGSMCallFunctionNotWorkingOfIssueDetails(String gsmCallFunctionNotWorkingOfIssueDetails) {
        editor.putString("gsmCallFunctionNotWorkingOfIssueDetails", gsmCallFunctionNotWorkingOfIssueDetails);
        editor.commit();
    }

    public String getGSMCallFunctionNotWorkingOfIssueDetails() {
        return pref.getString("gsmCallFunctionNotWorkingOfIssueDetails", "");
    }

    public void setEarphoneJackNotWorkingOfIssueDetails(String earphoneJackNotWorkingOfIssueDetails) {
        editor.putString("earphoneJackNotWorkingOfIssueDetails", earphoneJackNotWorkingOfIssueDetails);
        editor.commit();
    }

    public String getEarphoneJackNotWorkingOfIssueDetails() {
        return pref.getString("earphoneJackNotWorkingOfIssueDetails", "");
    }

    public void setFingerprintSensorNotWorkingOfIssueDetails(String fingerprintSensorNotWorkingOfIssueDetails) {
        editor.putString("fingerprintSensorNotWorkingOfIssueDetails", fingerprintSensorNotWorkingOfIssueDetails);
        editor.commit();
    }

    public String getFingerprintSensorNotWorkingOfIssueDetails() {
        return pref.getString("fingerprintSensorNotWorkingOfIssueDetails", "");
    }

    /*--------------------Repair Details---------------*/
    public void setRepairDetailsYesNo(String repairDetailsYesNo) {
        editor.putString("repairDetailsYesNo", repairDetailsYesNo);
        editor.commit();
    }

    public String getRepairDetailsYesNo() {
        return pref.getString("repairDetailsYesNo", "");
    }

    /*---------------------Brand Utilized-----------------*/
    public void setDeviceHasUndergoneRepairsMonths(String monthsValue) {
        editor.putString("monthsValue", monthsValue);
        editor.commit();
    }

    public String getDeviceHasUndergoneRepairsMonths() {
        return pref.getString("monthsValue", "");
    }

    /*----------------------Device Body details-----------------*/
    public void setFlawlessOfDeviceBodyDetails(String flawlessOfDeviceBodyDetails) {
        editor.putString("flawlessOfDeviceBodyDetails", flawlessOfDeviceBodyDetails);
        editor.commit();
    }

    public String getFlawlessOfDeviceBodyDetails() {
        return pref.getString("flawlessOfDeviceBodyDetails", "");
    }

    public void setScratchedOfDeviceBodyDetails(String scratchedOfDeviceBodyDetails) {
        editor.putString("scratchedOfDeviceBodyDetails", scratchedOfDeviceBodyDetails);
        editor.commit();
    }

    public String getScratchedOfDeviceBodyDetails() {
        return pref.getString("scratchedOfDeviceBodyDetails", "");
    }

    public void setCrackedOfDeviceBodyDetails(String crackedOfDeviceBodyDetails) {
        editor.putString("crackedOfDeviceBodyDetails", crackedOfDeviceBodyDetails);
        editor.commit();
    }

    public String getCrackedOfDeviceBodyDetails() {
        return pref.getString("crackedOfDeviceBodyDetails", "");
    }

    public void setBrokenOfDeviceBodyDetails(String brokenOfDeviceBodyDetails) {
        editor.putString("brokenOfDeviceBodyDetails", brokenOfDeviceBodyDetails);
        editor.commit();
    }

    public String getBrokenOfDeviceBodyDetails() {
        return pref.getString("brokenOfDeviceBodyDetails", "");
    }

    public void setOkOfDeviceBodyDetails(String okOfDeviceBodyDetails) {
        editor.putString("okOfDeviceBodyDetails", okOfDeviceBodyDetails);
        editor.commit();
    }

    public String getOkOfDeviceBodyDetails() {
        return pref.getString("okOfDeviceBodyDetails", "");
    }

    public void setLooseOfDeviceBodyDetails(String looseOfDeviceBodyDetails) {
        editor.putString("looseOfDeviceBodyDetails", looseOfDeviceBodyDetails);
        editor.commit();
    }

    public String getLooseOfDeviceBodyDetails() {
        return pref.getString("looseOfDeviceBodyDetails", "");
    }

    public void setMissingOfDeviceBodyDetails(String missingOfDeviceBodyDetails) {
        editor.putString("missingOfDeviceBodyDetails", missingOfDeviceBodyDetails);
        editor.commit();
    }

    public String getMissingOfDeviceBodyDetails() {
        return pref.getString("missingOfDeviceBodyDetails", "");
    }

    /*---------------------Around Screen Details, Silver Frame/Bezel(Around screen)------------------*/
    public void setOkOfBezel(String okOfBezel) {
        editor.putString("okOfBezel", okOfBezel);
        editor.commit();
    }

    public String getOkOfBezel() {
        return pref.getString("okOfBezel", "");
    }

    public void setDiscoloredOfBezel(String discoloredOfBezel) {
        editor.putString("discoloredOfBezel", discoloredOfBezel);
        editor.commit();
    }

    public String getDiscoloredOfBezel() {
        return pref.getString("discoloredOfBezel", "");
    }

    public void setDentedOfBezel(String dentedOfBezel) {
        editor.putString("dentedOfBezel", dentedOfBezel);
        editor.commit();
    }

    public String getDentedOfBezel() {
        return pref.getString("dentedOfBezel", "");
    }

    public void setBrokenOfBezel(String brokenOfBezel) {
        editor.putString("brokenOfBezel", brokenOfBezel);
        editor.commit();
    }

    public String getBrokenOfBezel() {
        return pref.getString("brokenOfBezel", "");
    }

    /*--------------------------Camera Glass Details-----------------------*/
    public void setOkOfCamera(String okOfCamera) {
        editor.putString("okOfCamera", okOfCamera);
        editor.commit();
    }

    public String getOkOfCamera() {
        return pref.getString("okOfCamera", "");
    }

    public void setScratchedOfCamera(String scratchedOfCamera) {
        editor.putString("scratchedOfCamera", scratchedOfCamera);
        editor.commit();
    }

    public String getScratchedOfCamera() {
        return pref.getString("scratchedOfCamera", "");
    }

    public void setBlurOfCamera(String blurOfCamera) {
        editor.putString("blurOfCamera", blurOfCamera);
        editor.commit();
    }

    public String getBlurOfCamera() {
        return pref.getString("blurOfCamera", "");
    }

    public void setCrackedOfCamera(String crackedOfCamera) {
        editor.putString("crackedOfCamera", crackedOfCamera);
        editor.commit();
    }

    public String getCrackedOfCamera() {
        return pref.getString("crackedOfCamera", "");
    }

    public void setBrokenOfCamera(String brokenOfCamera) {
        editor.putString("brokenOfCamera", brokenOfCamera);
        editor.commit();
    }

    public String getBrokenOfCamera() {
        return pref.getString("brokenOfCamera", "");
    }

    public void setPageDirectionStatus(String pageDirectionStatus) {
        editor.putString("pageDirectionStatus", pageDirectionStatus);
        editor.commit();
    }

    public String getPageDirectionStatus() {
        return pref.getString("pageDirectionStatus", "");
    }

    public void setSingleClick(String singleClick) {
        editor.putString("singleClick", singleClick);
        editor.commit();
    }

    public String getSingleClick() {
        return pref.getString("singleClick", "");
    }

    public void setProductName(String productName) {
        editor.putString("productName", productName);
        editor.commit();
    }

    public String getProductName() {
        return pref.getString("productName", "");
    }

    public void setProductImgUrl(String productImgUrl) {
        editor.putString("productImgUrl", productImgUrl);
        editor.commit();
    }

    public String getProductImgUrl() {
        return pref.getString("productImgUrl", "");
    }

    public void setOrderStatusId(int orderStatusId) {
        editor.putInt("orderStatusId", orderStatusId);
        editor.commit();
    }

    public int getOrderStatusId() {
        return pref.getInt("orderStatusId", 0);
    }

    public void setFirstImagePath(String firstImagePath) {
        editor.putString("firstImagePath", firstImagePath);
        editor.commit();
    }

    public String getFirstImagePath() {
        return pref.getString("firstImagePath", "");
    }

    public void setSecondImagePath(String secondImagePath) {
        editor.putString("secondImagePath", secondImagePath);
        editor.commit();
    }

    public String getSecondImagePath() {
        return pref.getString("secondImagePath", "");
    }

    public void setThirdImagePath(String thirdImagePath) {
        editor.putString("thirdImagePath", thirdImagePath);
        editor.commit();
    }

    public String getThirdImagePath() {
        return pref.getString("thirdImagePath", "");
    }

    public void setFourthImagePath(String fourthImagePath) {
        editor.putString("fourthImagePath", fourthImagePath);
        editor.commit();
    }

    public String getFourthImagePath() {
        return pref.getString("fourthImagePath", "");
    }

    public void setCurrentAddress(String currentAddress) {
        editor.putString("currentAddress", currentAddress);
        editor.commit();
    }

    public String getCurrentAddress() {
        return pref.getString("currentAddress", "");
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
    }

    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}