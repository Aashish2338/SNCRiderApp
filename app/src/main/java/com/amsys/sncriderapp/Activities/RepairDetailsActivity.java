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

public class RepairDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private UserSession userSession;
    private ImageView backRepairImg;
    private AppCompatButton continueRepairBtn, viewAnswerBtn;
    private AppCompatCheckBox yesRCheck, noRCheck;
    private LinearLayout yesRLayout, noRLayout;
    private boolean undergoneRepairs = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_details);
        mContext = this;
        userSession = new UserSession(mContext);

        getRepairLayoutUiIdFind();

        backRepairImg.setOnClickListener(this);
        continueRepairBtn.setOnClickListener(this);
        viewAnswerBtn.setOnClickListener(this);
        yesRLayout.setOnClickListener(this);
        noRLayout.setOnClickListener(this);

    }

    private void getRepairLayoutUiIdFind() {
        try {
            backRepairImg = (ImageView) findViewById(R.id.backRepairImg);
            continueRepairBtn = (AppCompatButton) findViewById(R.id.continueRepairBtn);
            viewAnswerBtn = (AppCompatButton) findViewById(R.id.viewAnswerBtn);
            yesRCheck = (AppCompatCheckBox) findViewById(R.id.yesRCheck);
            noRCheck = (AppCompatCheckBox) findViewById(R.id.noRCheck);
            yesRLayout = (LinearLayout) findViewById(R.id.yesRLayout);
            noRLayout = (LinearLayout) findViewById(R.id.noRLayout);

            userSession.setRepairDetailsYesNo("");

        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backRepairImg:
                userSession.setRepairDetailsYesNo("");
                onBackPressed();
                break;

            case R.id.yesRLayout:
                undergoneRepairs = true;
                userSession.setRepairDetailsYesNo("Yes");
                yesRLayout.setBackground(getDrawable(R.drawable.condition_bg));
                noRLayout.setBackground(getDrawable(R.drawable.check_bg));
                yesRCheck.setChecked(true);
                noRCheck.setChecked(false);
                break;

            case R.id.noRLayout:
                undergoneRepairs = false;
                userSession.setRepairDetailsYesNo("No");
                noRLayout.setBackground(getDrawable(R.drawable.condition_bg));
                yesRLayout.setBackground(getDrawable(R.drawable.check_bg));
                noRCheck.setChecked(true);
                yesRCheck.setChecked(false);
                break;

            case R.id.continueRepairBtn:
                if (!userSession.getRepairDetailsYesNo().equalsIgnoreCase("")) {
                    JsonObject repair = new JsonObject();
                    repair.addProperty("undergone_repairs", undergoneRepairs);
                    userSession.setRepairDetailsJson(repair.toString());
                    System.out.println("Repaire Json :- " + repair);
                    startActivity(new Intent(mContext, BrandWarrantyUtilizedActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                } else {
                    Toast.makeText(mContext, "Please check undergone repairs!", Toast.LENGTH_SHORT).show();
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