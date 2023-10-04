package com.amsys.sncriderapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;

import com.amsys.sncriderapp.R;
import com.amsys.sncriderapp.Utilities.UserSession;
import com.google.gson.JsonObject;

public class BrandWarrantyUtilizedActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private UserSession userSession;
    private ImageView backBrandImg;
    private AppCompatButton continueBrandBtn, viewAnswerBtn;
    private AppCompatCheckBox threeMonthsCheck, tenMonthCheck, notAvailableCheck, moreThanMonthCheck;
    private LinearLayout threeMonthsLayout, tenMonthLayout, notAvailableLayout, moreThanMonthLayout;
    private int warrantyUtilizedMonth = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_warranty_utilized);
        mContext = this;
        userSession = new UserSession(mContext);

        getWarrantyLayoutUiIdFind();

        backBrandImg.setOnClickListener(this);
        continueBrandBtn.setOnClickListener(this);
        viewAnswerBtn.setOnClickListener(this);
        threeMonthsLayout.setOnClickListener(this);
        tenMonthLayout.setOnClickListener(this);
        notAvailableLayout.setOnClickListener(this);
        moreThanMonthLayout.setOnClickListener(this);

    }

    private void getWarrantyLayoutUiIdFind() {
        try {
            backBrandImg = (ImageView) findViewById(R.id.backBrandImg);
            continueBrandBtn = (AppCompatButton) findViewById(R.id.continueBrandBtn);
            viewAnswerBtn = (AppCompatButton) findViewById(R.id.viewAnswerBtn);
            threeMonthsCheck = (AppCompatCheckBox) findViewById(R.id.threeMonthsCheck);
            tenMonthCheck = (AppCompatCheckBox) findViewById(R.id.tenMonthCheck);
            notAvailableCheck = (AppCompatCheckBox) findViewById(R.id.notAvailableCheck);
            moreThanMonthCheck = (AppCompatCheckBox) findViewById(R.id.moreThanMonthCheck);
            threeMonthsLayout = (LinearLayout) findViewById(R.id.threeMonthsLayout);
            tenMonthLayout = (LinearLayout) findViewById(R.id.tenMonthLayout);
            notAvailableLayout = (LinearLayout) findViewById(R.id.notAvailableLayout);
            moreThanMonthLayout = (LinearLayout) findViewById(R.id.moreThanMonthLayout);

            userSession.setDeviceHasUndergoneRepairsMonths("");
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBrandImg:
                userSession.setDeviceHasUndergoneRepairsMonths("");
                onBackPressed();
                break;

            case R.id.threeMonthsLayout:
                warrantyUtilizedMonth = 1;
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

            case R.id.continueBrandBtn:
                if (!userSession.getDeviceHasUndergoneRepairsMonths().equalsIgnoreCase("")) {
                    JsonObject warrantyUtilized = new JsonObject();
                    warrantyUtilized.addProperty("warranty_Utilized_Month", warrantyUtilizedMonth);
                    userSession.setBrandWarrantyUtilizedJson(warrantyUtilized.toString());
                    System.out.println("Warranty json :- " + warrantyUtilized);
                    startActivity(new Intent(mContext, DeviceBodyInformationActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                } else {
                    Toast.makeText(mContext, "Please check device warranty utilized!", Toast.LENGTH_SHORT).show();
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