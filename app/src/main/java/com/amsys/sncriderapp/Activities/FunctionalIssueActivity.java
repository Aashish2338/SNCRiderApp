package com.amsys.sncriderapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.amsys.sncriderapp.R;
import com.amsys.sncriderapp.Utilities.UserSession;
import com.google.gson.JsonObject;

public class FunctionalIssueActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private UserSession userSession;
    private ImageView backIssueImg, volumeImg, powerImg, wifiImg, chargingImg, batteryImg, speakerImg;
    private ImageView microphoneImg, gSMImg, earphoneImg, fingerprintImg;
    private TextView volumeTxt, powerTxt, wifiTxt, chargingTxt, batteryTxt, speakerTxt;
    private TextView microphoneTxt, gSMTxt, earphoneTxt, fingerprintTxt;
    private AppCompatButton continueIssueBtn, viewAnswerBtn, skipIssueBtn;
    private CardView volumeCard, powerCard, wifiCard, chargingCard, batteryCard, speakerCard;
    private CardView microphoneCard, gsmCard, jackCard, fingerPrintCard;
    private boolean volumeButton = false, powerHomeButton = false, wifiBluetoothGPS = false, chargingDefect = false;
    private boolean batteryFaulty = false, speakersFaulty = false, microphoneFaulty = false, gsMFaulty = false;
    private boolean earphoneJackFaulty = false, fingerprintSensorFaulty = false, cameraFaulty = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functional_issue);
        mContext = this;
        userSession = new UserSession(mContext);

        getIssueLayoutUiIdFind();

        backIssueImg.setOnClickListener(this);
        continueIssueBtn.setOnClickListener(this);
        viewAnswerBtn.setOnClickListener(this);
        skipIssueBtn.setOnClickListener(this);
        volumeCard.setOnClickListener(this);
        powerCard.setOnClickListener(this);
        wifiCard.setOnClickListener(this);
        chargingCard.setOnClickListener(this);
        batteryCard.setOnClickListener(this);
        speakerCard.setOnClickListener(this);
        microphoneCard.setOnClickListener(this);
        gsmCard.setOnClickListener(this);
        jackCard.setOnClickListener(this);
        fingerPrintCard.setOnClickListener(this);

    }

    private void getIssueLayoutUiIdFind() {
        try {
            backIssueImg = (ImageView) findViewById(R.id.backIssueImg);
            volumeImg = (ImageView) findViewById(R.id.volumeImg);
            powerImg = (ImageView) findViewById(R.id.powerImg);
            wifiImg = (ImageView) findViewById(R.id.wifiImg);
            chargingImg = (ImageView) findViewById(R.id.chargingImg);
            batteryImg = (ImageView) findViewById(R.id.batteryImg);
            speakerImg = (ImageView) findViewById(R.id.speakerImg);
            microphoneImg = (ImageView) findViewById(R.id.microphoneImg);
            gSMImg = (ImageView) findViewById(R.id.gSMImg);
            earphoneImg = (ImageView) findViewById(R.id.earphoneImg);
            fingerprintImg = (ImageView) findViewById(R.id.fingerprintImg);
            continueIssueBtn = (AppCompatButton) findViewById(R.id.continueIssueBtn);
            viewAnswerBtn = (AppCompatButton) findViewById(R.id.viewAnswerBtn);
            skipIssueBtn = (AppCompatButton) findViewById(R.id.skipIssueBtn);
            volumeCard = (CardView) findViewById(R.id.volumeCard);
            powerCard = (CardView) findViewById(R.id.powerCard);
            wifiCard = (CardView) findViewById(R.id.wifiCard);
            chargingCard = (CardView) findViewById(R.id.chargingCard);
            batteryCard = (CardView) findViewById(R.id.batteryCard);
            speakerCard = (CardView) findViewById(R.id.speakerCard);
            microphoneCard = (CardView) findViewById(R.id.microphoneCard);
            gsmCard = (CardView) findViewById(R.id.gsmCard);
            jackCard = (CardView) findViewById(R.id.jackCard);
            fingerPrintCard = (CardView) findViewById(R.id.fingerPrintCard);
            volumeTxt = (TextView) findViewById(R.id.volumeTxt);
            powerTxt = (TextView) findViewById(R.id.powerTxt);
            wifiTxt = (TextView) findViewById(R.id.wifiTxt);
            chargingTxt = (TextView) findViewById(R.id.chargingTxt);
            batteryTxt = (TextView) findViewById(R.id.batteryTxt);
            speakerTxt = (TextView) findViewById(R.id.speakerTxt);
            microphoneTxt = (TextView) findViewById(R.id.microphoneTxt);
            gSMTxt = (TextView) findViewById(R.id.gSMTxt);
            earphoneTxt = (TextView) findViewById(R.id.earphoneTxt);
            fingerprintTxt = (TextView) findViewById(R.id.fingerprintTxt);

            userSession.setVolumeNotWorkingOfIssueDetails("");
            userSession.setPowerHomeButtonFaultyOfIssueDetails("");
            userSession.setWifiBlueToothGPSOfIssueDetails("");
            userSession.setChargingDefectOfIssueDetails("");
            userSession.setBatteryFaultyLowOfIssueDetails("");
            userSession.setSpeakerNotWorkingOfIssueDetails("");
            userSession.setMicrophoneNotWorkingOfIssueDetails("");
            userSession.setGSMCallFunctionNotWorkingOfIssueDetails("");
            userSession.setEarphoneJackNotWorkingOfIssueDetails("");
            userSession.setFingerprintSensorNotWorkingOfIssueDetails("");

        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backIssueImg:
                userSession.setVolumeNotWorkingOfIssueDetails("");
                userSession.setPowerHomeButtonFaultyOfIssueDetails("");
                userSession.setWifiBlueToothGPSOfIssueDetails("");
                userSession.setChargingDefectOfIssueDetails("");
                userSession.setBatteryFaultyLowOfIssueDetails("");
                userSession.setSpeakerNotWorkingOfIssueDetails("");
                userSession.setMicrophoneNotWorkingOfIssueDetails("");
                userSession.setGSMCallFunctionNotWorkingOfIssueDetails("");
                userSession.setEarphoneJackNotWorkingOfIssueDetails("");
                userSession.setFingerprintSensorNotWorkingOfIssueDetails("");
                onBackPressed();
                break;

            case R.id.volumeCard:
                if (volumeButton == false) {
                    volumeButton = true;
                    userSession.setVolumeNotWorkingOfIssueDetails("Volume button not working");
                    volumeCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    volumeTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    volumeImg.setVisibility(View.VISIBLE);
                } else if (volumeButton == true) {
                    volumeButton = false;
                    userSession.setVolumeNotWorkingOfIssueDetails("");
                    volumeCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    volumeTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    volumeImg.setVisibility(View.INVISIBLE);
                } else {
                    volumeCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    volumeTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    volumeImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.powerCard:
                if (powerHomeButton == false) {
                    powerHomeButton = true;
                    userSession.setPowerHomeButtonFaultyOfIssueDetails("Power/Home Button faulty: Hard or not working");
                    powerCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    powerTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    powerImg.setVisibility(View.VISIBLE);
                } else if (powerHomeButton == true) {
                    powerHomeButton = false;
                    userSession.setPowerHomeButtonFaultyOfIssueDetails("");
                    powerCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    powerTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    powerImg.setVisibility(View.INVISIBLE);
                } else {
                    powerCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    powerTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    powerImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.wifiCard:
                if (wifiBluetoothGPS == false) {
                    wifiBluetoothGPS = true;
                    userSession.setWifiBlueToothGPSOfIssueDetails("Wifi or Bluetooth Or GPS Not Working");
                    wifiCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    wifiTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    wifiImg.setVisibility(View.VISIBLE);
                } else if (wifiBluetoothGPS == true) {
                    wifiBluetoothGPS = false;
                    userSession.setWifiBlueToothGPSOfIssueDetails("");
                    wifiCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    wifiTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    wifiImg.setVisibility(View.INVISIBLE);
                } else {
                    wifiCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    wifiTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    wifiImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.chargingCard:
                if (chargingDefect == false) {
                    chargingDefect = true;
                    userSession.setChargingDefectOfIssueDetails("Charging Defect: Unable to charge the phone");
                    chargingCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    chargingTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    chargingImg.setVisibility(View.VISIBLE);
                } else if (chargingDefect == true) {
                    chargingDefect = false;
                    userSession.setChargingDefectOfIssueDetails("");
                    chargingCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    chargingTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    chargingImg.setVisibility(View.INVISIBLE);
                } else {
                    chargingCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    chargingTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    chargingImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.batteryCard:
                if (batteryFaulty == false) {
                    batteryFaulty = true;
                    userSession.setBatteryFaultyLowOfIssueDetails("Battery Faulty or Very Low Battery Back up");
                    batteryCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    batteryTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    batteryImg.setVisibility(View.VISIBLE);
                } else if (batteryFaulty == true) {
                    batteryFaulty = false;
                    userSession.setBatteryFaultyLowOfIssueDetails("");
                    batteryCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    batteryTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    batteryImg.setVisibility(View.INVISIBLE);
                } else {
                    batteryCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    batteryTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    batteryImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.speakerCard:
                if (speakersFaulty == false) {
                    speakersFaulty = true;
                    userSession.setSpeakerNotWorkingOfIssueDetails("Speaker not working: faulty Or cracked sound");
                    speakerCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    speakerTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    speakerImg.setVisibility(View.VISIBLE);
                } else if (speakersFaulty == true) {
                    speakersFaulty = false;
                    userSession.setSpeakerNotWorkingOfIssueDetails("");
                    speakerCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    speakerTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    speakerImg.setVisibility(View.INVISIBLE);
                } else {
                    speakerCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    speakerTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    speakerImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.microphoneCard:
                if (microphoneFaulty == false) {
                    microphoneFaulty = true;
                    userSession.setMicrophoneNotWorkingOfIssueDetails("Microphone Not Working");
                    microphoneCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    microphoneTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    microphoneImg.setVisibility(View.VISIBLE);
                } else if (microphoneFaulty == true) {
                    microphoneFaulty = false;
                    userSession.setMicrophoneNotWorkingOfIssueDetails("");
                    microphoneCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    microphoneTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    microphoneImg.setVisibility(View.INVISIBLE);
                } else {
                    microphoneCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    microphoneTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    microphoneImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.gsmCard:
                if (gsMFaulty == false) {
                    gsMFaulty = true;
                    userSession.setGSMCallFunctionNotWorkingOfIssueDetails("GSM(Call Function) is not-working normally");
                    gsmCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    gSMTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    gSMImg.setVisibility(View.VISIBLE);
                } else if (gsMFaulty == true) {
                    gsMFaulty = false;
                    userSession.setGSMCallFunctionNotWorkingOfIssueDetails("");
                    gsmCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    gSMTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    gSMImg.setVisibility(View.INVISIBLE);
                } else {
                    gsmCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    gSMTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    gSMImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.jackCard:
                if (earphoneJackFaulty == false) {
                    earphoneJackFaulty = true;
                    userSession.setEarphoneJackNotWorkingOfIssueDetails("Earphone Jack is damaged or not-working");
                    jackCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    earphoneTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    earphoneImg.setVisibility(View.VISIBLE);
                } else if (earphoneJackFaulty == true) {
                    earphoneJackFaulty = false;
                    userSession.setEarphoneJackNotWorkingOfIssueDetails("");
                    jackCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    earphoneTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    earphoneImg.setVisibility(View.INVISIBLE);
                } else {
                    jackCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    earphoneTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    earphoneImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.fingerPrintCard:
                if (fingerprintSensorFaulty == false) {
                    fingerprintSensorFaulty = true;
                    userSession.setFingerprintSensorNotWorkingOfIssueDetails("Fingerprint Sensor Not-working");
                    fingerPrintCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    fingerprintTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    fingerprintImg.setVisibility(View.VISIBLE);
                } else if (fingerprintSensorFaulty == true) {
                    fingerprintSensorFaulty = false;
                    userSession.setFingerprintSensorNotWorkingOfIssueDetails("");
                    fingerPrintCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    fingerprintTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    fingerprintImg.setVisibility(View.INVISIBLE);
                } else {
                    fingerPrintCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    fingerprintTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    fingerprintImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.skipIssueBtn:
                JsonObject functionalIssue = new JsonObject();
                functionalIssue.addProperty("volume_Button", volumeButton);
                functionalIssue.addProperty("power_Home_Button", powerHomeButton);
                functionalIssue.addProperty("wifi_Bluetooth_GPS", wifiBluetoothGPS);
                functionalIssue.addProperty("charging_Defect", chargingDefect);
                functionalIssue.addProperty("battery_Faulty", batteryFaulty);
                functionalIssue.addProperty("speakers_Faulty", speakersFaulty);
                functionalIssue.addProperty("microphone_Faulty", microphoneFaulty);
                functionalIssue.addProperty("gsM_Faulty", gsMFaulty);
                functionalIssue.addProperty("earphone_Jack_Faulty", earphoneJackFaulty);
                functionalIssue.addProperty("fingerprint_Sensor_Faulty", fingerprintSensorFaulty);
                functionalIssue.addProperty("camera_Faulty", cameraFaulty);
                userSession.setFunctionalIssueJson(functionalIssue.toString());
                System.out.println("Functional issue json :- " + functionalIssue);
                startActivity(new Intent(mContext, DeviceBodyInformationActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                break;

            case R.id.continueIssueBtn:
                if (volumeButton == true || powerHomeButton == true || wifiBluetoothGPS == true ||
                        chargingDefect == true || batteryFaulty == true || speakersFaulty == true ||
                        microphoneFaulty == true || gsMFaulty == true || earphoneJackFaulty == true ||
                        fingerprintSensorFaulty == true || cameraFaulty == true) {
                    JsonObject functionalIssueF = new JsonObject();
                    functionalIssueF.addProperty("volume_Button", volumeButton);
                    functionalIssueF.addProperty("power_Home_Button", powerHomeButton);
                    functionalIssueF.addProperty("wifi_Bluetooth_GPS", wifiBluetoothGPS);
                    functionalIssueF.addProperty("charging_Defect", chargingDefect);
                    functionalIssueF.addProperty("battery_Faulty", batteryFaulty);
                    functionalIssueF.addProperty("speakers_Faulty", speakersFaulty);
                    functionalIssueF.addProperty("microphone_Faulty", microphoneFaulty);
                    functionalIssueF.addProperty("gsM_Faulty", gsMFaulty);
                    functionalIssueF.addProperty("earphone_Jack_Faulty", earphoneJackFaulty);
                    functionalIssueF.addProperty("fingerprint_Sensor_Faulty", fingerprintSensorFaulty);
                    functionalIssueF.addProperty("camera_Faulty", cameraFaulty);
                    userSession.setFunctionalIssueJson(functionalIssueF.toString());
                    System.out.println("Functional issue json :- " + functionalIssueF);
                    startActivity(new Intent(mContext, DeviceBodyInformationActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                } else {
                    Toast.makeText(mContext, "Please select any functional issue!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.viewAnswerBtn:
                startActivity(new Intent(mContext, ViewAnswerDetailsActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}