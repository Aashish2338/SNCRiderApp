package com.amsys.sncriderapp.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.amsys.sncriderapp.ApiCallService;
import com.amsys.sncriderapp.Model.UserProfileData;
import com.amsys.sncriderapp.R;
import com.amsys.sncriderapp.ResponseModel.UserProfileResponse;
import com.amsys.sncriderapp.Utilities.ApiNetworkClient;
import com.amsys.sncriderapp.Utilities.NetworkStatus;
import com.amsys.sncriderapp.Utilities.UserSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private UserSession userSession;
    private TextView userName, mobileNumber, emailId, date, time;
    private LinearLayout logoutLayout;
    private Date oneWayTripDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        mContext = this;
        userSession = new UserSession(mContext);
        setNotificationStatusColor();

        getProfileLayoutUiIdFind();

        if (NetworkStatus.isNetworkAvailable(mContext)) {
            getUserProfileDetails();
        } else {
            Toast.makeText(mContext, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
        }

        logoutLayout.setOnClickListener(this);
    }

    private void getUserProfileDetails() {
        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String tokenKey = "Bearer " + userSession.getToken();
        System.out.println("Token Key value :- " + tokenKey);

        try {
            ApiCallService apiClient = ApiNetworkClient.getSncRiderRetrofit().create(ApiCallService.class);
            Call<UserProfileResponse> profileResponseCall = apiClient.getUserProfile(userSession.getUserMobileNumber(), tokenKey);
            profileResponseCall.enqueue(new Callback<UserProfileResponse>() {
                @Override
                public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatusCode() == 200) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            if (response.body().getData() != null) {
                                setUserProfileData(response.body().getData());
                            }
                        } else {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            Toast.makeText(mContext, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(mContext, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserProfileResponse> call, Throwable t) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    System.out.println("Throwable " + t.getStackTrace());
                }
            });
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    private void setUserProfileData(UserProfileData data) {
        try {
            userName.setText(data.getFullName());
            mobileNumber.setText(data.getMobileNo());
            emailId.setText(data.getEmail());

            System.out.println("Created Date :- " + parseDateToddMMyyyy(data.getCreateDate().substring(0, 10)));
            date.setText(parseDateToddMMyyyy(data.getCreateDate().substring(0, 10)));
            time.setText(parseTimeToHHMMss(data.getCreateDate().substring(11, 19)));

        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    private String parseTimeToHHMMss(String time) {
        String inputPattern = "HH:mm:ss";
        String outputPattern = "HH:mm:ss";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String strTime = null;

        try {
            date = inputFormat.parse(time);
            strTime = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return strTime;
    }

    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd-MMM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    private void getProfileLayoutUiIdFind() {
        try {
            userName = (TextView) findViewById(R.id.userName);
            mobileNumber = (TextView) findViewById(R.id.mobileNumber);
            emailId = (TextView) findViewById(R.id.emailId);
            date = (TextView) findViewById(R.id.date);
            time = (TextView) findViewById(R.id.time);
            logoutLayout = (LinearLayout) findViewById(R.id.logoutLayout);

        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    private void setNotificationStatusColor() {
        try {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(mContext, R.color.share));

        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logoutLayout:
                userSession.logoutUser();
                startActivity(new Intent(mContext, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                UserProfileActivity.this.finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}