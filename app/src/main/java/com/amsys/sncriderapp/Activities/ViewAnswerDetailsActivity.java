package com.amsys.sncriderapp.Activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amsys.sncriderapp.R;
import com.amsys.sncriderapp.Utilities.UserSession;

import org.json.JSONObject;

public class ViewAnswerDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private UserSession userSession;
    private ImageView backAnswerImg;
    private TextView deviceConditionDetailsAns, flawlessConditionDetailsFlawlessAns, minorScratches, shadedWhiteDots;
    private TextView brokenDeadPixel, earphone, boxWithSameIMEI, originalCharger, volumeNotWorking, powerHomeNotWorking;
    private TextView wifiBluetoothWorking, chargingDefect, batteryFaulty, speakerNotWorking, microphoneNot;
    private TextView gsmCallFunction, earphoneJackDamaged, fingerprintSensor, repairDetailsAns, brandUtilizedAns;
    private TextView flawlessDeviceBodyDetailsAns, scratches, cracked, broken, okay, loose, missing;
    private TextView okBezelAns, discoloredBezel, dentedBezel, brokenBezel, okCameraAns, scratchedCamera;
    private TextView blurCamera, crackedCamera, brokenCamera, questionairs;

    private TextView deviceOnOffAns, touchScreenAns, accessoriesAns, functionalIssueAns, repairAns;
    private TextView brandUtilizedAnsEd, deviceBodyAns, silverFrameAns, mainCameraAns;

    private LinearLayout doesYourDeviceSwitchOnLayout, displayAndTouchScreenLayout, availableAccessoriesLayout;
    private LinearLayout functionalIssueLayout, repairDetailsLayout, warrantyUtilizedLayout, deviceBodyLayout;
    private LinearLayout silverFrameLayout, mainCameraLayout;

    private String jsonIsDeviceOn = "", jsonFlawless = "", jsonMinorScratchesTwoOrThree = "", jsonShadedWhiteDots = "";
    private String jsonBrokenDead = "", jsonEarphone = "", jsonBoxWithSameIMEI = "", jsonOriginalCharger = "";
    private String jsonVolumeButton = "", jsonPowerHomeButton = "", jsonWifiBluetoothGPS = "", jsonChargingDefect = "";
    private String jsonBatteryFaulty = "", jsonSpeakersFaulty = "", jsonMicrophoneFaulty = "", jsonGsMFaulty = "";
    private String jsonEarphoneJackFaulty = "", jsonFingerPrintSensorFaulty = "", jsonCameraFaulty = "", jsonUndergoneRepairs = "";
    private String jsonWarrantyUtilizedMonth = "", jsonLessThanThreeMonths = "", jsonLessThanTenMonths = "", jsonMoreThanTenMonths = "";
    private String jsonNotAvailable = "", jsonDeviceFlawless = "", jsonScratched = "", jsonBroken = "", jsonLoose = "", jsonMissing = "";
    private String jsonOkay = "", jsonDiscolored = "", jsonDented = "", jsonSilverBroken = "", jsonOkayFlawless = "", jsonMainScratched = "";
    private String jsonBlur = "", jsonMainBroken = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_answer_details);
        mContext = this;
        userSession = new UserSession(mContext);

        getViewAnswerLayoutUiIdFind();
        getJsonParseData();

        System.out.println("Device On Off :- " + userSession.getDeviceOnOffJson());
        System.out.println("Display Touch :- " + userSession.getDisplayTouchJson());
        System.out.println("Accessories :- " + userSession.getAvailableAccessoriesJson());
        System.out.println("Functional Issue :- " + userSession.getFunctionalIssueJson());
        System.out.println("Repair :- " + userSession.getRepairDetailsJson());
        System.out.println("Warranty Utilized :- " + userSession.getBrandWarrantyUtilizedJson());
        System.out.println("Device Body :- " + userSession.getBodyInformationJson());
        System.out.println("Silver Frame :- " + userSession.getSilverFrameBezelJson());
        System.out.println("Main Camera :- " + userSession.getMainCameraJson());
        System.out.println("Aashish Vishavkarma");

        backAnswerImg.setOnClickListener(this);

        getDataForSetInTextView();
        getDataForSetInTextViewByEndUser();

    }

    private void getJsonParseData() {
        try {
            System.out.println("Device On Off By End User :- " + userSession.getDeviceOnOffEndUserJson());
            System.out.println("Display Touch By End User :- " + userSession.getDisplayTouchEndUserJson());
            System.out.println("Accessories By End User :- " + userSession.getAvailableAccessoriesEndUserJson());
            System.out.println("Functional Issue By End User :- " + userSession.getFunctionalIssueEndUserJson());
            System.out.println("Repair By End User :- " + userSession.getRepairDetailsEndUserJson());
            System.out.println("Warranty Utilized By End User :- " + userSession.getBrandWarrantyUtilizedEndUserJson());
            System.out.println("Device Body By End User :- " + userSession.getBodyInformationEndUserJson());
            System.out.println("Silver Frame By End User :- " + userSession.getSilverFrameBezelEndUserJson());
            System.out.println("Main Camera By End User :- " + userSession.getMainCameraEndUserJson());

            JSONObject jObjectDeviceOnOff = new JSONObject(userSession.getDeviceOnOffEndUserJson());
            JSONObject jObjectDisplayTouch = new JSONObject(userSession.getDisplayTouchEndUserJson());
            JSONObject jObjectAccessories = new JSONObject(userSession.getAvailableAccessoriesEndUserJson());
            JSONObject jObjectFunctionalIssue = new JSONObject(userSession.getFunctionalIssueEndUserJson());
            JSONObject jObjectRepair = new JSONObject(userSession.getRepairDetailsEndUserJson());
            JSONObject jObjectWarrantyUtilized = new JSONObject(userSession.getBrandWarrantyUtilizedEndUserJson());
            JSONObject jObjectDeviceBody = new JSONObject(userSession.getBodyInformationEndUserJson());
            JSONObject jObjectSilverFrame = new JSONObject(userSession.getSilverFrameBezelEndUserJson());
            JSONObject jObjectMainCamera = new JSONObject(userSession.getMainCameraEndUserJson());

            jsonIsDeviceOn = jObjectDeviceOnOff.getString("isDeviceOn");

            jsonFlawless = jObjectDisplayTouch.getString("flawless");
            jsonMinorScratchesTwoOrThree = jObjectDisplayTouch.getString("minorScratchesTwoOrThree");
            jsonShadedWhiteDots = jObjectDisplayTouch.getString("shaded_WhiteDots");
            jsonBrokenDead = jObjectDisplayTouch.getString("broken_Dead");

            jsonEarphone = jObjectAccessories.getString("earphone");
            jsonBoxWithSameIMEI = jObjectAccessories.getString("box_with_same_IMEI");
            jsonOriginalCharger = jObjectAccessories.getString("original_Charger");

            jsonVolumeButton = jObjectFunctionalIssue.getString("volume_Button");
            jsonPowerHomeButton = jObjectFunctionalIssue.getString("power_Home_Button");
            jsonWifiBluetoothGPS = jObjectFunctionalIssue.getString("wifi_Bluetooth_GPS");
            jsonChargingDefect = jObjectFunctionalIssue.getString("charging_Defect");
            jsonBatteryFaulty = jObjectFunctionalIssue.getString("battery_Faulty");
            jsonSpeakersFaulty = jObjectFunctionalIssue.getString("speakers_Faulty");
            jsonMicrophoneFaulty = jObjectFunctionalIssue.getString("microphone_Faulty");
            jsonGsMFaulty = jObjectFunctionalIssue.getString("gsM_Faulty");
            jsonEarphoneJackFaulty = jObjectFunctionalIssue.getString("earphone_Jack_Faulty");
            jsonFingerPrintSensorFaulty = jObjectFunctionalIssue.getString("fingerprint_Sensor_Faulty");
            jsonCameraFaulty = jObjectFunctionalIssue.getString("camera_Faulty");

            jsonUndergoneRepairs = jObjectRepair.getString("undergone_repairs");

            jsonWarrantyUtilizedMonth = jObjectWarrantyUtilized.getString("warranty_Utilized_Month");
            jsonLessThanThreeMonths = jObjectWarrantyUtilized.getString("lessThanThreeMonths");
            jsonLessThanTenMonths = jObjectWarrantyUtilized.getString("lessThanTenMonths");
            jsonMoreThanTenMonths = jObjectWarrantyUtilized.getString("moreThanTenMonths");
            jsonNotAvailable = jObjectWarrantyUtilized.getString("notAvailable");

            jsonDeviceFlawless = jObjectDeviceBody.getString("flawless");
            jsonScratched = jObjectDeviceBody.getString("scratched");
            jsonBroken = jObjectDeviceBody.getString("broken");
            jsonLoose = jObjectDeviceBody.getString("loose");
            jsonMissing = jObjectDeviceBody.getString("missing");

            jsonOkay = jObjectSilverFrame.getString("okay");
            jsonDiscolored = jObjectSilverFrame.getString("discolored");
            jsonDented = jObjectSilverFrame.getString("dented");
            jsonSilverBroken = jObjectSilverFrame.getString("broken");

            jsonOkayFlawless = jObjectMainCamera.getString("okayFlawless");
            jsonMainScratched = jObjectMainCamera.getString("scratched");
            jsonBlur = jObjectMainCamera.getString("blur");
            jsonMainBroken = jObjectMainCamera.getString("broken");

        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    private void getDataForSetInTextViewByEndUser() {
        try {
            if (jsonIsDeviceOn.equalsIgnoreCase("true")) {
                deviceOnOffAns.setText("By EndUser :- Yes");
            } else {
                deviceOnOffAns.setText("By EndUser :- No");
            }

            if (jsonFlawless.equalsIgnoreCase("true")) {
                touchScreenAns.setText("By EndUser :- Flawless");
            } else if (jsonMinorScratchesTwoOrThree.equalsIgnoreCase("true")) {
                touchScreenAns.setText("By EndUser :- 2-3 Minor Scratches");
            } else if (jsonShadedWhiteDots.equalsIgnoreCase("true")) {
                touchScreenAns.setText("By EndUser :- Shaded/White Dots");
            } else if (jsonBrokenDead.equalsIgnoreCase("true")) {
                touchScreenAns.setText("By EndUser :- Broken Dead Pixel Liquid Mark or Dots not work properly");
            } else if (jsonFlawless.equalsIgnoreCase("true") &&
                    jsonMinorScratchesTwoOrThree.equalsIgnoreCase("true")) {
                touchScreenAns.setText("By EndUser :- Flawless, 2-3 Minor Scratches");
            } else if (jsonFlawless.equalsIgnoreCase("true") &&
                    jsonMinorScratchesTwoOrThree.equalsIgnoreCase("true") &&
                    jsonShadedWhiteDots.equalsIgnoreCase("true")) {
                touchScreenAns.setText("By EndUser :- Flawless, 2-3 Minor Scratches, Shaded/White Dots");
            } else if (jsonFlawless.equalsIgnoreCase("true") &&
                    jsonMinorScratchesTwoOrThree.equalsIgnoreCase("true") &&
                    jsonShadedWhiteDots.equalsIgnoreCase("true") &&
                    jsonBrokenDead.equalsIgnoreCase("true")) {
                touchScreenAns.setText("By EndUser :- Flawless, 2-3 Minor Scratches, Shaded/White Dots, Broken Dead Pixel Liquid Mark or Dots not work properly");
            } else {
                touchScreenAns.setText("By EndUser :- N/A");
            }

            if (jsonEarphone.equalsIgnoreCase("true")) {
                accessoriesAns.setText("By EndUser :- Earphone");
            } else if (jsonBoxWithSameIMEI.equalsIgnoreCase("true")) {
                accessoriesAns.setText("By EndUser :- Box with same IMEI");
            } else if (jsonOriginalCharger.equalsIgnoreCase("true")) {
                accessoriesAns.setText("By EndUser :- Original Charger");
            } else if (jsonEarphone.equalsIgnoreCase("true")
                    && jsonBoxWithSameIMEI.equalsIgnoreCase("true")) {
                accessoriesAns.setText("By EndUser :- Earphone, Box with same IMEI");
            } else if (jsonEarphone.equalsIgnoreCase("true")
                    && jsonBoxWithSameIMEI.equalsIgnoreCase("true")
                    && jsonOriginalCharger.equalsIgnoreCase("true")) {
                accessoriesAns.setText("By EndUser :- Earphone, Box with same IMEI, Original Charger");
            } else {
                accessoriesAns.setText("By EndUser :- N/A");
            }

            if (jsonVolumeButton.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("By EndUser :- Volume button not working");
            } else if (jsonPowerHomeButton.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("By EndUser :- Power/Home Button faulty: Hard or not working");
            } else if (jsonWifiBluetoothGPS.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("By EndUser :- Wifi or Bluetooth Or GPS Not Working");
            } else if (jsonChargingDefect.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("By EndUser :- Charging Defect: Unable to charge the phone");
            } else if (jsonBatteryFaulty.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("By EndUser :- Battery Faulty or Very Low Battery Back up");
            } else if (jsonSpeakersFaulty.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("By EndUser :- Speaker not working: faulty Or cracked sound");
            } else if (jsonMicrophoneFaulty.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("By EndUser :- Microphone Not Working");
            } else if (jsonGsMFaulty.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("By EndUser :- GSM(Call Function) is not-working normally");
            } else if (jsonEarphoneJackFaulty.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("By EndUser :- Earphone Jack is damaged or not-working");
            } else if (jsonFingerPrintSensorFaulty.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("By EndUser :- Fingerprint Sensor Not-working");
            } else if (jsonVolumeButton.equalsIgnoreCase("true") &&
                    jsonPowerHomeButton.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("By EndUser :- Volume button not working, Power/Home Button faulty: Hard or not working");
            } else if (jsonVolumeButton.equalsIgnoreCase("true") &&
                    jsonPowerHomeButton.equalsIgnoreCase("true") &&
                    jsonWifiBluetoothGPS.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("By EndUser :- Volume button not working, " +
                        "Power/Home Button faulty: Hard or not working, " +
                        "Wifi or Bluetooth Or GPS Not Working");
            } else if (jsonVolumeButton.equalsIgnoreCase("true") &&
                    jsonPowerHomeButton.equalsIgnoreCase("true") &&
                    jsonWifiBluetoothGPS.equalsIgnoreCase("true") &&
                    jsonChargingDefect.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("By EndUser :- Volume button not working, " +
                        "Power/Home Button faulty: Hard or not working, " +
                        "Wifi or Bluetooth Or GPS Not Working, " +
                        "Charging Defect: Unable to charge the phone");
            } else if (jsonVolumeButton.equalsIgnoreCase("true") &&
                    jsonPowerHomeButton.equalsIgnoreCase("true") &&
                    jsonWifiBluetoothGPS.equalsIgnoreCase("true") &&
                    jsonChargingDefect.equalsIgnoreCase("true") &&
                    jsonBatteryFaulty.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("By EndUser :- Volume button not working, " +
                        "Power/Home Button faulty: Hard or not working, " +
                        "Wifi or Bluetooth Or GPS Not Working, " +
                        "Charging Defect: Unable to charge the phone, " +
                        "Battery Faulty or Very Low Battery Back up");
            } else if (jsonVolumeButton.equalsIgnoreCase("true") &&
                    jsonPowerHomeButton.equalsIgnoreCase("true") &&
                    jsonWifiBluetoothGPS.equalsIgnoreCase("true") &&
                    jsonChargingDefect.equalsIgnoreCase("true") &&
                    jsonBatteryFaulty.equalsIgnoreCase("true") &&
                    jsonSpeakersFaulty.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("By EndUser :- Volume button not working, " +
                        "Power/Home Button faulty: Hard or not working, " +
                        "Wifi or Bluetooth Or GPS Not Working, " +
                        "Charging Defect: Unable to charge the phone, " +
                        "Battery Faulty or Very Low Battery Back up, " +
                        "Speaker not working: faulty Or cracked sound");
            } else if (jsonVolumeButton.equalsIgnoreCase("true") &&
                    jsonPowerHomeButton.equalsIgnoreCase("true") &&
                    jsonWifiBluetoothGPS.equalsIgnoreCase("true") &&
                    jsonChargingDefect.equalsIgnoreCase("true") &&
                    jsonBatteryFaulty.equalsIgnoreCase("true") &&
                    jsonSpeakersFaulty.equalsIgnoreCase("true") &&
                    jsonMicrophoneFaulty.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("By EndUser :- Volume button not working, " +
                        "Power/Home Button faulty: Hard or not working, " +
                        "Wifi or Bluetooth Or GPS Not Working, " +
                        "Charging Defect: Unable to charge the phone, " +
                        "Battery Faulty or Very Low Battery Back up, " +
                        "Speaker not working: faulty Or cracked sound, " +
                        "Microphone Not Working");
            } else if (jsonVolumeButton.equalsIgnoreCase("true") &&
                    jsonPowerHomeButton.equalsIgnoreCase("true") &&
                    jsonWifiBluetoothGPS.equalsIgnoreCase("true") &&
                    jsonChargingDefect.equalsIgnoreCase("true") &&
                    jsonBatteryFaulty.equalsIgnoreCase("true") &&
                    jsonSpeakersFaulty.equalsIgnoreCase("true") &&
                    jsonMicrophoneFaulty.equalsIgnoreCase("true") &&
                    jsonGsMFaulty.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("By EndUser :- Volume button not working, " +
                        "Power/Home Button faulty: Hard or not working, " +
                        "Wifi or Bluetooth Or GPS Not Working, " +
                        "Charging Defect: Unable to charge the phone, " +
                        "Battery Faulty or Very Low Battery Back up, " +
                        "Speaker not working: faulty Or cracked sound, " +
                        "Microphone Not Working, GSM(Call Function) is not-working normally");
            } else if (jsonVolumeButton.equalsIgnoreCase("true") &&
                    jsonPowerHomeButton.equalsIgnoreCase("true") &&
                    jsonWifiBluetoothGPS.equalsIgnoreCase("true") &&
                    jsonChargingDefect.equalsIgnoreCase("true") &&
                    jsonBatteryFaulty.equalsIgnoreCase("true") &&
                    jsonSpeakersFaulty.equalsIgnoreCase("true") &&
                    jsonMicrophoneFaulty.equalsIgnoreCase("true") &&
                    jsonGsMFaulty.equalsIgnoreCase("true") &&
                    jsonEarphoneJackFaulty.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("By EndUser :- Volume button not working, " +
                        "Power/Home Button faulty: Hard or not working, " +
                        "Wifi or Bluetooth Or GPS Not Working, " +
                        "Charging Defect: Unable to charge the phone, " +
                        "Battery Faulty or Very Low Battery Back up, " +
                        "Speaker not working: faulty Or cracked sound, " +
                        "Microphone Not Working, GSM(Call Function) is not-working normally, " +
                        "Earphone Jack is damaged or not-working");
            } else if (jsonVolumeButton.equalsIgnoreCase("true") &&
                    jsonPowerHomeButton.equalsIgnoreCase("true") &&
                    jsonWifiBluetoothGPS.equalsIgnoreCase("true") &&
                    jsonChargingDefect.equalsIgnoreCase("true") &&
                    jsonBatteryFaulty.equalsIgnoreCase("true") &&
                    jsonSpeakersFaulty.equalsIgnoreCase("true") &&
                    jsonMicrophoneFaulty.equalsIgnoreCase("true") &&
                    jsonGsMFaulty.equalsIgnoreCase("true") &&
                    jsonEarphoneJackFaulty.equalsIgnoreCase("true") &&
                    jsonFingerPrintSensorFaulty.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("By EndUser :- Volume button not working, " +
                        "Power/Home Button faulty: Hard or not working, " +
                        "Wifi or Bluetooth Or GPS Not Working, " +
                        "Charging Defect: Unable to charge the phone, " +
                        "Battery Faulty or Very Low Battery Back up, " +
                        "Speaker not working: faulty Or cracked sound, " +
                        "Microphone Not Working, GSM(Call Function) is not-working normally, " +
                        "Earphone Jack is damaged or not-working, Fingerprint Sensor Not-working");
            } else {
                functionalIssueAns.setText("By EndUser :- N/A");
            }

            if (jsonUndergoneRepairs.equalsIgnoreCase("true")) {
                repairAns.setText("By EndUser :- Yes");
            } else {
                repairAns.setText("By EndUser :- N/A");
            }

            if (jsonLessThanThreeMonths.equalsIgnoreCase("true")) {
                brandUtilizedAnsEd.setText("By EndUser :- 0 to 3 months");
            } else if (jsonLessThanTenMonths.equalsIgnoreCase("true")) {
                brandUtilizedAnsEd.setText("By EndUser :- 3 to 10 months");
            } else if (jsonMoreThanTenMonths.equalsIgnoreCase("true")) {
                brandUtilizedAnsEd.setText("By EndUser :- More than 10 Months");
            } else if (jsonNotAvailable.equalsIgnoreCase("true")) {
                brandUtilizedAnsEd.setText("By EndUser :- Not available");
            } else {
                brandUtilizedAnsEd.setText("By EndUser :- N/A");
            }

            if (jsonDeviceFlawless.equalsIgnoreCase("true")) {
                deviceBodyAns.setText("By EndUser :- Flawless");
            } else if (jsonScratched.equalsIgnoreCase("true")) {
                deviceBodyAns.setText("By EndUser :- Scratched");
            } else if (jsonBroken.equalsIgnoreCase("true")) {
                deviceBodyAns.setText("By EndUser :- Broken");
            } else if (jsonLoose.equalsIgnoreCase("true")) {
                deviceBodyAns.setText("By EndUser :- Loose");
            } else if (jsonMissing.equalsIgnoreCase("true")) {
                deviceBodyAns.setText("By EndUser :- Missing");
            } else if (jsonDeviceFlawless.equalsIgnoreCase("true") &&
                    jsonScratched.equalsIgnoreCase("true")) {
                deviceBodyAns.setText("By EndUser :- Flawless, Scratched");
            } else if (jsonDeviceFlawless.equalsIgnoreCase("true") &&
                    jsonScratched.equalsIgnoreCase("true") &&
                    jsonBroken.equalsIgnoreCase("true")) {
                deviceBodyAns.setText("By EndUser :- Flawless, Scratched, Broken");
            } else if (jsonDeviceFlawless.equalsIgnoreCase("true") &&
                    jsonScratched.equalsIgnoreCase("true") &&
                    jsonBroken.equalsIgnoreCase("true") &&
                    jsonLoose.equalsIgnoreCase("true")) {
                deviceBodyAns.setText("By EndUser :- Flawless, Scratched, Broken, Loose");
            } else if (jsonDeviceFlawless.equalsIgnoreCase("true") &&
                    jsonScratched.equalsIgnoreCase("true") &&
                    jsonBroken.equalsIgnoreCase("true") &&
                    jsonLoose.equalsIgnoreCase("true") &&
                    jsonMissing.equalsIgnoreCase("true")) {
                deviceBodyAns.setText("By EndUser :- Flawless, Scratched, Broken, Loose, Missing");
            } else {
                deviceBodyAns.setText("By EndUser :- N/A");
            }

            if (jsonOkay.equalsIgnoreCase("true")) {
                silverFrameAns.setText("By EndUser :- Okay");
            } else if (jsonDiscolored.equalsIgnoreCase("true")) {
                silverFrameAns.setText("By EndUser :- Discolored");
            } else if (jsonDented.equalsIgnoreCase("true")) {
                silverFrameAns.setText("By EndUser :- Dented");
            } else if (jsonSilverBroken.equalsIgnoreCase("true")) {
                silverFrameAns.setText("By EndUser :- Broken");
            } else if (jsonOkay.equalsIgnoreCase("true") && jsonDiscolored.equalsIgnoreCase("true")) {
                silverFrameAns.setText("By EndUser :- Okay, Discolored");
            } else if (jsonOkay.equalsIgnoreCase("true")
                    && jsonDiscolored.equalsIgnoreCase("true")
                    && jsonDented.equalsIgnoreCase("true")) {
                silverFrameAns.setText("By EndUser :- Okay, Discolored, Dented");
            } else if (jsonOkay.equalsIgnoreCase("true")
                    && jsonDiscolored.equalsIgnoreCase("true")
                    && jsonDented.equalsIgnoreCase("true")
                    && jsonSilverBroken.equalsIgnoreCase("true")) {
                silverFrameAns.setText("By EndUser :- Okay, Discolored, Dented, Broken");
            } else {
                silverFrameAns.setText("By EndUser :- N/A");
            }

            if (jsonOkayFlawless.equalsIgnoreCase("true")) {
                mainCameraAns.setText("By EndUser :- Okay");
            } else if (jsonMainScratched.equalsIgnoreCase("true")) {
                mainCameraAns.setText("By EndUser :- Scratched");
            } else if (jsonBlur.equalsIgnoreCase("true")) {
                mainCameraAns.setText("By EndUser :- Blur");
            } else if (jsonMainBroken.equalsIgnoreCase("true")) {
                mainCameraAns.setText("By EndUser :- Broken");
            } else if (jsonOkayFlawless.equalsIgnoreCase("true") && jsonMainScratched.equalsIgnoreCase("true")) {
                mainCameraAns.setText("By EndUser :- Okay, Scratched");
            } else if (jsonOkayFlawless.equalsIgnoreCase("true") && jsonMainScratched.equalsIgnoreCase("true") &&
                    jsonBlur.equalsIgnoreCase("true")) {
                mainCameraAns.setText("By EndUser :- Okay, Scratched, Blur");
            } else if (jsonOkayFlawless.equalsIgnoreCase("true") && jsonMainScratched.equalsIgnoreCase("true") &&
                    jsonBlur.equalsIgnoreCase("true") && jsonMainBroken.equalsIgnoreCase("true")) {
                mainCameraAns.setText("By EndUser :- Okay, Scratched, Blur, Broken");
            } else {
                mainCameraAns.setText("By EndUser :- N/A");
            }
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    private void getDataForSetInTextView() {
        try {
            System.out.println("Abcd :- " + userSession.getDeviceConditionYesNo());

            if (!userSession.getDeviceConditionYesNo().equalsIgnoreCase("")) {
                doesYourDeviceSwitchOnLayout.setVisibility(View.VISIBLE);
                questionairs.setVisibility(View.GONE);
                deviceConditionDetailsAns.setText("By Rider:- " + userSession.getDeviceConditionYesNo());
            } else {
                doesYourDeviceSwitchOnLayout.setVisibility(View.GONE);
                questionairs.setVisibility(View.VISIBLE);
            }

            if (!userSession.getRepairDetailsYesNo().equalsIgnoreCase("")) {
                repairDetailsLayout.setVisibility(View.VISIBLE);
                repairDetailsAns.setVisibility(View.VISIBLE);
                questionairs.setVisibility(View.GONE);
                repairDetailsAns.setText("By Rider:- " + userSession.getRepairDetailsYesNo());
            } else {
                repairDetailsLayout.setVisibility(View.GONE);
                repairDetailsAns.setVisibility(View.GONE);
                questionairs.setVisibility(View.GONE);
            }

            if (!userSession.getDeviceHasUndergoneRepairsMonths().equalsIgnoreCase("")) {
                warrantyUtilizedLayout.setVisibility(View.VISIBLE);
                brandUtilizedAns.setVisibility(View.VISIBLE);
                questionairs.setVisibility(View.GONE);
                brandUtilizedAns.setText("By Rider:- " + userSession.getDeviceHasUndergoneRepairsMonths());
            } else {
                warrantyUtilizedLayout.setVisibility(View.GONE);
                brandUtilizedAns.setVisibility(View.GONE);
                questionairs.setVisibility(View.GONE);
            }

            if (!userSession.getFlawlessValueOfDisplayAndTouchScreen().equalsIgnoreCase("")) {
                displayAndTouchScreenLayout.setVisibility(View.VISIBLE);
                questionairs.setVisibility(View.GONE);
                flawlessConditionDetailsFlawlessAns.setVisibility(View.VISIBLE);
                flawlessConditionDetailsFlawlessAns.setText("By Rider:- " + userSession.getFlawlessValueOfDisplayAndTouchScreen());
                if (displayAndTouchScreenLayout.getVisibility() == View.VISIBLE) {
                    if (!userSession.getMinorScratchesOfDisplayAndTouchScreen().equalsIgnoreCase("")) {
                        minorScratches.setVisibility(View.VISIBLE);
                        minorScratches.setText(userSession.getMinorScratchesOfDisplayAndTouchScreen());
                    } else {
                        minorScratches.setVisibility(View.GONE);
                    }

                    if (!userSession.getShadedWhiteDotsOfDisplayAndTouchScreen().equalsIgnoreCase("")) {
                        shadedWhiteDots.setVisibility(View.VISIBLE);
                        shadedWhiteDots.setText(userSession.getShadedWhiteDotsOfDisplayAndTouchScreen());
                    } else {
                        shadedWhiteDots.setVisibility(View.GONE);
                    }

                    if (!userSession.getBrokenDeadPixelLiquidOfDisplayAndTouchScreen().equalsIgnoreCase("")) {
                        brokenDeadPixel.setVisibility(View.VISIBLE);
                        brokenDeadPixel.setText(userSession.getBrokenDeadPixelLiquidOfDisplayAndTouchScreen());
                    } else {
                        brokenDeadPixel.setVisibility(View.GONE);
                    }
                }
            } else {
                displayAndTouchScreenLayout.setVisibility(View.GONE);
                flawlessConditionDetailsFlawlessAns.setVisibility(View.GONE);
                questionairs.setVisibility(View.GONE);
            }

            if (!userSession.getEarphoneOfAccessoriesDetails().equalsIgnoreCase("")) {
                availableAccessoriesLayout.setVisibility(View.VISIBLE);
                earphone.setVisibility(View.VISIBLE);
                questionairs.setVisibility(View.GONE);
                earphone.setText("By Rider:- " + userSession.getEarphoneOfAccessoriesDetails());
                if (availableAccessoriesLayout.getVisibility() == View.VISIBLE) {
                    if (!userSession.getBoxWithSameImeiOfAccessoriesDetails().equalsIgnoreCase("")) {
                        boxWithSameIMEI.setVisibility(View.VISIBLE);
                        boxWithSameIMEI.setText(userSession.getBoxWithSameImeiOfAccessoriesDetails());
                    } else {
                        boxWithSameIMEI.setVisibility(View.GONE);
                    }

                    if (!userSession.getOriginalChargerOfAccessoriesDetails().equalsIgnoreCase("")) {
                        originalCharger.setVisibility(View.VISIBLE);
                        originalCharger.setText(userSession.getOriginalChargerOfAccessoriesDetails());
                    } else {
                        originalCharger.setVisibility(View.GONE);
                    }
                }
            } else {
                availableAccessoriesLayout.setVisibility(View.GONE);
                earphone.setVisibility(View.GONE);
                questionairs.setVisibility(View.GONE);
            }

            if (!userSession.getVolumeNotWorkingOfIssueDetails().equalsIgnoreCase("")) {
                functionalIssueLayout.setVisibility(View.VISIBLE);
                volumeNotWorking.setVisibility(View.VISIBLE);
                questionairs.setVisibility(View.GONE);
                volumeNotWorking.setText("By Rider:- " + userSession.getVolumeNotWorkingOfIssueDetails());
                if (functionalIssueLayout.getVisibility() == View.VISIBLE) {
                    if (!userSession.getPowerHomeButtonFaultyOfIssueDetails().equalsIgnoreCase("")) {
                        powerHomeNotWorking.setVisibility(View.VISIBLE);
                        powerHomeNotWorking.setText(userSession.getPowerHomeButtonFaultyOfIssueDetails());
                    } else {
                        powerHomeNotWorking.setVisibility(View.GONE);
                    }

                    if (!userSession.getWifiBlueToothGPSOfIssueDetails().equalsIgnoreCase("")) {
                        wifiBluetoothWorking.setVisibility(View.VISIBLE);
                        wifiBluetoothWorking.setText(userSession.getWifiBlueToothGPSOfIssueDetails());
                    } else {
                        wifiBluetoothWorking.setVisibility(View.GONE);
                    }

                    if (!userSession.getChargingDefectOfIssueDetails().equalsIgnoreCase("")) {
                        chargingDefect.setVisibility(View.VISIBLE);
                        chargingDefect.setText(userSession.getChargingDefectOfIssueDetails());
                    } else {
                        chargingDefect.setVisibility(View.GONE);
                    }

                    if (!userSession.getBatteryFaultyLowOfIssueDetails().equalsIgnoreCase("")) {
                        batteryFaulty.setVisibility(View.VISIBLE);
                        batteryFaulty.setText(userSession.getBatteryFaultyLowOfIssueDetails());
                    } else {
                        batteryFaulty.setVisibility(View.GONE);
                    }

                    if (!userSession.getSpeakerNotWorkingOfIssueDetails().equalsIgnoreCase("")) {
                        speakerNotWorking.setVisibility(View.VISIBLE);
                        speakerNotWorking.setText(userSession.getSpeakerNotWorkingOfIssueDetails());
                    } else {
                        speakerNotWorking.setVisibility(View.GONE);
                    }

                    if (!userSession.getMicrophoneNotWorkingOfIssueDetails().equalsIgnoreCase("")) {
                        microphoneNot.setVisibility(View.VISIBLE);
                        microphoneNot.setText(userSession.getMicrophoneNotWorkingOfIssueDetails());
                    } else {
                        microphoneNot.setVisibility(View.GONE);
                    }

                    if (!userSession.getGSMCallFunctionNotWorkingOfIssueDetails().equalsIgnoreCase("")) {
                        gsmCallFunction.setVisibility(View.VISIBLE);
                        gsmCallFunction.setText(userSession.getGSMCallFunctionNotWorkingOfIssueDetails());
                    } else {
                        gsmCallFunction.setVisibility(View.GONE);
                    }

                    if (!userSession.getEarphoneJackNotWorkingOfIssueDetails().equalsIgnoreCase("")) {
                        earphoneJackDamaged.setVisibility(View.VISIBLE);
                        earphoneJackDamaged.setText(userSession.getEarphoneJackNotWorkingOfIssueDetails());
                    } else {
                        earphoneJackDamaged.setVisibility(View.GONE);
                    }

                    if (!userSession.getFingerprintSensorNotWorkingOfIssueDetails().equalsIgnoreCase("")) {
                        fingerprintSensor.setVisibility(View.VISIBLE);
                        fingerprintSensor.setText(userSession.getFingerprintSensorNotWorkingOfIssueDetails());
                    } else {
                        fingerprintSensor.setVisibility(View.GONE);
                    }
                }
            } else {
                functionalIssueLayout.setVisibility(View.GONE);
                volumeNotWorking.setVisibility(View.GONE);
                questionairs.setVisibility(View.GONE);
            }

            if (!userSession.getFlawlessOfDeviceBodyDetails().equalsIgnoreCase("")) {
                deviceBodyLayout.setVisibility(View.VISIBLE);
                flawlessDeviceBodyDetailsAns.setVisibility(View.VISIBLE);
                questionairs.setVisibility(View.GONE);
                flawlessDeviceBodyDetailsAns.setText("By Rider:- " + userSession.getFlawlessOfDeviceBodyDetails());
                if (deviceBodyLayout.getVisibility() == View.VISIBLE) {
                    if (!userSession.getScratchedOfDeviceBodyDetails().equalsIgnoreCase("")) {
                        scratches.setVisibility(View.VISIBLE);
                        scratches.setText(userSession.getScratchedOfDeviceBodyDetails());
                    } else {
                        scratches.setVisibility(View.GONE);
                    }

                    if (!userSession.getCrackedOfDeviceBodyDetails().equalsIgnoreCase("")) {
                        cracked.setVisibility(View.VISIBLE);
                        cracked.setText(userSession.getCrackedOfDeviceBodyDetails());
                    } else {
                        cracked.setVisibility(View.GONE);
                    }

                    if (!userSession.getBrokenOfDeviceBodyDetails().equalsIgnoreCase("")) {
                        broken.setVisibility(View.VISIBLE);
                        broken.setText(userSession.getBrokenOfDeviceBodyDetails());
                    } else {
                        broken.setVisibility(View.GONE);
                    }

                    if (!userSession.getOkOfDeviceBodyDetails().equalsIgnoreCase("")) {
                        okay.setVisibility(View.VISIBLE);
                        okay.setText(userSession.getOkOfDeviceBodyDetails());
                    } else {
                        okay.setVisibility(View.GONE);
                    }

                    if (!userSession.getLooseOfDeviceBodyDetails().equalsIgnoreCase("")) {
                        loose.setVisibility(View.VISIBLE);
                        loose.setText(userSession.getLooseOfDeviceBodyDetails());
                    } else {
                        loose.setVisibility(View.GONE);
                    }

                    if (!userSession.getMissingOfDeviceBodyDetails().equalsIgnoreCase("")) {
                        missing.setVisibility(View.VISIBLE);
                        missing.setText(userSession.getMissingOfDeviceBodyDetails());
                    } else {
                        missing.setVisibility(View.GONE);
                    }
                }
            } else {
                deviceBodyLayout.setVisibility(View.GONE);
                flawlessDeviceBodyDetailsAns.setVisibility(View.GONE);
                questionairs.setVisibility(View.GONE);
            }

            if (!userSession.getOkOfBezel().equalsIgnoreCase("")) {
                silverFrameLayout.setVisibility(View.VISIBLE);
                okBezelAns.setVisibility(View.VISIBLE);
                questionairs.setVisibility(View.GONE);
                okBezelAns.setText("By Rider:- " + userSession.getOkOfBezel());
                if (silverFrameLayout.getVisibility() == View.VISIBLE) {
                    if (!userSession.getDiscoloredOfBezel().equalsIgnoreCase("")) {
                        discoloredBezel.setVisibility(View.VISIBLE);
                        discoloredBezel.setText(userSession.getDiscoloredOfBezel());
                    } else {
                        discoloredBezel.setVisibility(View.GONE);
                    }

                    if (!userSession.getDentedOfBezel().equalsIgnoreCase("")) {
                        dentedBezel.setVisibility(View.VISIBLE);
                        dentedBezel.setText(userSession.getDentedOfBezel());
                    } else {
                        dentedBezel.setVisibility(View.GONE);
                    }

                    if (!userSession.getBrokenOfBezel().equalsIgnoreCase("")) {
                        brokenBezel.setVisibility(View.VISIBLE);
                        brokenBezel.setText(userSession.getBrokenOfBezel());
                    } else {
                        brokenBezel.setVisibility(View.GONE);
                    }
                }
            } else {
                silverFrameLayout.setVisibility(View.GONE);
                okBezelAns.setVisibility(View.GONE);
                questionairs.setVisibility(View.GONE);
            }

            if (!userSession.getOkOfCamera().equalsIgnoreCase("")) {
                mainCameraLayout.setVisibility(View.VISIBLE);
                okCameraAns.setVisibility(View.VISIBLE);
                questionairs.setVisibility(View.GONE);
                okCameraAns.setText("By Rider:- " + userSession.getOkOfCamera());
                if (mainCameraLayout.getVisibility() == View.VISIBLE) {
                    if (!userSession.getScratchedOfCamera().equalsIgnoreCase("")) {
                        scratchedCamera.setVisibility(View.VISIBLE);
                        scratchedCamera.setText(userSession.getScratchedOfCamera());
                    } else {
                        scratchedCamera.setVisibility(View.GONE);
                    }

                    if (!userSession.getBlurOfCamera().equalsIgnoreCase("")) {
                        blurCamera.setVisibility(View.VISIBLE);
                        blurCamera.setText(userSession.getBlurOfCamera());
                    } else {
                        blurCamera.setVisibility(View.GONE);
                    }

                    if (!userSession.getCrackedOfCamera().equalsIgnoreCase("")) {
                        crackedCamera.setVisibility(View.VISIBLE);
                        crackedCamera.setText(userSession.getCrackedOfCamera());
                    } else {
                        crackedCamera.setVisibility(View.GONE);
                    }

                    if (!userSession.getBrokenOfCamera().equalsIgnoreCase("")) {
                        brokenCamera.setVisibility(View.VISIBLE);
                        brokenCamera.setText(userSession.getBrokenOfCamera());
                    } else {
                        brokenCamera.setVisibility(View.GONE);
                    }
                }
            } else {
                mainCameraLayout.setVisibility(View.GONE);
                okCameraAns.setVisibility(View.GONE);
                questionairs.setVisibility(View.GONE);
            }
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    private void getViewAnswerLayoutUiIdFind() {
        try {
            backAnswerImg = (ImageView) findViewById(R.id.backAnswerImg);
            doesYourDeviceSwitchOnLayout = (LinearLayout) findViewById(R.id.doesYourDeviceSwitchOnLayout);
            displayAndTouchScreenLayout = (LinearLayout) findViewById(R.id.displayAndTouchScreenLayout);
            availableAccessoriesLayout = (LinearLayout) findViewById(R.id.availableAccessoriesLayout);
            functionalIssueLayout = (LinearLayout) findViewById(R.id.functionalIssueLayout);
            repairDetailsLayout = (LinearLayout) findViewById(R.id.repairDetailsLayout);
            warrantyUtilizedLayout = (LinearLayout) findViewById(R.id.warrantyUtilizedLayout);
            deviceBodyLayout = (LinearLayout) findViewById(R.id.deviceBodyLayout);
            silverFrameLayout = (LinearLayout) findViewById(R.id.silverFrameLayout);
            mainCameraLayout = (LinearLayout) findViewById(R.id.mainCameraLayout);

            deviceConditionDetailsAns = (TextView) findViewById(R.id.deviceConditionDetailsAns);
            flawlessConditionDetailsFlawlessAns = (TextView) findViewById(R.id.flawlessConditionDetailsFlawlessAns);
            minorScratches = (TextView) findViewById(R.id.minorScratches);
            shadedWhiteDots = (TextView) findViewById(R.id.shadedWhiteDots);
            brokenDeadPixel = (TextView) findViewById(R.id.brokenDeadPixel);
            earphone = (TextView) findViewById(R.id.earphone);
            boxWithSameIMEI = (TextView) findViewById(R.id.boxWithSameIMEI);
            originalCharger = (TextView) findViewById(R.id.originalCharger);
            volumeNotWorking = (TextView) findViewById(R.id.volumeNotWorking);
            powerHomeNotWorking = (TextView) findViewById(R.id.powerHomeNotWorking);
            wifiBluetoothWorking = (TextView) findViewById(R.id.wifiBluetoothWorking);
            chargingDefect = (TextView) findViewById(R.id.chargingDefect);
            batteryFaulty = (TextView) findViewById(R.id.batteryFaulty);
            speakerNotWorking = (TextView) findViewById(R.id.speakerNotWorking);
            microphoneNot = (TextView) findViewById(R.id.microphoneNot);
            gsmCallFunction = (TextView) findViewById(R.id.gsmCallFunction);
            earphoneJackDamaged = (TextView) findViewById(R.id.earphoneJackDamaged);
            fingerprintSensor = (TextView) findViewById(R.id.fingerprintSensor);
            repairDetailsAns = (TextView) findViewById(R.id.repairDetailsAns);
            brandUtilizedAns = (TextView) findViewById(R.id.brandUtilizedAns);
            flawlessDeviceBodyDetailsAns = (TextView) findViewById(R.id.flawlessDeviceBodyDetailsAns);
            scratches = (TextView) findViewById(R.id.scratches);
            cracked = (TextView) findViewById(R.id.cracked);
            broken = (TextView) findViewById(R.id.broken);
            okay = (TextView) findViewById(R.id.okay);
            loose = (TextView) findViewById(R.id.loose);
            missing = (TextView) findViewById(R.id.missing);
            okBezelAns = (TextView) findViewById(R.id.okBezelAns);
            discoloredBezel = (TextView) findViewById(R.id.discoloredBezel);
            dentedBezel = (TextView) findViewById(R.id.dentedBezel);
            brokenBezel = (TextView) findViewById(R.id.brokenBezel);
            okCameraAns = (TextView) findViewById(R.id.okCameraAns);
            scratchedCamera = (TextView) findViewById(R.id.scratchedCamera);
            blurCamera = (TextView) findViewById(R.id.blurCamera);
            crackedCamera = (TextView) findViewById(R.id.crackedCamera);
            brokenCamera = (TextView) findViewById(R.id.brokenCamera);
            questionairs = (TextView) findViewById(R.id.questionairs);

            deviceOnOffAns = (TextView) findViewById(R.id.deviceOnOffAns);
            touchScreenAns = (TextView) findViewById(R.id.touchScreenAns);
            accessoriesAns = (TextView) findViewById(R.id.accessoriesAns);
            functionalIssueAns = (TextView) findViewById(R.id.functionalIssueAns);
            repairAns = (TextView) findViewById(R.id.repairAns);
            brandUtilizedAnsEd = (TextView) findViewById(R.id.brandUtilizedAnsEd);
            deviceBodyAns = (TextView) findViewById(R.id.deviceBodyAns);
            silverFrameAns = (TextView) findViewById(R.id.silverFrameAns);
            mainCameraAns = (TextView) findViewById(R.id.mainCameraAns);

        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backAnswerImg:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}