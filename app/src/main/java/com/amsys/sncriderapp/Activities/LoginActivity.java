package com.amsys.sncriderapp.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.amsys.sncriderapp.ApiCallService;
import com.amsys.sncriderapp.R;
import com.amsys.sncriderapp.ResponseModel.OtpResponse;
import com.amsys.sncriderapp.ResponseModel.UserProfileResponse;
import com.amsys.sncriderapp.Utilities.ApiNetworkClient;
import com.amsys.sncriderapp.Utilities.NetworkStatus;
import com.amsys.sncriderapp.Utilities.UserSession;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private UserSession userSession;
    private EditText et_UserEmail;
    private AppCompatButton verificationCodeBtn;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        userSession = new UserSession(mContext);

        getLayoutUiIdFinds();

        verificationCodeBtn.setOnClickListener(this);
    }

    private void getLayoutUiIdFinds() {
        try {
            et_UserEmail = (EditText) findViewById(R.id.et_UserEmail);
            verificationCodeBtn = (AppCompatButton) findViewById(R.id.verificationCodeBtn);

        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.verificationCodeBtn:
                if (isEmailValidate()) {
                    if (NetworkStatus.isNetworkAvailable(mContext)) {
                        getVerificationCodeLogin(et_UserEmail.getText().toString());
                    } else {
                        Toast.makeText(mContext, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    private void getVerificationCodeLogin(String mailID) {
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        try {
            ApiCallService apiClient = ApiNetworkClient.getSncRiderRetrofit().create(ApiCallService.class);
            Call<UserProfileResponse> responseCall = apiClient.getValidateUser(createApiJsonObjectValidate(mailID, "123456"));
            responseCall.enqueue(new Callback<UserProfileResponse>() {
                @Override
                public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatusCode() == 200) {
                            System.out.println("Login Response Data :- " + response.body().toString());
                            if (response.body().getData() != null) {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                userSession.createLoginSession(mailID, "123456");
                                userSession.setUserId(response.body().getData().getUserId());
                                userSession.setUserFullName(response.body().getData().getFullName());
                                userSession.setUserMobileNumber(response.body().getData().getMobileNo());
                                userSession.setUserMail(response.body().getData().getEmail());
                                userSession.setUserTypeDetail(response.body().getData().getUserTypeDetail());
                                userSession.setUserType(response.body().getData().getUserType());
                                if (response.body().getData().getUserType() == 2) {
                                    getOtpData(mailID);
                                } else {
                                    Toast.makeText(mContext, "This user is not registered for rider!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                Toast.makeText(mContext, "This user is not registered for rider!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            Toast.makeText(mContext, "This user is not registered for rider!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(mContext, "This user is not registered for rider!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserProfileResponse> call, Throwable t) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    System.out.println("Throwable :- " + t.getStackTrace());
                }
            });
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    private void getOtpData(String mailID) {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        try {
            ApiCallService apiClient = ApiNetworkClient.getSncRiderRetrofit().create(ApiCallService.class);
            Call<OtpResponse> otpResponseCall = apiClient.getOtp(createApiJsonObjectValidate(mailID, "123456"));
            otpResponseCall.enqueue(new Callback<OtpResponse>() {
                @Override
                public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatusCode() == 200) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            System.out.println("Otp Response :- " + response.body().toString());
                            if (response.body().getData() == true) {
                                startActivity(new Intent(mContext, VerificationCodeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            } else {
                                Toast.makeText(mContext, "Please try again!", Toast.LENGTH_SHORT).show();
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
                public void onFailure(Call<OtpResponse> call, Throwable t) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    System.out.println("Throwable :- " + t.getStackTrace());
                }
            });
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    private JsonObject createApiJsonObjectValidate(String mailID, String password) {
        JsonObject gsonJsonObjectValidate = new JsonObject();
        try {
            JSONObject jsonMain = new JSONObject();
            jsonMain.put("userId", mailID);
            jsonMain.put("password", password);

            JsonParser jsonParser = new JsonParser();
            gsonJsonObjectValidate = (JsonObject) jsonParser.parse(jsonMain.toString());

        } catch (Exception exp) {
            exp.getStackTrace();
        }
        return gsonJsonObjectValidate;
    }

    private boolean isEmailValidate() {
        try {
            if (et_UserEmail.getText().toString().trim().isEmpty()) {
                et_UserEmail.setError("Enter your mobile number");
                et_UserEmail.requestFocus();
                return false;
            }
        } catch (Exception exp) {
            exp.getStackTrace();
        }
        return true;
    }
}