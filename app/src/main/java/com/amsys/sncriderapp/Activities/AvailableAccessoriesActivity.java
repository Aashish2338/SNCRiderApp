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

public class AvailableAccessoriesActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private UserSession userSession;
    private ImageView backAccessoriesImg, earphoneImg, iMEIImg, chargerImg;
    private TextView earphoneTxt, iMEITxt, chargerTxt;
    private AppCompatButton continueAcsBtn, viewAnswerBtn, skipAcsBtn;
    private CardView earphoneCard, imeiCard, chargerCard;
    private boolean earphone = false, boxWithSameIMEI = false, originalCharger = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_accessories);
        mContext = this;
        userSession = new UserSession(mContext);

        getAccessoriesLayoutUIIdFind();

        backAccessoriesImg.setOnClickListener(this);
        continueAcsBtn.setOnClickListener(this);
        skipAcsBtn.setOnClickListener(this);
        viewAnswerBtn.setOnClickListener(this);
        earphoneCard.setOnClickListener(this);
        imeiCard.setOnClickListener(this);
        chargerCard.setOnClickListener(this);

    }

    private void getAccessoriesLayoutUIIdFind() {
        try {
            backAccessoriesImg = (ImageView) findViewById(R.id.backAccessoriesImg);
            earphoneImg = (ImageView) findViewById(R.id.earphoneImg);
            iMEIImg = (ImageView) findViewById(R.id.iMEIImg);
            chargerImg = (ImageView) findViewById(R.id.chargerImg);
            continueAcsBtn = (AppCompatButton) findViewById(R.id.continueAcsBtn);
            skipAcsBtn = (AppCompatButton) findViewById(R.id.skipAcsBtn);
            viewAnswerBtn = (AppCompatButton) findViewById(R.id.viewAnswerBtn);
            earphoneCard = (CardView) findViewById(R.id.earphoneCard);
            imeiCard = (CardView) findViewById(R.id.imeiCard);
            chargerCard = (CardView) findViewById(R.id.chargerCard);
            earphoneTxt = (TextView) findViewById(R.id.earphoneTxt);
            iMEITxt = (TextView) findViewById(R.id.iMEITxt);
            chargerTxt = (TextView) findViewById(R.id.chargerTxt);

            userSession.setEarphoneOfAccessoriesDetails("");
            userSession.setBoxWithSameImeiOfAccessoriesDetails("");
            userSession.setOriginalChargerOfAccessoriesDetails("");

        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backAccessoriesImg:
                userSession.setEarphoneOfAccessoriesDetails("");
                userSession.setBoxWithSameImeiOfAccessoriesDetails("");
                userSession.setOriginalChargerOfAccessoriesDetails("");
                onBackPressed();
                break;

            case R.id.earphoneCard:
                if (earphone == false) {
                    earphone = true;
                    userSession.setEarphoneOfAccessoriesDetails("Earphone");
                    earphoneCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    earphoneTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    earphoneImg.setVisibility(View.VISIBLE);
                } else if (earphone == true) {
                    earphone = false;
                    userSession.setEarphoneOfAccessoriesDetails("");
                    earphoneCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    earphoneTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    earphoneImg.setVisibility(View.INVISIBLE);
                } else {
                    earphoneCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    earphoneTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    earphoneImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.imeiCard:
                if (boxWithSameIMEI == false) {
                    boxWithSameIMEI = true;
                    userSession.setBoxWithSameImeiOfAccessoriesDetails("Box with same IMEI");
                    imeiCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    iMEITxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    iMEIImg.setVisibility(View.VISIBLE);
                } else if (boxWithSameIMEI == true) {
                    boxWithSameIMEI = false;
                    userSession.setBoxWithSameImeiOfAccessoriesDetails("");
                    imeiCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    iMEITxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    iMEIImg.setVisibility(View.INVISIBLE);
                } else {
                    imeiCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    iMEITxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    iMEIImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.chargerCard:
                if (originalCharger == false) {
                    originalCharger = true;
                    userSession.setOriginalChargerOfAccessoriesDetails("Original Charger");
                    chargerCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    chargerTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    chargerImg.setVisibility(View.VISIBLE);
                } else if (originalCharger == true) {
                    originalCharger = false;
                    userSession.setOriginalChargerOfAccessoriesDetails("");
                    chargerCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    chargerTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    chargerImg.setVisibility(View.INVISIBLE);
                } else {
                    chargerCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    chargerTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    chargerImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.skipAcsBtn:
                JsonObject accessoriesS = new JsonObject();
                accessoriesS.addProperty("earphone", earphone);
                accessoriesS.addProperty("box_with_same_IMEI", boxWithSameIMEI);
                accessoriesS.addProperty("original_Charger", originalCharger);
                userSession.setAvailableAccessoriesJson(accessoriesS.toString());
                System.out.println("Accessories json :- " + accessoriesS);
                startActivity(new Intent(mContext, FunctionalIssueActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                break;

            case R.id.continueAcsBtn:
                if (earphone == true || boxWithSameIMEI == true || originalCharger == true) {
                    JsonObject accessories = new JsonObject();
                    accessories.addProperty("earphone", earphone);
                    accessories.addProperty("box_with_same_IMEI", boxWithSameIMEI);
                    accessories.addProperty("original_Charger", originalCharger);
                    userSession.setAvailableAccessoriesJson(accessories.toString());
                    System.out.println("Accessories json :- " + accessories);
                    startActivity(new Intent(mContext, FunctionalIssueActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                } else {
                    Toast.makeText(mContext, "Please select any accessories!", Toast.LENGTH_SHORT).show();
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