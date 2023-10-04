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

import com.amsys.sncriderapp.Adapters.CompleteOrderAdapter;
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

public class CompleteOrderActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private UserSession userSession;
    private ImageView backOrderImg;
    private RecyclerView completeOrderRcView;
    private LinearLayout oopsDataLayout;
    private CompleteOrderAdapter completeOrderAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<RiderOrderConfirmPickupData> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_order);
        mContext = this;
        userSession = new UserSession(mContext);

        getOrderLayoutUiIdFind();

        backOrderImg.setOnClickListener(this);

        if (NetworkStatus.isNetworkAvailable(mContext)) {
            getConfirmedPickupRiderOrder();
        } else {
            Toast.makeText(mContext, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getConfirmedPickupRiderOrder() {
        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        try {
            ApiCallService apiClient = ApiNetworkClient.getSncRiderRetrofit().create(ApiCallService.class);
            Call<RiderOrderConfirmPickupResponse> pickupResponseCall = apiClient.getConfirmedPickupRiderOrderDetail(userSession.getUserId());
            pickupResponseCall.enqueue(new Callback<RiderOrderConfirmPickupResponse>() {
                @Override
                public void onResponse(Call<RiderOrderConfirmPickupResponse> call, Response<RiderOrderConfirmPickupResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatusCode() == 200) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            if (response.body().getData() != null) {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                data = response.body().getData();
                                if (!data.isEmpty()) {
                                    if (progressDialog.isShowing()) {
                                        progressDialog.dismiss();
                                    }
                                    oopsDataLayout.setVisibility(View.GONE);
                                    if (data.size() >= 0) {
                                        setOrderConfirmPickupList(data);
                                        System.out.println("Api Response :- " + data);
                                    } else {
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
                                Toast.makeText(mContext, "Data not found!", Toast.LENGTH_SHORT).show();
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
                    System.out.println("Throwable :- " + t.getStackTrace());
                }
            });
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    private void setOrderConfirmPickupList(List<RiderOrderConfirmPickupData> data) {
        try {
            completeOrderAdapter = new CompleteOrderAdapter(mContext, data);
            linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
            completeOrderRcView.setLayoutManager(linearLayoutManager);
            completeOrderRcView.setAdapter(completeOrderAdapter);
            completeOrderAdapter.notifyDataSetChanged();
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    private void getOrderLayoutUiIdFind() {
        try {
            backOrderImg = (ImageView) findViewById(R.id.backOrderImg);
            completeOrderRcView = (RecyclerView) findViewById(R.id.completeOrderRcView);
            oopsDataLayout = (LinearLayout) findViewById(R.id.oopsDataLayout);

        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backOrderImg:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}