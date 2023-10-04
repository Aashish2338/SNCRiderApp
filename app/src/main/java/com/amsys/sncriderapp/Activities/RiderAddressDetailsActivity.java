package com.amsys.sncriderapp.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.amsys.sncriderapp.ApiCallService;
import com.amsys.sncriderapp.Model.DistrictsData;
import com.amsys.sncriderapp.Model.StateData;
import com.amsys.sncriderapp.R;
import com.amsys.sncriderapp.ResponseModel.DistrictsReponse;
import com.amsys.sncriderapp.ResponseModel.StateResponse;
import com.amsys.sncriderapp.ResponseModel.UserProfileResponse;
import com.amsys.sncriderapp.Utilities.ApiNetworkClient;
import com.amsys.sncriderapp.Utilities.NetworkStatus;
import com.amsys.sncriderapp.Utilities.UserSession;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiderAddressDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private UserSession userSession;
    private EditText fullNameEt, flatDetailEt, pinCodeEt, mobileNumberEt, areaDetailsEt;
    private Spinner stateSpinner, districtSpinner;
    private AppCompatButton saveAddressBtn;
    private List<StateData> data = null;
    private List<DistrictsData> dataDistrict;
    private String[] stateNameArray, districtNameArray;
    private List<String> stateIdList = new ArrayList<>();
    private List<String> districtIdList = new ArrayList<>();
    private int stateId, districtId;
    private String stateName, districtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_address_details);
        mContext = this;
        userSession = new UserSession(mContext);

        getAddressLayoutUiIdFind();

        saveAddressBtn.setOnClickListener(this);

        if (NetworkStatus.isNetworkAvailable(mContext)) {
            getAllStates();
        } else {
            Toast.makeText(mContext, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getAddressLayoutUiIdFind() {
        try {
            fullNameEt = (EditText) findViewById(R.id.fullNameEt);
            flatDetailEt = (EditText) findViewById(R.id.flatDetailEt);
            pinCodeEt = (EditText) findViewById(R.id.pinCodeEt);
            mobileNumberEt = (EditText) findViewById(R.id.mobileNumberEt);
            areaDetailsEt = (EditText) findViewById(R.id.areaDetailsEt);
            stateSpinner = (Spinner) findViewById(R.id.stateSpinner);
            districtSpinner = (Spinner) findViewById(R.id.districtSpinner);
            saveAddressBtn = (AppCompatButton) findViewById(R.id.saveAddressBtn);

        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    private void getAllStates() {
        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        try {
            ApiCallService apiClient = ApiNetworkClient.getSncRiderRetrofit().create(ApiCallService.class);
            Call<StateResponse> stateResponseCall = apiClient.getAllStates();
            stateResponseCall.enqueue(new Callback<StateResponse>() {
                @Override
                public void onResponse(Call<StateResponse> call, Response<StateResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatusCode() == 200) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            data = response.body().getData();
                            if (data.size() >= 0) {
                                data = response.body().getData();
                                System.out.println("Everything is fine all brand device lists!");
                                setAllStatesListData(data);
                            } else {
                                Toast.makeText(getApplicationContext(), "Data not found!", Toast.LENGTH_SHORT).show();
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
                public void onFailure(Call<StateResponse> call, Throwable t) {
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

    private void setAllStatesListData(List<StateData> data) {
        try {
            if (data.size() >= 0) {
                stateNameArray = new String[data.size()];
                for (int i = 0; i < data.size(); i++) {
                    stateIdList.add(data.get(i).getStateId().toString());
                    stateNameArray[i] = data.get(i).getStateName();
                }
                ArrayAdapter<String> stateAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, stateNameArray);
                stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                stateSpinner.setAdapter(stateAdapter);
                stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        stateId = Integer.parseInt(stateIdList.get(i));
                        stateName = stateNameArray[i];
                        if (NetworkStatus.isNetworkAvailable(mContext)) {
                            getDistrictDetails(stateId);
                        } else {
                            Toast.makeText(mContext, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    private void getDistrictDetails(int stateId) {
        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        try {
            ApiCallService apiClient = ApiNetworkClient.getSncRiderRetrofit().create(ApiCallService.class);
            Call<DistrictsReponse> districtsReponseCall = apiClient.getAllDistricts(stateId);
            districtsReponseCall.enqueue(new Callback<DistrictsReponse>() {
                @Override
                public void onResponse(Call<DistrictsReponse> call, Response<DistrictsReponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatusCode() == 200) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                                dataDistrict = response.body().getData();
                                if (dataDistrict.size() >= 0) {
                                    dataDistrict = response.body().getData();
                                    System.out.println("Everything is fine all District device lists!");
                                    setAllDistrictListData(dataDistrict);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Data not found!", Toast.LENGTH_SHORT).show();
                                }
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
                public void onFailure(Call<DistrictsReponse> call, Throwable t) {
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

    private void setAllDistrictListData(List<DistrictsData> dataDistrict) {
        try {
            districtNameArray = new String[dataDistrict.size()];
            for (int j = 0; j < dataDistrict.size(); j++) {
                districtIdList.add(dataDistrict.get(j).getDistrictId().toString());
                districtNameArray[j] = dataDistrict.get(j).getDistrictName();
            }
            ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, districtNameArray);
            districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            districtSpinner.setAdapter(districtAdapter);
            districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    districtId = Integer.parseInt(districtIdList.get(i));
                    districtName = districtNameArray[i];
//                    Toast.makeText(mContext, String.valueOf(districtId), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveAddressBtn:
                if (isAddressValidate()) {
                    if (NetworkStatus.isNetworkAvailable(mContext)) {
                        getAddressDetails(fullNameEt.getText().toString(), mobileNumberEt.getText().toString(),
                                flatDetailEt.getText().toString(), areaDetailsEt.getText().toString(), stateName, districtName, districtId, pinCodeEt.getText().toString());
                    } else {
                        Toast.makeText(mContext, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    private void getAddressDetails(String fullName, String mobileNumber, String flatNumber, String areaDetails, String stateName, String districtName, int districtId, String pinCode) {

        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String tokenKey = "Bearer " + userSession.getToken();
        System.out.println("Token Key value :- " + tokenKey);

        JsonObject object = new JsonObject();
        object.addProperty("userId", userSession.getUserId());
        object.addProperty("fullName", fullName);
        object.addProperty("flatDetail", flatNumber);
        object.addProperty("areaDetail", areaDetails);
        object.addProperty("pincode", pinCode);
        object.addProperty("districtId", districtId);
        object.addProperty("mobileNo", mobileNumber);
        object.addProperty("districtName", districtName);
        object.addProperty("stateName", stateName);

        System.out.println("Json Value :- " + object.toString());

        try {
            ApiCallService apiClient = ApiNetworkClient.getSncRiderRetrofit().create(ApiCallService.class);
            Call<UserProfileResponse> responseCall = apiClient.addUserAddress(object, tokenKey);
            responseCall.enqueue(new Callback<UserProfileResponse>() {
                @Override
                public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatusCode() == 200) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            System.out.println("Response of Address Save :- " + response.body());
                            Toast.makeText(mContext, "Address successfully saved!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(mContext, DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
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

    private boolean isAddressValidate() {
        try {
            if (fullNameEt.getText().toString().trim().isEmpty()) {
                fullNameEt.setError("Enter full name");
                fullNameEt.requestFocus();
                return false;
            } else if (flatDetailEt.getText().toString().trim().isEmpty()) {
                flatDetailEt.setError("Enter flat details");
                flatDetailEt.requestFocus();
                return false;
            } else if (pinCodeEt.getText().toString().trim().isEmpty()) {
                pinCodeEt.setError("Enter pin code");
                pinCodeEt.requestFocus();
                return false;
            } else if (mobileNumberEt.getText().toString().trim().isEmpty()) {
                mobileNumberEt.setError("Enter mobile number");
                mobileNumberEt.requestFocus();
                return false;
            } else if (areaDetailsEt.getText().toString().trim().isEmpty()) {
                areaDetailsEt.setError("Enter area details");
                areaDetailsEt.requestFocus();
                return false;
            }
        } catch (Exception exp) {
            exp.getStackTrace();
        }
        return true;
    }
}