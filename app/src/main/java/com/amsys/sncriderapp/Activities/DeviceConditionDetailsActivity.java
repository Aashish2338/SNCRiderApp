package com.amsys.sncriderapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;

import com.amsys.sncriderapp.R;
import com.amsys.sncriderapp.Utilities.UserSession;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class DeviceConditionDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private UserSession userSession;
    private ImageView backDetailsConditionImg;
    private AppCompatButton continueBtn, viewAnswerBtn;
    private LinearLayout yesLayout, noLayout, yesRLayout, noRLayout;
    private LinearLayout threeMonthsLayout, tenMonthLayout, notAvailableLayout, moreThanMonthLayout;
    private AppCompatCheckBox yesCheck, noCheck, yesRCheck, noRCheck;
    private AppCompatCheckBox threeMonthsCheck, tenMonthCheck, notAvailableCheck, moreThanMonthCheck;
    private boolean isDeviceOn = false, undergoneRepairs = false;
    private int warrantyUtilizedMonth = 0;
    private TextView deviceOn, deviceOff, repairYes, repairNo, warrantyFirst, warrantySecond, warrantyThird, warrantyFourth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_condition_details);
        mContext = this;
        userSession = new UserSession(mContext);

        getConditionLayoutUiIdFind();

        backDetailsConditionImg.setOnClickListener(this);
        continueBtn.setOnClickListener(this);
        viewAnswerBtn.setOnClickListener(this);
        yesLayout.setOnClickListener(this);
        noLayout.setOnClickListener(this);
        yesRLayout.setOnClickListener(this);
        noRLayout.setOnClickListener(this);
        threeMonthsLayout.setOnClickListener(this);
        tenMonthLayout.setOnClickListener(this);
        notAvailableLayout.setOnClickListener(this);
        moreThanMonthLayout.setOnClickListener(this);

    }

    private void getConditionLayoutUiIdFind() {
        try {
            backDetailsConditionImg = (ImageView) findViewById(R.id.backDetailsConditionImg);
            continueBtn = (AppCompatButton) findViewById(R.id.continueBtn);
            viewAnswerBtn = (AppCompatButton) findViewById(R.id.viewAnswerBtn);
            yesLayout = (LinearLayout) findViewById(R.id.yesLayout);
            noLayout = (LinearLayout) findViewById(R.id.noLayout);
            yesCheck = (AppCompatCheckBox) findViewById(R.id.yesCheck);
            noCheck = (AppCompatCheckBox) findViewById(R.id.noCheck);
            yesRCheck = (AppCompatCheckBox) findViewById(R.id.yesRCheck);
            noRCheck = (AppCompatCheckBox) findViewById(R.id.noRCheck);
            yesRLayout = (LinearLayout) findViewById(R.id.yesRLayout);
            noRLayout = (LinearLayout) findViewById(R.id.noRLayout);
            threeMonthsCheck = (AppCompatCheckBox) findViewById(R.id.threeMonthsCheck);
            tenMonthCheck = (AppCompatCheckBox) findViewById(R.id.tenMonthCheck);
            notAvailableCheck = (AppCompatCheckBox) findViewById(R.id.notAvailableCheck);
            moreThanMonthCheck = (AppCompatCheckBox) findViewById(R.id.moreThanMonthCheck);
            threeMonthsLayout = (LinearLayout) findViewById(R.id.threeMonthsLayout);
            tenMonthLayout = (LinearLayout) findViewById(R.id.tenMonthLayout);
            notAvailableLayout = (LinearLayout) findViewById(R.id.notAvailableLayout);
            moreThanMonthLayout = (LinearLayout) findViewById(R.id.moreThanMonthLayout);

            deviceOn = (TextView) findViewById(R.id.deviceOn);
            deviceOff = (TextView) findViewById(R.id.deviceOff);
            repairYes = (TextView) findViewById(R.id.repairYes);
            repairNo = (TextView) findViewById(R.id.repairNo);
            warrantyFirst = (TextView) findViewById(R.id.warrantyFirst);
            warrantySecond = (TextView) findViewById(R.id.warrantySecond);
            warrantyThird = (TextView) findViewById(R.id.warrantyThird);
            warrantyFourth = (TextView) findViewById(R.id.warrantyFourth);

            userSession.setDeviceConditionYesNo("");
            userSession.setRepairDetailsYesNo("");
            userSession.setDeviceHasUndergoneRepairsMonths("");

        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backDetailsConditionImg:
                userSession.setDeviceConditionYesNo("");
                userSession.setRepairDetailsYesNo("");
                userSession.setDeviceHasUndergoneRepairsMonths("");
                onBackPressed();
                break;

            case R.id.yesLayout:
                isDeviceOn = true;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    deviceOn.setTextColor(getColor(R.color.white));
                    deviceOff.setTextColor(getColor(R.color.blackText));
                }
                userSession.setDeviceConditionYesNo("Yes");
                yesLayout.setBackground(getDrawable(R.drawable.condition_bg));
                noLayout.setBackground(getDrawable(R.drawable.check_bg));
                yesCheck.setChecked(true);
                noCheck.setChecked(false);
                break;

            case R.id.noLayout:
                isDeviceOn = false;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    deviceOn.setTextColor(getColor(R.color.blackText));
                    deviceOff.setTextColor(getColor(R.color.white));
                }
                userSession.setDeviceConditionYesNo("No");
                yesLayout.setBackground(getDrawable(R.drawable.check_bg));
                noLayout.setBackground(getDrawable(R.drawable.condition_bg));
                yesCheck.setChecked(false);
                noCheck.setChecked(true);
                break;

            case R.id.yesRLayout:
                undergoneRepairs = true;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    repairYes.setTextColor(getColor(R.color.white));
                    repairNo.setTextColor(getColor(R.color.blackText));
                }
                userSession.setRepairDetailsYesNo("Yes");
                yesRLayout.setBackground(getDrawable(R.drawable.condition_bg));
                noRLayout.setBackground(getDrawable(R.drawable.check_bg));
                yesRCheck.setChecked(true);
                noRCheck.setChecked(false);
                break;

            case R.id.noRLayout:
                undergoneRepairs = false;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    repairNo.setTextColor(getColor(R.color.white));
                    repairYes.setTextColor(getColor(R.color.blackText));
                }
                userSession.setRepairDetailsYesNo("No");
                noRLayout.setBackground(getDrawable(R.drawable.condition_bg));
                yesRLayout.setBackground(getDrawable(R.drawable.check_bg));
                noRCheck.setChecked(true);
                yesRCheck.setChecked(false);
                break;

            case R.id.threeMonthsLayout:
                warrantyUtilizedMonth = 1;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    warrantyFirst.setTextColor(getColor(R.color.white));
                    warrantySecond.setTextColor(getColor(R.color.blackText));
                    warrantyThird.setTextColor(getColor(R.color.blackText));
                    warrantyFourth.setTextColor(getColor(R.color.blackText));
                }
                userSession.setDeviceHasUndergoneRepairsMonths("0 to 3 months");
                threeMonthsLayout.setBackground(getDrawable(R.drawable.condition_bg));
                tenMonthLayout.setBackground(getDrawable(R.drawable.check_bg));
                notAvailableLayout.setBackground(getDrawable(R.drawable.check_bg));
                moreThanMonthLayout.setBackground(getDrawable(R.drawable.check_bg));
                threeMonthsCheck.setChecked(true);
                tenMonthCheck.setChecked(false);
                notAvailableCheck.setChecked(false);
                moreThanMonthCheck.setChecked(false);
                break;

            case R.id.tenMonthLayout:
                warrantyUtilizedMonth = 2;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    warrantyFirst.setTextColor(getColor(R.color.blackText));
                    warrantySecond.setTextColor(getColor(R.color.white));
                    warrantyThird.setTextColor(getColor(R.color.blackText));
                    warrantyFourth.setTextColor(getColor(R.color.blackText));
                }
                userSession.setDeviceHasUndergoneRepairsMonths("3 to 10 months");
                tenMonthLayout.setBackground(getDrawable(R.drawable.condition_bg));
                threeMonthsLayout.setBackground(getDrawable(R.drawable.check_bg));
                notAvailableLayout.setBackground(getDrawable(R.drawable.check_bg));
                moreThanMonthLayout.setBackground(getDrawable(R.drawable.check_bg));
                tenMonthCheck.setChecked(true);
                threeMonthsCheck.setChecked(false);
                notAvailableCheck.setChecked(false);
                moreThanMonthCheck.setChecked(false);
                break;

            case R.id.notAvailableLayout:
                warrantyUtilizedMonth = 4;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    warrantyFirst.setTextColor(getColor(R.color.blackText));
                    warrantySecond.setTextColor(getColor(R.color.blackText));
                    warrantyThird.setTextColor(getColor(R.color.white));
                    warrantyFourth.setTextColor(getColor(R.color.blackText));
                }
                userSession.setDeviceHasUndergoneRepairsMonths("Not available");
                notAvailableLayout.setBackground(getDrawable(R.drawable.condition_bg));
                threeMonthsLayout.setBackground(getDrawable(R.drawable.check_bg));
                tenMonthLayout.setBackground(getDrawable(R.drawable.check_bg));
                moreThanMonthLayout.setBackground(getDrawable(R.drawable.check_bg));
                notAvailableCheck.setChecked(true);
                threeMonthsCheck.setChecked(false);
                tenMonthCheck.setChecked(false);
                moreThanMonthCheck.setChecked(false);
                break;

            case R.id.moreThanMonthLayout:
                warrantyUtilizedMonth = 3;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    warrantyFirst.setTextColor(getColor(R.color.blackText));
                    warrantySecond.setTextColor(getColor(R.color.blackText));
                    warrantyThird.setTextColor(getColor(R.color.blackText));
                    warrantyFourth.setTextColor(getColor(R.color.white));
                }
                userSession.setDeviceHasUndergoneRepairsMonths("More than 10 Months");
                moreThanMonthLayout.setBackground(getDrawable(R.drawable.condition_bg));
                threeMonthsLayout.setBackground(getDrawable(R.drawable.check_bg));
                tenMonthLayout.setBackground(getDrawable(R.drawable.check_bg));
                notAvailableLayout.setBackground(getDrawable(R.drawable.check_bg));
                moreThanMonthCheck.setChecked(true);
                threeMonthsCheck.setChecked(false);
                tenMonthCheck.setChecked(false);
                notAvailableCheck.setChecked(false);
                break;

            case R.id.continueBtn:
                if (!userSession.getDeviceConditionYesNo().equalsIgnoreCase("")
                        && !userSession.getRepairDetailsYesNo().equalsIgnoreCase("")
                        && !userSession.getDeviceHasUndergoneRepairsMonths().equalsIgnoreCase("")) {
                    // device On/Off json
                    Gson gson = new Gson();
                    JsonObject deviceOnOff = new JsonObject();
                    deviceOnOff.addProperty("isDeviceOn", isDeviceOn);
                    userSession.setDeviceOnOffJson(gson.toJson(deviceOnOff));
                    System.out.println("Device On/Off Json :- " + gson.toJson(deviceOnOff));

                    // undergone repairs json
                    JsonObject repair = new JsonObject();
                    repair.addProperty("undergone_repairs", undergoneRepairs);
                    userSession.setRepairDetailsJson(repair.toString());
                    System.out.println("Repaire Json :- " + repair);

                    // device warranty utilized json
                    JsonObject warrantyUtilized = new JsonObject();
                    warrantyUtilized.addProperty("warranty_Utilized_Month", warrantyUtilizedMonth);
                    userSession.setBrandWarrantyUtilizedJson(warrantyUtilized.toString());
                    System.out.println("Warranty json :- " + warrantyUtilized);

                    startActivity(new Intent(mContext, DeviceDisplayTouchScreenActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                } else if (userSession.getDeviceConditionYesNo().equalsIgnoreCase("")) {
                    Toast.makeText(mContext, "Please check device On/Off options in Yes/No!", Toast.LENGTH_SHORT).show();
                } else if (userSession.getRepairDetailsYesNo().equalsIgnoreCase("")) {
                    Toast.makeText(mContext, "Please check undergone repairs options in Yes/No!", Toast.LENGTH_SHORT).show();
                } else if (userSession.getDeviceHasUndergoneRepairsMonths().equalsIgnoreCase("")) {
                    Toast.makeText(mContext, "Please check device warranty utilized options!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "Please check device On/Off options in Yes/No!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.viewAnswerBtn:
                if (!userSession.getDeviceConditionYesNo().equalsIgnoreCase("")) {
                    startActivity(new Intent(mContext, ViewAnswerDetailsActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                } else {
                    Toast.makeText(mContext, "Please check Yes/No!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}