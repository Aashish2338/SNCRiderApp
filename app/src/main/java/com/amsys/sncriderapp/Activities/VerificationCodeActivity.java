package com.amsys.sncriderapp.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.amsys.sncriderapp.ApiCallService;
import com.amsys.sncriderapp.R;
import com.amsys.sncriderapp.ResponseModel.AddressResponse;
import com.amsys.sncriderapp.ResponseModel.LoginResponse;
import com.amsys.sncriderapp.ResponseModel.OtpResponse;
import com.amsys.sncriderapp.Utilities.ApiNetworkClient;
import com.amsys.sncriderapp.Utilities.NetworkStatus;
import com.amsys.sncriderapp.Utilities.UserSession;
import com.chaos.view.PinView;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerificationCodeActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private UserSession userSession;
    private PinView et_OtpCode;
    private AppCompatButton verifyCodeBtn;
    private TextView resendOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);
        mContext = this;
        userSession = new UserSession(mContext);

        getLayoutUiIDFinds();

        verifyCodeBtn.setOnClickListener(this);
        resendOtp.setOnClickListener(this);

    }

    private void getLayoutUiIDFinds() {
        try {
            et_OtpCode = (PinView) findViewById(R.id.et_OtpCode);
            verifyCodeBtn = (AppCompatButton) findViewById(R.id.verifyCodeBtn);
            resendOtp = (TextView) findViewById(R.id.resendOtp);

        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.verifyCodeBtn:
                if (isOTPValidate()) {
                    if (NetworkStatus.isNetworkAvailable(mContext)) {
                        getVerifyOTPCode(et_OtpCode.getText().toString());
                    } else {
                        Toast.makeText(mContext, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.resendOtp:
                if (NetworkStatus.isNetworkAvailable(mContext)) {
                    getResendOtpData(userSession.getUserMobileNumber());
                } else {
                    Toast.makeText(mContext, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void getResendOtpData(String userMobileNumber) {
        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userMobileNumber);
        jsonObject.addProperty("password", "123456");

        try {
            ApiCallService apiClient = ApiNetworkClient.getSncRiderRetrofit().create(ApiCallService.class);
            Call<OtpResponse> otpResponseCall = apiClient.getOtp(jsonObject);
            otpResponseCall.enqueue(new Callback<OtpResponse>() {
                @Override
                public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatusCode() == 200) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            if (response.body().getData() != null) {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                Toast.makeText(mContext, "OTP has been sent on your mobile number!", Toast.LENGTH_SHORT).show();
                            } else {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                Toast.makeText(mContext, "OTP is not send on your mobile number!", Toast.LENGTH_SHORT).show();
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
                    System.out.println("Throwable " + t.getStackTrace());
                }
            });
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    private boolean isOTPValidate() {
        try {
            if (et_OtpCode.getText().toString().trim().isEmpty()) {
                et_OtpCode.setError("Enter your OTP");
                et_OtpCode.requestFocus();
                return false;
            } else if (!et_OtpCode.getText().toString().equalsIgnoreCase(et_OtpCode.getText().toString())) {
                Toast.makeText(mContext, "Enter your valid OTP", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (Exception exp) {
            exp.getStackTrace();
        }
        return true;
    }

    private void getVerifyOTPCode(String oTPCode) {
        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        System.out.println("Api Request Login Data :- " + createApiJsonObjectLogin(userSession.getUserMobileNumber(), oTPCode).toString());

        try {
            ApiCallService apiClient = ApiNetworkClient.getSncRiderRetrofit().create(ApiCallService.class);
            Call<LoginResponse> loginResponseCall = apiClient.getLoginData(createApiJsonObjectLogin(userSession.getUserMobileNumber(), oTPCode));
            loginResponseCall.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatusCode() == 200) {
                            if (response.body().getData() != null) {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                System.out.println("Token Key :- " + response.body().getData().getAuthenticationToken());
                                userSession.setToken(response.body().getData().getAuthenticationToken());
                                if (NetworkStatus.isNetworkAvailable(mContext)) {
                                    getUserAddressDetails(userSession.getUserMobileNumber());
                                } else {
                                    Toast.makeText(mContext, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                userSession.setToken("");
                                Toast.makeText(mContext, "Token key is not generate!", Toast.LENGTH_SHORT).show();
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
                public void onFailure(Call<LoginResponse> call, Throwable t) {
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

    private void getUserAddressDetails(String userMobileNumber) {
        try {
            String tokenKey = "Bearer " + userSession.getToken();

            ApiCallService apiClient = ApiNetworkClient.getSncRiderRetrofit().create(ApiCallService.class);
            Call<AddressResponse> addressResponseCall = apiClient.getUserAddresses(userMobileNumber, tokenKey);
            addressResponseCall.enqueue(new Callback<AddressResponse>() {
                @Override
                public void onResponse(Call<AddressResponse> call, Response<AddressResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatusCode() == 200) {
                            if (response.body().getData() != null || !response.body().getData().isEmpty()) {
                                System.out.println("Hi Aashish Vishwakarma!");
                                startActivity(new Intent(mContext, DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                VerificationCodeActivity.this.finish();
                            } else {
                                startActivity(new Intent(mContext, RiderAddressDetailsActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                VerificationCodeActivity.this.finish();
                            }
                        } else {
                            Toast.makeText(mContext, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(mContext, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AddressResponse> call, Throwable t) {
                    Toast.makeText(mContext, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    private JsonObject createApiJsonObjectLogin(String userId, String password) {
        JsonObject gsonJsonObjectLogin = new JsonObject();
        try {
            JSONObject jsonMain = new JSONObject();
            jsonMain.put("userId", userId);
            jsonMain.put("password", password);

            JsonParser jsonParser = new JsonParser();
            gsonJsonObjectLogin = (JsonObject) jsonParser.parse(jsonMain.toString());

        } catch (Exception exp) {
            exp.getStackTrace();
        }
        return gsonJsonObjectLogin;
    }
}