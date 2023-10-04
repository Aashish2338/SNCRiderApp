package com.amsys.sncriderapp.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.amsys.sncriderapp.ApiCallService;
import com.amsys.sncriderapp.Model.CompleteOrderDetailData;
import com.amsys.sncriderapp.R;
import com.amsys.sncriderapp.ResponseModel.CompleteOrderDetailResponse;
import com.amsys.sncriderapp.Utilities.ApiNetworkClient;
import com.amsys.sncriderapp.Utilities.NetworkStatus;
import com.amsys.sncriderapp.Utilities.UserSession;
import com.google.gson.JsonObject;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Response;

public class DeviceLeadDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private UserSession userSession;
    private ImageView backDeviceLeadDetailsImg, deviceImg;
    private LinearLayout showOrderDetails;
    private AppCompatButton startDiagnoseBtn;
    private TextView deviceDetails, tradersName, location, tokenNumber, dateAndTime, deviceImeiNumber, partnerName;
    private TextView partnerPhoneNumber, pickUpDateTime, costumerName, costumerPhoneNUmber, devicePrice, costumerAddress;
    private TextView leadTxt;
    private int pickupScheduleId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_lead_details);
        mContext = this;
        userSession = new UserSession(mContext);

        getDeviceLeadDetailsLayoutUiIdFind();

        backDeviceLeadDetailsImg.setOnClickListener(this);
        startDiagnoseBtn.setOnClickListener(this);
        showOrderDetails.setOnClickListener(this);

        if (userSession.getPageDirectionStatus().equalsIgnoreCase("Open Leads")) {
            startDiagnoseBtn.setText("Confirm Pickup");
            leadTxt.setText("Open Lead Details");
        } else {
            startDiagnoseBtn.setText("Start Diagnose");
            leadTxt.setText("Accepted Lead Details");
        }

        if (NetworkStatus.isNetworkAvailable(mContext)) {
            getCompleteOrderDetails();
        } else {
            Toast.makeText(mContext, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getCompleteOrderDetails() {
        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        System.out.println("Open/Accepted Order Id :- " + userSession.getOrderId());
        userSession.setOrderId(userSession.getOrderId());

        try {
            ApiCallService apiClient = ApiNetworkClient.getSncRiderRetrofit().create(ApiCallService.class);
            Call<CompleteOrderDetailResponse> completeOrderDetailResponseCall = apiClient.getCompleteOrderDetails(userSession.getOrderId());
            completeOrderDetailResponseCall.enqueue(new retrofit2.Callback<CompleteOrderDetailResponse>() {
                @Override
                public void onResponse(Call<CompleteOrderDetailResponse> call, Response<CompleteOrderDetailResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatusCode() == 200) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            if (response.body().getData() != null) {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                setDataInTextView(response.body().getData());
                                if (userSession.getPageDirectionStatus().equalsIgnoreCase("Complete Leads")) {
                                    setDataInSessionForRiderConfirmation(response.body().getData());
                                } else {
                                    System.out.println("This is not applied for open leads!");
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
                public void onFailure(Call<CompleteOrderDetailResponse> call, Throwable t) {
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

    private void setDataInTextView(CompleteOrderDetailData data) {
        try {
            if (data.getPickupScheduleDetail() != null) {
                pickupScheduleId = data.getPickupScheduleDetail().getPickupScheduleId();
                System.out.println("pickupScheduleId :- " + pickupScheduleId);
            } else {
                pickupScheduleId = 0;
                System.out.println("pickupScheduleId :- " + pickupScheduleId);
            }

            if (data.getUserAddressDetail().getAddressId() != null) {
                userSession.setAddressId(data.getUserAddressDetail().getAddressId());
            } else {
                userSession.setAddressId(0);
            }

            if (data.getOrderStatuses() != null) {
                if (data.getOrderStatuses().get(0).getStatusId() != null) {
                    userSession.setOrderStatusId(data.getOrderStatuses().get(0).getStatusId());
                } else {
                    userSession.setOrderStatusId(0);
                }
            } else {
                userSession.setOrderStatusId(0);
                System.out.println("Not found Order Status Id!");
            }

            if (data.getEndUserOrderPriceDetail() != null) {
                if (data.getEndUserOrderPriceDetail().getExactPrice() != null) {
                    userSession.setExactPrice(data.getEndUserOrderPriceDetail().getExactPrice());
                    double amount = Double.parseDouble(data.getEndUserOrderPriceDetail().getExactPrice().toString());
                    DecimalFormat formatter = new DecimalFormat("#,###.00");
                    System.out.println(formatter.format(amount));
                    devicePrice.setText("₹" + formatter.format(amount));
                } else {
                    userSession.setExactPrice(0);
                    devicePrice.setText("N/A");
                }
                if (data.getEndUserOrderPriceDetail().getAgeing() != null) {
                    userSession.setAgeingId(data.getEndUserOrderPriceDetail().getAgeing());
                } else {
                    userSession.setAgeingId("");
                }

                if (data.getEndUserOrderPriceDetail().getTupc() != null) {
                    userSession.setTupcId(data.getEndUserOrderPriceDetail().getTupc());
                } else {
                    userSession.setTupcId("");
                }
            } else {
                if (data.getRiderOrderPriceDetail().getExactPrice() != null) {
                    userSession.setExactPrice(data.getRiderOrderPriceDetail().getExactPrice());
                    double amount = Double.parseDouble(data.getRiderOrderPriceDetail().getExactPrice().toString());
                    DecimalFormat formatter = new DecimalFormat("#,###.00");
                    System.out.println(formatter.format(amount));
                    devicePrice.setText("₹" + formatter.format(amount));
                } else {
                    userSession.setExactPrice(0);
                    devicePrice.setText("N/A");
                }
                if (data.getRiderOrderPriceDetail().getAgeing() != null) {
                    userSession.setAgeingId(data.getRiderOrderPriceDetail().getAgeing());
                } else {
                    userSession.setAgeingId("");
                }

                if (data.getRiderOrderPriceDetail().getTupc() != null) {
                    userSession.setTupcId(data.getRiderOrderPriceDetail().getTupc());
                } else {
                    userSession.setTupcId("");
                }
            }

            if (data.getProductVariantDetail().getProductImgUrl1() != null) {
                userSession.setProductImgUrl(data.getProductVariantDetail().getProductImgUrl1());
                Picasso.get().load(data.getProductVariantDetail().getProductImgUrl1()).error(R.drawable.no_image_available).into(deviceImg, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d("TAG", "onSuccess");
                    }

                    @Override
                    public void onError(Exception exception) {
                        System.out.println("Image Loading Error :- " + exception.getMessage());
                    }
                });
//                Glide.with(mContext).load(data.getProductVariantDetail().getProductImgUrl1()).into(deviceImg);
            }
            if (data.getProductVariantDetail().getProductName() != null) {
                deviceDetails.setText(data.getProductVariantDetail().getProductName());
                userSession.setProductName(data.getProductVariantDetail().getProductName());
            } else {
                deviceDetails.setText("N/A");
            }

            if (data.getUserDetail().getFullName() != null) {
                tradersName.setText(data.getUserDetail().getFullName());
            } else {
                tradersName.setText("N/A");
            }

            if (data.getUserAddressDetail().getDistrictName() != null) {
                location.setText(data.getUserAddressDetail().getDistrictName());
            } else {
                location.setText("N/A");
            }

            if (data.getOrderId() != null) {
                tokenNumber.setText(data.getOrderDetail().getOrderNumber());
                userSession.setOrderNumber(data.getOrderDetail().getOrderNumber());
                userSession.setOrderId(data.getOrderId());
            } else {
                tokenNumber.setText("N/A");
            }

            if (data.getUserDetail().getCreateDate() != null) {
                System.out.println("Created Date :- " + parseDateToddMMyyyy(data.getUserDetail().getCreateDate().substring(0, 10)));
                dateAndTime.setText(parseDateToddMMyyyy(data.getUserDetail().getCreateDate().substring(0, 10)) + ", " + parseTimeToHHMMss(data.getUserDetail().getCreateDate().substring(11, 19)));
            } else {
                System.out.println("Created Date :- " + parseDateToddMMyyyy(data.getUserDetail().getCreateDate().substring(0, 10)));
                dateAndTime.setText("N/A");
            }
            if (data.getUserDetail().getFullName() != null) {
                partnerName.setText(data.getUserDetail().getFullName());
            } else {
                partnerName.setText("N/A");
            }

            if (data.getUserDetail().getMobileNo() != null) {
                partnerPhoneNumber.setText(data.getUserDetail().getMobileNo());
            } else {
                partnerPhoneNumber.setText("N/A");
            }

            if (data.getPickupScheduleDetail().getScheduleTime() != null) {
                pickUpDateTime.setText(parseDateToddMMyyyy(data.getPickupScheduleDetail().getScheduleTime().substring(0, 10)) + ", " + parseTimeToHHMMss(data.getPickupScheduleDetail().getScheduleTime().substring(11, 19)));
            } else {
                pickUpDateTime.setText("N/A");
            }

            if (data.getUserAddressDetail().getFullName() != null) {
                costumerName.setText(data.getUserAddressDetail().getFullName());
            } else {
                costumerName.setText("N/A");
            }

            if (data.getUserAddressDetail().getMobileNo() != null) {
                costumerPhoneNUmber.setText(data.getUserAddressDetail().getMobileNo());
            } else {
                costumerPhoneNUmber.setText("N/A");
            }

            if (data.getUserAddressDetail().getFlatDetail() != null && data.getUserAddressDetail().getAreaDetail() != null && data.getUserAddressDetail().getDistrictName() != null && data.getUserAddressDetail().getStateName() != null && data.getUserAddressDetail().getPincode() != null) {
                costumerAddress.setText(data.getUserAddressDetail().getFlatDetail() + ", " + data.getUserAddressDetail().getAreaDetail() + ", " + data.getUserAddressDetail().getDistrictName() + " " + data.getUserAddressDetail().getStateName() + " " + data.getUserAddressDetail().getPincode());
            } else {
                costumerAddress.setText("N/A");
            }
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    private void setDataInSessionForRiderConfirmation(CompleteOrderDetailData data) {
        try {
            if (data.getQuestionairsByEndUser() != null) {

                JSONObject jsonDeviceOnOff = new JSONObject();
                JSONObject jsonDisplayTouchScreen = new JSONObject();
                JSONObject jsonAccessories = new JSONObject();
                JSONObject jsonFunctionalIssue = new JSONObject();
                JSONObject jsonRepair = new JSONObject();
                JSONObject jsonWarrantyUtilized = new JSONObject();
                JSONObject jsonDeviceBody = new JSONObject();
                JSONObject jsonSilverFrame = new JSONObject();
                JSONObject jsonMainCamera = new JSONObject();

                jsonDeviceOnOff.put("isDeviceOn", data.getQuestionairsByEndUser().getDeviceOnOff().getIsDeviceOn());
                userSession.setDeviceOnOffEndUserJson(jsonDeviceOnOff.toString());
                System.out.println("Device On Off :- " + jsonDeviceOnOff);

                jsonDisplayTouchScreen.put("flawless", data.getQuestionairsByEndUser().getDisplayTouch().getFlawless());
                jsonDisplayTouchScreen.put("minorScratchesTwoOrThree", data.getQuestionairsByEndUser().getDisplayTouch().getMinorScratchesTwoOrThree());
                jsonDisplayTouchScreen.put("shaded_WhiteDots", data.getQuestionairsByEndUser().getDisplayTouch().getShadedWhiteDots());
                jsonDisplayTouchScreen.put("broken_Dead", data.getQuestionairsByEndUser().getDisplayTouch().getBrokenDead());
                userSession.setDisplayTouchEndUserJson(jsonDisplayTouchScreen.toString());
                System.out.println("Display Touch :- " + jsonDisplayTouchScreen);

                jsonAccessories.put("earphone", data.getQuestionairsByEndUser().getAccessories().getEarphone());
                jsonAccessories.put("box_with_same_IMEI", data.getQuestionairsByEndUser().getAccessories().getBoxWithSameIMEI());
                jsonAccessories.put("original_Charger", data.getQuestionairsByEndUser().getAccessories().getOriginalCharger());
                userSession.setAvailableAccessoriesEndUserJson(jsonAccessories.toString());
                System.out.println("Available Accessories :- " + jsonAccessories);

                jsonFunctionalIssue.put("volume_Button", data.getQuestionairsByEndUser().getFunctionalIssue().getVolumeButton());
                jsonFunctionalIssue.put("power_Home_Button", data.getQuestionairsByEndUser().getFunctionalIssue().getPowerHomeButton());
                jsonFunctionalIssue.put("wifi_Bluetooth_GPS", data.getQuestionairsByEndUser().getFunctionalIssue().getWifiBluetoothGPS());
                jsonFunctionalIssue.put("charging_Defect", data.getQuestionairsByEndUser().getFunctionalIssue().getChargingDefect());
                jsonFunctionalIssue.put("battery_Faulty", data.getQuestionairsByEndUser().getFunctionalIssue().getBatteryFaulty());
                jsonFunctionalIssue.put("speakers_Faulty", data.getQuestionairsByEndUser().getFunctionalIssue().getSpeakersFaulty());
                jsonFunctionalIssue.put("microphone_Faulty", data.getQuestionairsByEndUser().getFunctionalIssue().getMicrophoneFaulty());
                jsonFunctionalIssue.put("gsM_Faulty", data.getQuestionairsByEndUser().getFunctionalIssue().getGsMFaulty());
                jsonFunctionalIssue.put("earphone_Jack_Faulty", data.getQuestionairsByEndUser().getFunctionalIssue().getEarphoneJackFaulty());
                jsonFunctionalIssue.put("fingerprint_Sensor_Faulty", data.getQuestionairsByEndUser().getFunctionalIssue().getFingerprintSensorFaulty());
                jsonFunctionalIssue.put("camera_Faulty", data.getQuestionairsByEndUser().getFunctionalIssue().getCameraFaulty());
                userSession.setFunctionalIssueEndUserJson(jsonFunctionalIssue.toString());
                System.out.println("Functional Issue :- " + jsonFunctionalIssue);

                jsonRepair.put("undergone_repairs", data.getQuestionairsByEndUser().getRepair().getUndergoneRepairs());
                userSession.setRepairDetailsEndUserJson(jsonRepair.toString());
                System.out.println("Repair Details :- " + jsonRepair);

                jsonWarrantyUtilized.put("warranty_Utilized_Month", data.getQuestionairsByEndUser().getWarrantyUtilized().getWarrantyUtilizedMonth());
                jsonWarrantyUtilized.put("lessThanThreeMonths", data.getQuestionairsByEndUser().getWarrantyUtilized().getLessThanThreeMonths());
                jsonWarrantyUtilized.put("lessThanTenMonths", data.getQuestionairsByEndUser().getWarrantyUtilized().getLessThanTenMonths());
                jsonWarrantyUtilized.put("moreThanTenMonths", data.getQuestionairsByEndUser().getWarrantyUtilized().getMoreThanTenMonths());
                jsonWarrantyUtilized.put("notAvailable", data.getQuestionairsByEndUser().getWarrantyUtilized().getNotAvailable());
                userSession.setBrandWarrantyUtilizedEndUserJson(jsonWarrantyUtilized.toString());
                System.out.println("Warranty Utilized :- " + jsonWarrantyUtilized);

                jsonDeviceBody.put("flawless", data.getQuestionairsByEndUser().getDeviceBody().getFlawless());
                jsonDeviceBody.put("scratched", data.getQuestionairsByEndUser().getDeviceBody().getScratched());
                jsonDeviceBody.put("broken", data.getQuestionairsByEndUser().getDeviceBody().getBroken());
                jsonDeviceBody.put("loose", data.getQuestionairsByEndUser().getDeviceBody().getLoose());
                jsonDeviceBody.put("missing", data.getQuestionairsByEndUser().getDeviceBody().getMissing());
                userSession.setBodyInformationEndUserJson(jsonDeviceBody.toString());
                System.out.println("Body Information :- " + jsonDeviceBody);

                jsonSilverFrame.put("okay", data.getQuestionairsByEndUser().getSilverFrame().getOkay());
                jsonSilverFrame.put("discolored", data.getQuestionairsByEndUser().getSilverFrame().getDiscolored());
                jsonSilverFrame.put("dented", data.getQuestionairsByEndUser().getSilverFrame().getDented());
                jsonSilverFrame.put("broken", data.getQuestionairsByEndUser().getSilverFrame().getBroken());
                userSession.setSilverFrameBezelEndUserJson(jsonSilverFrame.toString());
                System.out.println("Silver Frame Bezel :- " + jsonSilverFrame);

                jsonMainCamera.put("okayFlawless", data.getQuestionairsByEndUser().getMainCamera().getOkayFlawless());
                jsonMainCamera.put("scratched", data.getQuestionairsByEndUser().getMainCamera().getScratched());
                jsonMainCamera.put("blur", data.getQuestionairsByEndUser().getMainCamera().getBlur());
                jsonMainCamera.put("cracked", data.getQuestionairsByEndUser().getMainCamera().getCracked());
                jsonMainCamera.put("broken", data.getQuestionairsByEndUser().getMainCamera().getBroken());
                userSession.setMainCameraEndUserJson(jsonMainCamera.toString());
                System.out.println("Main Camera :- " + jsonMainCamera);
            } else {
                Toast.makeText(mContext, "Not available any questionairs by end user!", Toast.LENGTH_SHORT).show();
            }
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

    private void getDeviceLeadDetailsLayoutUiIdFind() {
        try {
            backDeviceLeadDetailsImg = (ImageView) findViewById(R.id.backDeviceLeadDetailsImg);
            deviceImg = (ImageView) findViewById(R.id.deviceImg);
            startDiagnoseBtn = (AppCompatButton) findViewById(R.id.startDiagnoseBtn);
            deviceDetails = (TextView) findViewById(R.id.deviceDetails);
            tradersName = (TextView) findViewById(R.id.tradersName);
            location = (TextView) findViewById(R.id.location);
            tokenNumber = (TextView) findViewById(R.id.tokenNumber);
            dateAndTime = (TextView) findViewById(R.id.dateAndTime);
            deviceImeiNumber = (TextView) findViewById(R.id.deviceImeiNumber);
            partnerName = (TextView) findViewById(R.id.partnerName);
            partnerPhoneNumber = (TextView) findViewById(R.id.partnerPhoneNumber);
            pickUpDateTime = (TextView) findViewById(R.id.pickUpDateTime);
            costumerName = (TextView) findViewById(R.id.costumerName);
            costumerPhoneNUmber = (TextView) findViewById(R.id.costumerPhoneNUmber);
            devicePrice = (TextView) findViewById(R.id.devicePrice);
            costumerAddress = (TextView) findViewById(R.id.costumerAddress);
            leadTxt = (TextView) findViewById(R.id.leadTxt);
            showOrderDetails = (LinearLayout) findViewById(R.id.showOrderDetails);

        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backDeviceLeadDetailsImg:
                onBackPressed();
                break;

            case R.id.startDiagnoseBtn:
                if (userSession.getPageDirectionStatus().equalsIgnoreCase("Open Leads")) {
                    getOrderConfirmForPickup(userSession.getOrderId(), pickupScheduleId);
                } else {
                    startActivity(new Intent(mContext, DeviceConditionDetailsActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                }
                break;

            case R.id.showOrderDetails:
                break;
        }
    }

    private void getOrderConfirmForPickup(String orderId, int pickupScheduleId) {
        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("pickupScheduleId", pickupScheduleId);
        jsonObject.addProperty("orderId", orderId);
        jsonObject.addProperty("riderId", userSession.getUserId());

        System.out.println("Confirm pickup :- " + jsonObject.toString());
        try {
            ApiCallService apiClient = ApiNetworkClient.getSncRiderRetrofit().create(ApiCallService.class);
            Call<CompleteOrderDetailResponse> completeOrderDetailResponseCall = apiClient.getConfirmedSchedulePickup(jsonObject);
            completeOrderDetailResponseCall.enqueue(new retrofit2.Callback<CompleteOrderDetailResponse>() {
                @Override
                public void onResponse(Call<CompleteOrderDetailResponse> call, Response<CompleteOrderDetailResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatusCode() == 200) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            if (response.body().getData() != null) {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                getAlertDialogForSuccess(userSession.getOrderNumber());
                            } else {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                Toast.makeText(mContext, "Please try again after some time!", Toast.LENGTH_SHORT).show();
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
                public void onFailure(Call<CompleteOrderDetailResponse> call, Throwable t) {
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

    private void getAlertDialogForSuccess(String orderId) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setMessage("You have confirmed order for pickup device!" + "\nOrder Number :- " + orderId);
            builder.setTitle("Order Confirm");
            builder.setCancelable(false);
            builder.setPositiveButton("Okay", (DialogInterface.OnClickListener) (dialog, which) -> {
                startActivity(new Intent(mContext, DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                dialog.cancel();
                finish();
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}