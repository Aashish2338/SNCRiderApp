package com.amsys.sncriderapp.Activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.amsys.sncriderapp.R;
import com.amsys.sncriderapp.Utilities.UserSession;

import org.json.JSONObject;

public class AnswerViewDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private UserSession userSession;
    private ImageView backAnswerImg;
    private AppCompatButton riderBtn, endUserBtn;
    private LinearLayout riderLayout, endUserLayout;
    private TextView deviceOnOffAns, touchScreenAns, accessoriesAns, functionalIssueAns, repairAns;
    private TextView brandUtilizedAnsEd, deviceBodyAns, silverFrameAns, mainCameraAns;

    //    private String deviceOnOff = "", displayTouch = "", accessories = "", functionalIssue = "", repair = "";
//    private String warrantyUtilized = "", deviceBody = "", silverFrame = "", mainCamera = "";
    private String jsonIsDeviceOn = "", jsonFlawless = "", jsonMinorScratchesTwoOrThree = "", jsonShadedWhiteDots = "";
    private String jsonBrokenDead = "", jsonEarphone = "", jsonBoxWithSameIMEI = "", jsonOriginalCharger = "";
    private String jsonVolumeButton = "", jsonPowerHomeButton = "", jsonWifiBluetoothGPS = "", jsonChargingDefect = "";
    private String jsonBatteryFaulty = "", jsonSpeakersFaulty = "", jsonMicrophoneFaulty = "", jsonGsMFaulty = "";
    private String jsonEarphoneJackFaulty = "", jsonFingerPrintSensorFaulty = "", jsonCameraFaulty = "", jsonUndergoneRepairs = "";
    private String jsonWarrantyUtilizedMonth = "", jsonLessThanThreeMonths = "", jsonLessThanTenMonths = "", jsonMoreThanTenMonths = "";
    private String jsonNotAvailable = "", jsonDeviceFlawless = "", jsonScratched = "", jsonBroken = "", jsonLoose = "", jsonMissing = "";
    private String jsonOkay = "", jsonDiscolored = "", jsonDented = "", jsonSilverBroken = "", jsonOkayFlawless = "", jsonMainScratched = "";
    private String jsonBlur = "", jsonMainBroken = "";
    private String displayTouchAns = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_view_details);
        mContext = this;
        userSession = new UserSession(mContext);

        getAnswerViewDetailsLayoutUiId();

        backAnswerImg.setOnClickListener(this);
        riderBtn.setOnClickListener(this);
        endUserBtn.setOnClickListener(this);

        getJsonParseData();
    }

    private void getJsonParseData() {
        try {
//            deviceOnOff = userSession.getDeviceOnOffEndUserJson();
//            displayTouch = userSession.getDisplayTouchEndUserJson();
//            accessories = userSession.getAvailableAccessoriesEndUserJson();
//            functionalIssue = userSession.getFunctionalIssueEndUserJson();
//            repair = userSession.getRepairDetailsEndUserJson();
//            warrantyUtilized = userSession.getBrandWarrantyUtilizedEndUserJson();
//            deviceBody = userSession.getBodyInformationEndUserJson();
//            silverFrame = userSession.getSilverFrameBezelEndUserJson();
//            mainCamera = userSession.getMainCameraEndUserJson();

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

            System.out.println("Hi Aashish :- " + jsonFlawless + ", " + jsonMinorScratchesTwoOrThree + ", " + jsonShadedWhiteDots + ", " + jsonBrokenDead);

            getDataForSetInTextView();

        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    private void getDataForSetInTextView() {
        try {
            if (jsonIsDeviceOn.equalsIgnoreCase("true")) {
                deviceOnOffAns.setText("Ans. By EndUser :- Yes");
            } else {
                deviceOnOffAns.setText("Ans. By EndUser :- No");
            }

            if (jsonFlawless.equalsIgnoreCase("true")) {
                touchScreenAns.setText("Ans. By EndUser :- Flawless");
            } else if (jsonMinorScratchesTwoOrThree.equalsIgnoreCase("true")) {
                touchScreenAns.setText("Ans. By EndUser :- 2-3 Minor Scratches");
            } else if (jsonShadedWhiteDots.equalsIgnoreCase("true")) {
                touchScreenAns.setText("Ans. By EndUser :- Shaded/White Dots");
            } else if (jsonBrokenDead.equalsIgnoreCase("true")) {
                touchScreenAns.setText("Ans. By EndUser :- Broken Dead Pixel Liquid Mark or Dots not work properly");
            } else if (jsonFlawless.equalsIgnoreCase("true") &&
                    jsonMinorScratchesTwoOrThree.equalsIgnoreCase("true")) {
                touchScreenAns.setText("Ans. By EndUser :- Flawless, 2-3 Minor Scratches");
            } else if (jsonFlawless.equalsIgnoreCase("true") &&
                    jsonMinorScratchesTwoOrThree.equalsIgnoreCase("true") &&
                    jsonShadedWhiteDots.equalsIgnoreCase("true")) {
                touchScreenAns.setText("Ans. By EndUser :- Flawless, 2-3 Minor Scratches, Shaded/White Dots");
            } else if (jsonFlawless.equalsIgnoreCase("true") &&
                    jsonMinorScratchesTwoOrThree.equalsIgnoreCase("true") &&
                    jsonShadedWhiteDots.equalsIgnoreCase("true") &&
                    jsonBrokenDead.equalsIgnoreCase("true")) {
                touchScreenAns.setText("Ans. By EndUser :- Flawless, 2-3 Minor Scratches, Shaded/White Dots, Broken Dead Pixel Liquid Mark or Dots not work properly");
            } else {
                touchScreenAns.setText("Ans. By EndUser :- N/A");
            }

            if (jsonEarphone.equalsIgnoreCase("true")) {
                accessoriesAns.setText("Ans.By EndUser :- Earphone");
            } else if (jsonBoxWithSameIMEI.equalsIgnoreCase("true")) {
                accessoriesAns.setText("Ans.By EndUser :- Box with same IMEI");
            } else if (jsonOriginalCharger.equalsIgnoreCase("true")) {
                accessoriesAns.setText("Ans.By EndUser :- Original Charger");
            } else if (jsonEarphone.equalsIgnoreCase("true")
                    && jsonBoxWithSameIMEI.equalsIgnoreCase("true")) {
                accessoriesAns.setText("Ans.By EndUser :- Earphone, Box with same IMEI");
            } else if (jsonEarphone.equalsIgnoreCase("true")
                    && jsonBoxWithSameIMEI.equalsIgnoreCase("true")
                    && jsonOriginalCharger.equalsIgnoreCase("true")) {
                accessoriesAns.setText("Ans.By EndUser :- Earphone, Box with same IMEI, Original Charger");
            } else {
                accessoriesAns.setText("Ans.By EndUser :- N/A");
            }

            if (jsonVolumeButton.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("Ans. By EndUser :- Volume button not working");
            } else if (jsonPowerHomeButton.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("Ans. By EndUser :- Power/Home Button faulty: Hard or not working");
            } else if (jsonWifiBluetoothGPS.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("Ans. By EndUser :- Wifi or Bluetooth Or GPS Not Working");
            } else if (jsonChargingDefect.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("Ans. By EndUser :- Charging Defect: Unable to charge the phone");
            } else if (jsonBatteryFaulty.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("Ans. By EndUser :- Battery Faulty or Very Low Battery Back up");
            } else if (jsonSpeakersFaulty.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("Ans. By EndUser :- Speaker not working: faulty Or cracked sound");
            } else if (jsonMicrophoneFaulty.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("Ans. By EndUser :- Microphone Not Working");
            } else if (jsonGsMFaulty.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("Ans. By EndUser :- GSM(Call Function) is not-working normally");
            } else if (jsonEarphoneJackFaulty.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("Ans. By EndUser :- Earphone Jack is damaged or not-working");
            } else if (jsonFingerPrintSensorFaulty.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("Ans. By EndUser :- Fingerprint Sensor Not-working");
            } else if (jsonVolumeButton.equalsIgnoreCase("true") &&
                    jsonPowerHomeButton.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("Ans. By EndUser :- Volume button not working, Power/Home Button faulty: Hard or not working");
            } else if (jsonVolumeButton.equalsIgnoreCase("true") &&
                    jsonPowerHomeButton.equalsIgnoreCase("true") &&
                    jsonWifiBluetoothGPS.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("Ans. By EndUser :- Volume button not working, " +
                        "Power/Home Button faulty: Hard or not working, " +
                        "Wifi or Bluetooth Or GPS Not Working");
            } else if (jsonVolumeButton.equalsIgnoreCase("true") &&
                    jsonPowerHomeButton.equalsIgnoreCase("true") &&
                    jsonWifiBluetoothGPS.equalsIgnoreCase("true") &&
                    jsonChargingDefect.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("Ans. By EndUser :- Volume button not working, " +
                        "Power/Home Button faulty: Hard or not working, " +
                        "Wifi or Bluetooth Or GPS Not Working, " +
                        "Charging Defect: Unable to charge the phone");
            } else if (jsonVolumeButton.equalsIgnoreCase("true") &&
                    jsonPowerHomeButton.equalsIgnoreCase("true") &&
                    jsonWifiBluetoothGPS.equalsIgnoreCase("true") &&
                    jsonChargingDefect.equalsIgnoreCase("true") &&
                    jsonBatteryFaulty.equalsIgnoreCase("true")) {
                functionalIssueAns.setText("Ans. By EndUser :- Volume button not working, " +
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
                functionalIssueAns.setText("Ans. By EndUser :- Volume button not working, " +
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
                functionalIssueAns.setText("Ans. By EndUser :- Volume button not working, " +
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
                functionalIssueAns.setText("Ans. By EndUser :- Volume button not working, " +
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
                functionalIssueAns.setText("Ans. By EndUser :- Volume button not working, " +
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
                functionalIssueAns.setText("Ans. By EndUser :- Volume button not working, " +
                        "Power/Home Button faulty: Hard or not working, " +
                        "Wifi or Bluetooth Or GPS Not Working, " +
                        "Charging Defect: Unable to charge the phone, " +
                        "Battery Faulty or Very Low Battery Back up, " +
                        "Speaker not working: faulty Or cracked sound, " +
                        "Microphone Not Working, GSM(Call Function) is not-working normally, " +
                        "Earphone Jack is damaged or not-working, Fingerprint Sensor Not-working");
            } else {
                functionalIssueAns.setText("Ans. By EndUser :- N/A");
            }

            if (jsonUndergoneRepairs.equalsIgnoreCase("true")) {
                repairAns.setText("Ans. By EndUser :- Yes");
            } else {
                repairAns.setText("Ans. By EndUser :- N/A");
            }

            if (jsonLessThanThreeMonths.equalsIgnoreCase("true")) {
                brandUtilizedAnsEd.setText("Ans. By EndUser :- 0 to 3 months");
            } else if (jsonLessThanTenMonths.equalsIgnoreCase("true")) {
                brandUtilizedAnsEd.setText("Ans. By EndUser :- 3 to 10 months");
            } else if (jsonMoreThanTenMonths.equalsIgnoreCase("true")) {
                brandUtilizedAnsEd.setText("Ans. By EndUser :- More than 10 Months");
            } else if (jsonNotAvailable.equalsIgnoreCase("true")) {
                brandUtilizedAnsEd.setText("Ans. By EndUser :- Not available");
            } else {
                brandUtilizedAnsEd.setText("Ans. By EndUser :- N/A");
            }

            if (jsonDeviceFlawless.equalsIgnoreCase("true")) {
                deviceBodyAns.setText("Ans. By EndUser :- Flawless");
            } else if (jsonScratched.equalsIgnoreCase("true")) {
                deviceBodyAns.setText("Ans. By EndUser :- Scratched");
            } else if (jsonBroken.equalsIgnoreCase("true")) {
                deviceBodyAns.setText("Ans. By EndUser :- Broken");
            } else if (jsonLoose.equalsIgnoreCase("true")) {
                deviceBodyAns.setText("Ans. By EndUser :- Loose");
            } else if (jsonMissing.equalsIgnoreCase("true")) {
                deviceBodyAns.setText("Ans. By EndUser :- Missing");
            } else if (jsonDeviceFlawless.equalsIgnoreCase("true") &&
                    jsonScratched.equalsIgnoreCase("true")) {
                deviceBodyAns.setText("Ans. By EndUser :- Flawless, Scratched");
            } else if (jsonDeviceFlawless.equalsIgnoreCase("true") &&
                    jsonScratched.equalsIgnoreCase("true") &&
                    jsonBroken.equalsIgnoreCase("true")) {
                deviceBodyAns.setText("Ans. By EndUser :- Flawless, Scratched, Broken");
            } else if (jsonDeviceFlawless.equalsIgnoreCase("true") &&
                    jsonScratched.equalsIgnoreCase("true") &&
                    jsonBroken.equalsIgnoreCase("true") &&
                    jsonLoose.equalsIgnoreCase("true")) {
                deviceBodyAns.setText("Ans. By EndUser :- Flawless, Scratched, Broken, Loose");
            } else if (jsonDeviceFlawless.equalsIgnoreCase("true") &&
                    jsonScratched.equalsIgnoreCase("true") &&
                    jsonBroken.equalsIgnoreCase("true") &&
                    jsonLoose.equalsIgnoreCase("true") &&
                    jsonMissing.equalsIgnoreCase("true")) {
                deviceBodyAns.setText("Ans. By EndUser :- Flawless, Scratched, Broken, Loose, Missing");
            } else {
                deviceBodyAns.setText("Ans. By EndUser :- N/A");
            }

            if (jsonOkay.equalsIgnoreCase("true")) {
                silverFrameAns.setText("Ans. By EndUser :- Okay");
            } else if (jsonDiscolored.equalsIgnoreCase("true")) {
                silverFrameAns.setText("Ans. By EndUser :- Discolored");
            } else if (jsonDented.equalsIgnoreCase("true")) {
                silverFrameAns.setText("Ans. By EndUser :- Dented");
            } else if (jsonSilverBroken.equalsIgnoreCase("true")) {
                silverFrameAns.setText("Ans. By EndUser :- Broken");
            } else if (jsonOkay.equalsIgnoreCase("true") && jsonDiscolored.equalsIgnoreCase("true")) {
                silverFrameAns.setText("Ans. By EndUser :- Okay, Discolored");
            } else if (jsonOkay.equalsIgnoreCase("true")
                    && jsonDiscolored.equalsIgnoreCase("true")
                    && jsonDented.equalsIgnoreCase("true")) {
                silverFrameAns.setText("Ans. By EndUser :- Okay, Discolored, Dented");
            } else if (jsonOkay.equalsIgnoreCase("true")
                    && jsonDiscolored.equalsIgnoreCase("true")
                    && jsonDented.equalsIgnoreCase("true")
                    && jsonSilverBroken.equalsIgnoreCase("true")) {
                silverFrameAns.setText("Ans. By EndUser :- Okay, Discolored, Dented, Broken");
            } else {
                silverFrameAns.setText("Ans. By EndUser :- N/A");
            }

            if (jsonOkayFlawless.equalsIgnoreCase("true")) {
                mainCameraAns.setText("Ans. By EndUser :- Okay");
            } else if (jsonMainScratched.equalsIgnoreCase("true")) {
                mainCameraAns.setText("Ans. By EndUser :- Scratched");
            } else if (jsonBlur.equalsIgnoreCase("true")) {
                mainCameraAns.setText("Ans. By EndUser :- Blur");
            } else if (jsonMainBroken.equalsIgnoreCase("true")) {
                mainCameraAns.setText("Ans. By EndUser :- Broken");
            } else if (jsonOkayFlawless.equalsIgnoreCase("true") && jsonMainScratched.equalsIgnoreCase("true")) {
                mainCameraAns.setText("Ans. By EndUser :- Okay, Scratched");
            } else if (jsonOkayFlawless.equalsIgnoreCase("true") && jsonMainScratched.equalsIgnoreCase("true") &&
                    jsonBlur.equalsIgnoreCase("true")) {
                mainCameraAns.setText("Ans. By EndUser :- Okay, Scratched, Blur");
            } else if (jsonOkayFlawless.equalsIgnoreCase("true") && jsonMainScratched.equalsIgnoreCase("true") &&
                    jsonBlur.equalsIgnoreCase("true") && jsonMainBroken.equalsIgnoreCase("true")) {
                mainCameraAns.setText("Ans. By EndUser :- Okay, Scratched, Blur, Broken");
            } else {
                mainCameraAns.setText("Ans. By EndUser :- N/A");
            }

        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    private void getAnswerViewDetailsLayoutUiId() {
        try {
            backAnswerImg = (ImageView) findViewById(R.id.backAnswerImg);
            riderBtn = (AppCompatButton) findViewById(R.id.riderBtn);
            endUserBtn = (AppCompatButton) findViewById(R.id.endUserBtn);
            riderLayout = (LinearLayout) findViewById(R.id.riderLayout);
            endUserLayout = (LinearLayout) findViewById(R.id.endUserLayout);

            deviceOnOffAns = (TextView) findViewById(R.id.deviceOnOffAns);
            touchScreenAns = (TextView) findViewById(R.id.touchScreenAns);
            accessoriesAns = (TextView) findViewById(R.id.accessoriesAns);
            functionalIssueAns = (TextView) findViewById(R.id.functionalIssueAns);
            repairAns = (TextView) findViewById(R.id.repairAns);
            brandUtilizedAnsEd = (TextView) findViewById(R.id.brandUtilizedAns);
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

            case R.id.riderBtn:
                riderLayout.setVisibility(View.VISIBLE);
                endUserLayout.setVisibility(View.GONE);
                break;

            case R.id.endUserBtn:
                riderLayout.setVisibility(View.GONE);
                endUserLayout.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}