package com.amsys.sncriderapp.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amsys.sncriderapp.Adapters.DeviceLeadsAdapter;
import com.amsys.sncriderapp.ApiCallService;
import com.amsys.sncriderapp.Model.RiderOrderConfirmPickupData;
import com.amsys.sncriderapp.R;
import com.amsys.sncriderapp.ResponseModel.RiderOrderConfirmPickupResponse;
import com.amsys.sncriderapp.Utilities.ApiNetworkClient;
import com.amsys.sncriderapp.Utilities.NetworkStatus;
import com.amsys.sncriderapp.Utilities.UserSession;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceLeadsActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private UserSession userSession;
    private ImageView backDeviceLeadsImg;
    private RecyclerView deviceLeadsRcView;
    private LinearLayout oopsDataLayout;
    private DeviceLeadsAdapter deviceLeadsAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<RiderOrderConfirmPickupData> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_leads);
        mContext = this;
        userSession = new UserSession(mContext);

        getLayoutDeviceLeadsUiIdFind();

        backDeviceLeadsImg.setOnClickListener(this);

        if (NetworkStatus.isNetworkAvailable(mContext)) {
            getRiderOrderForConfirmPickup();
        } else {
            Toast.makeText(mContext, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getRiderOrderForConfirmPickup() {
        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        try {
            ApiCallService apiClient = ApiNetworkClient.getSncRiderRetrofit().create(ApiCallService.class);
            Call<RiderOrderConfirmPickupResponse> riderOrderConfirmPickupResponseCall = apiClient.getRiderOrderListPickup();
            riderOrderConfirmPickupResponseCall.enqueue(new Callback<RiderOrderConfirmPickupResponse>() {
                @Override
                public void onResponse(Call<RiderOrderConfirmPickupResponse> call, Response<RiderOrderConfirmPickupResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatusCode() == 200) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            System.out.println("Api Response :- " + response.body().toString());
                            if (!response.body().getData().isEmpty()) {
                                data = response.body().getData();
                                if (data.size() >= 0) {
                                    if (progressDialog.isShowing()) {
                                        progressDialog.dismiss();
                                    }
                                    oopsDataLayout.setVisibility(View.GONE);
                                    setOrderConfirmPickup(data);
//                                        System.out.println("Api Response :- " + data);
                                } else {
                                    if (progressDialog.isShowing()) {
                                        progressDialog.dismiss();
                                    }
                                    oopsDataLayout.setVisibility(View.VISIBLE);
                                }
                            } else {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                oopsDataLayout.setVisibility(View.VISIBLE);
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
                public void onFailure(Call<RiderOrderConfirmPickupResponse> call, Throwable t) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    System.out.println("Error :- " + t.getStackTrace());
                }
            });
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    private void setOrderConfirmPickup(List<RiderOrderConfirmPickupData> data) {
        try {
            deviceLeadsAdapter = new DeviceLeadsAdapter(mContext, data);
            linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
            deviceLeadsRcView.setLayoutManager(linearLayoutManager);
            deviceLeadsRcView.setAdapter(deviceLeadsAdapter);
            deviceLeadsAdapter.notifyDataSetChanged();

        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    private void getLayoutDeviceLeadsUiIdFind() {
        try {
            backDeviceLeadsImg = (ImageView) findViewById(R.id.backDeviceLeadsImg);
            deviceLeadsRcView = (RecyclerView) findViewById(R.id.deviceLeadsRcView);
            oopsDataLayout = (LinearLayout) findViewById(R.id.oopsDataLayout);

        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backDeviceLeadsImg:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}