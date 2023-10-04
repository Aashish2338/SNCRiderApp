package com.amsys.sncriderapp.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.FileProvider;

import com.amsys.sncriderapp.ApiCallService;
import com.amsys.sncriderapp.R;
import com.amsys.sncriderapp.ResponseModel.ConfirmOrderResponse;
import com.amsys.sncriderapp.ResponseModel.UploadDeviceImagesResponse;
import com.amsys.sncriderapp.Utilities.ApiNetworkClient;
import com.amsys.sncriderapp.Utilities.NetworkStatus;
import com.amsys.sncriderapp.Utilities.UserSession;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class SeeDeviceDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private UserSession userSession;
    private ImageView backSeeImg, reportImg, deviceImageImg, imeiSerialNumberImg, aadharImg, declarationImg;
    private AppCompatButton cancelBtn, getPaidBtn, editAmountBtn, imeiSerialImgBtn, aadharImgBtn, declarationImgBtn;
    private TextView productNameDevice, deviceExactPrice, newExactPrice, deviceCore, rearCamera, storage, chipset;
    private RelativeLayout mainLayout;
    private String rejectionConfirmationRemarks = "", orderId = "", newChangePrice = "";
    private JSONObject jsonMain = null;
    private JsonObject gsonObject = null;
    private File imeiSerialImageInBase64 = null, aadharImageInBase64 = null, declarationImageInBase64 = null;
    private int exactOldNewPrice = 0;
    private File first = null, second = null, third = null, fourth = null;
    private ProgressDialog progressDialog = null;
    private String serialPhotoPath;
    private Uri photoURI = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_device_details);
        mContext = this;
        userSession = new UserSession(mContext);

        getSeeDeviceLayoutUiIdFind();

        backSeeImg.setOnClickListener(this);
        reportImg.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        getPaidBtn.setOnClickListener(this);
        editAmountBtn.setOnClickListener(this);
        imeiSerialImgBtn.setOnClickListener(this);
        aadharImgBtn.setOnClickListener(this);
        declarationImgBtn.setOnClickListener(this);

        orderId = userSession.getOrderId();
        if (exactOldNewPrice == 0) {
            exactOldNewPrice = userSession.getExactPrice();
            double amount = Double.parseDouble(String.valueOf(exactOldNewPrice));
            DecimalFormat formatter = new DecimalFormat("#,###.00");
            System.out.println("Converted Amount price:- " + formatter.format(amount));
            deviceExactPrice.setText("₹" + formatter.format(amount));
        }
        productNameDevice.setText(userSession.getProductName());
        Picasso.get().load(userSession.getProductImgUrl()).error(R.drawable.no_image_available).into(deviceImageImg, new Callback() {
            @Override
            public void onSuccess() {
                Log.d("TAG", "onSuccess");
            }

            @Override
            public void onError(Exception exception) {
                System.out.println("Image Loading Error :- " + exception.getMessage());
            }
        });
    }

    private void getSeeDeviceLayoutUiIdFind() {
        try {
            mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
            backSeeImg = (ImageView) findViewById(R.id.backSeeImg);
            reportImg = (ImageView) findViewById(R.id.reportImg);
            deviceImageImg = (ImageView) findViewById(R.id.deviceImageImg);
            imeiSerialNumberImg = (ImageView) findViewById(R.id.imeiSerialNumberImg);
            aadharImg = (ImageView) findViewById(R.id.aadharImg);
            declarationImg = (ImageView) findViewById(R.id.declarationImg);
            cancelBtn = (AppCompatButton) findViewById(R.id.cancelBtn);
            getPaidBtn = (AppCompatButton) findViewById(R.id.getPaidBtn);
            editAmountBtn = (AppCompatButton) findViewById(R.id.editAmountBtn);
            imeiSerialImgBtn = (AppCompatButton) findViewById(R.id.imeiSerialImgBtn);
            aadharImgBtn = (AppCompatButton) findViewById(R.id.aadharImgBtn);
            declarationImgBtn = (AppCompatButton) findViewById(R.id.declarationImgBtn);
            productNameDevice = (TextView) findViewById(R.id.productNameDevice);
            deviceExactPrice = (TextView) findViewById(R.id.deviceExactPrice);
            newExactPrice = (TextView) findViewById(R.id.newExactPrice);
            deviceCore = (TextView) findViewById(R.id.deviceCore);
            rearCamera = (TextView) findViewById(R.id.rearCamera);
            storage = (TextView) findViewById(R.id.storage);
            chipset = (TextView) findViewById(R.id.chipset);

            userSession.setSingleClick("");
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backSeeImg:
                onBackPressed();
                break;

            case R.id.editAmountBtn:
                setChangeNewPriceOfDevice();
                break;

            case R.id.reportImg:
                getDisplayDiagnoseReportBottomSheet();
                break;

            case R.id.imeiSerialImgBtn:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    Intent takePictureIntent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                        if (photoFile != null) {
                            photoURI = FileProvider.getUriForFile(mContext, "com.amsys.sncriderapp.FileProvider", photoFile);
                            takePictureIntent1.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                            launchSerialActivity.launch(takePictureIntent1);
                        }
                    } catch (IOException ex) {
                        Log.d("Tag", ex.toString());
                    }
                } else {
                    Intent cameraSerialIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    launchSerialActivity.launch(cameraSerialIntent);
                }
                break;

            case R.id.aadharImgBtn:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    Intent takePictureIntent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                        if (photoFile != null) {
                            photoURI = FileProvider.getUriForFile(mContext, "com.amsys.sncriderapp.FileProvider", photoFile);
                            takePictureIntent2.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                            launchAadharActivity.launch(takePictureIntent2);
                        }
                    } catch (IOException ex) {
                        Log.d("Tag", ex.toString());
                    }
                } else {
                    Intent cameraAadharIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    launchAadharActivity.launch(cameraAadharIntent);
                }
                break;

            case R.id.declarationImgBtn:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    Intent takePictureIntent3 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                        if (photoFile != null) {
                            photoURI = FileProvider.getUriForFile(mContext, "com.amsys.sncriderapp.FileProvider", photoFile);
                            takePictureIntent3.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                            launchDeclarationActivity.launch(takePictureIntent3);
                        }
                    } catch (IOException ex) {
                        Log.d("Tag", ex.toString());
                    }
                } else {
                    Intent cameraDeclarationIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    launchDeclarationActivity.launch(cameraDeclarationIntent);
                }
                break;

            case R.id.cancelBtn:
                getOrderRejectForPickup();
                break;

            case R.id.getPaidBtn:
                if (imeiSerialImageInBase64 == null) {
                    Toast.makeText(mContext, "Please capture mobile box with you!", Toast.LENGTH_SHORT).show();
                } else if (aadharImageInBase64 == null) {
                    Toast.makeText(mContext, "Please capture your Aadhar card!", Toast.LENGTH_SHORT).show();
                } else if (declarationImageInBase64 == null) {
                    Toast.makeText(mContext, "Please capture your declaration form!", Toast.LENGTH_SHORT).show();
                } else {
                    getOrderConfirmForPickup();
                }
                break;
        }
    }

    private void setChangeNewPriceOfDevice() {
        try {
            Dialog dialog = new Dialog(mContext);
            dialog.setContentView(R.layout.change_amout_price_layout);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.setCanceledOnTouchOutside(true);
            dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

            TextView exactConfirmNumber = (TextView) dialog.findViewById(R.id.exactConfirmNumber);
            EditText remarkForConfirmEt = (EditText) dialog.findViewById(R.id.remarkForConfirmEt);
            AppCompatButton saveBtn = (AppCompatButton) dialog.findViewById(R.id.saveBtn);

            exactConfirmNumber.setText("₹" + userSession.getExactPrice());
            saveBtn.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    newChangePrice = remarkForConfirmEt.getText().toString().trim();
                    if (!newChangePrice.isEmpty()) {
                        userSession.setSingleClick("Single Click");
                        exactOldNewPrice = Integer.parseInt(newChangePrice);
                        newExactPrice.setVisibility(View.VISIBLE);
                        editAmountBtn.setVisibility(View.GONE);
                        double amount = Double.parseDouble(String.valueOf(exactOldNewPrice));
                        DecimalFormat formatter = new DecimalFormat("#,###.00");
                        System.out.println("Converted Amount price:- " + formatter.format(amount));

                        newExactPrice.setText("Final Price ₹" + formatter.format(amount));
                        deviceExactPrice.setTextColor(R.color.textcolor);
                        dialog.dismiss();
                    } else {
                        Toast.makeText(mContext, "Enter your new price!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            dialog.show();
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    private void getOrderRejectForPickup() {
        try {
            Dialog dialog = new Dialog(mContext);
            dialog.setContentView(R.layout.order_rejection_report_layout);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.setCanceledOnTouchOutside(true);
            dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

            TextView orderIdNumber = (TextView) dialog.findViewById(R.id.orderIdNumber);
            EditText remarkForRejectEt = (EditText) dialog.findViewById(R.id.remarkForRejectEt);
            AppCompatButton getRejectBtn = (AppCompatButton) dialog.findViewById(R.id.getRejectBtn);

            orderIdNumber.setText(userSession.getOrderId());

            getRejectBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rejectionConfirmationRemarks = remarkForRejectEt.getText().toString().trim();
                    if (!rejectionConfirmationRemarks.isEmpty()) {
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("orderId", userSession.getOrderId());
                        jsonObject.addProperty("userId", orderId);
                        jsonObject.addProperty("statusId", userSession.getOrderStatusId());
                        jsonObject.addProperty("remark", rejectionConfirmationRemarks);
                        getRejectOrderForPickupRider(jsonObject);
                        dialog.dismiss();
                    } else {
                        Toast.makeText(mContext, "Please enter your rejection remarks!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            dialog.show();
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    private void getRejectOrderForPickupRider(JsonObject gsonObject) {
        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        try {
            ApiCallService apiClient = ApiNetworkClient.getSncRiderRetrofit().create(ApiCallService.class);
            Call<ConfirmOrderResponse> confirmOrderResponseCall = apiClient.getRejectOrder(gsonObject);
            confirmOrderResponseCall.enqueue(new retrofit2.Callback<ConfirmOrderResponse>() {
                @Override
                public void onResponse(Call<ConfirmOrderResponse> call, Response<ConfirmOrderResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatusCode() == 200) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            if (response.body().getData() != null) {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                System.out.println("Your Reject Order Number :- " + response.body().getData().getOrderId());
                                getAlertDialogForSuccess("You have done reject your order!", userSession.getOrderNumber());
                            } else {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                Toast.makeText(mContext, "Your order is not rejected!", Toast.LENGTH_SHORT).show();
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
                public void onFailure(Call<ConfirmOrderResponse> call, Throwable t) {
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

    private void getOrderConfirmForPickup() {
        try {
            Dialog dialog = new Dialog(mContext);
            dialog.setContentView(R.layout.confirm_order_pickup_dialog_layout);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.setCanceledOnTouchOutside(true);
            dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

            TextView orderIdConfirmNumber = (TextView) dialog.findViewById(R.id.orderIdConfirmNumber);
            EditText remarkForConfirmEt = (EditText) dialog.findViewById(R.id.remarkForConfirmEt);
            AppCompatButton getConfirmBtn = (AppCompatButton) dialog.findViewById(R.id.getConfirmBtn);

            orderIdConfirmNumber.setText(userSession.getOrderNumber());
            getConfirmBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rejectionConfirmationRemarks = remarkForConfirmEt.getText().toString().trim();
                    if (!rejectionConfirmationRemarks.isEmpty()) {
                        getConfirmationJsonReports(exactOldNewPrice, orderId, rejectionConfirmationRemarks);
                        dialog.dismiss();
                    } else {
                        Toast.makeText(mContext, "Enter your confirmation remarks!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            dialog.show();
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    private void getConfirmationJsonReports(int newPrice, String orderId, String remarks) {
        try {
            jsonMain = new JSONObject();
            JSONObject orderCreationRequest = new JSONObject();
            JSONObject jsonQuestion = new JSONObject();
            JSONObject jsonDeviceOnOff = new JSONObject(userSession.getDeviceOnOffJson());
            JSONObject jsonDisplayTouch = new JSONObject(userSession.getDisplayTouchJson());
            JSONObject jsonAccessories = new JSONObject(userSession.getAvailableAccessoriesJson());
            JSONObject jsonFunctionalIssue = new JSONObject(userSession.getFunctionalIssueJson());
            JSONObject jsonRepair = new JSONObject(userSession.getRepairDetailsJson());
            JSONObject jsonWarrantyUtilized = new JSONObject(userSession.getBrandWarrantyUtilizedJson());
            JSONObject jsonDeviceBody = new JSONObject(userSession.getBodyInformationJson());
            JSONObject jsonSilverFrame = new JSONObject(userSession.getSilverFrameBezelJson());
            JSONObject jsonMainCamera = new JSONObject(userSession.getMainCameraJson());

            jsonMain.put("userId", userSession.getUserId());
            jsonMain.put("addressId", userSession.getAddressId());
            jsonMain.put("userType", userSession.getUserType());
            jsonMain.put("exactPrice", newPrice);
            jsonMain.put("orderId", orderId);
            jsonMain.put("remark", remarks);

            System.out.println("OrderId :- " + orderId);

            jsonMain.put("orderCreationRequest", orderCreationRequest);
            orderCreationRequest.put("tupc", userSession.getTupcId());
            orderCreationRequest.put("ageing", userSession.getAgeingId());
            orderCreationRequest.put("questionairs", jsonQuestion);

            jsonQuestion.put("deviceOnOff", jsonDeviceOnOff);
            jsonQuestion.put("displayTouch", jsonDisplayTouch);
            jsonQuestion.put("accessories", jsonAccessories);
            jsonQuestion.put("functionalIssue", jsonFunctionalIssue);
            jsonQuestion.put("repair", jsonRepair);
            jsonQuestion.put("warranty_Utilized", jsonWarrantyUtilized);
            jsonQuestion.put("device_Body", jsonDeviceBody);
            jsonQuestion.put("silver_Frame", jsonSilverFrame);
            jsonQuestion.put("main_Camera", jsonMainCamera);

            JSONObject object = jsonMain;
            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(object.toString());
            System.out.println("API Request json :- " + gsonObject);
//            getConfirmOrderForPickupRider(gsonObject);
            if (NetworkStatus.isNetworkAvailable(mContext)) {
                first = new File(userSession.getFirstImagePath());
                second = new File(userSession.getSecondImagePath());
                third = new File(userSession.getThirdImagePath());
                fourth = new File(userSession.getFourthImagePath());
                System.out.println("First image :- " + first.getAbsoluteFile());
                System.out.println("Second image :- " + second.getAbsoluteFile());
                System.out.println("Third image :- " + third.getAbsoluteFile());
                System.out.println("Fourth image :- " + fourth.getAbsoluteFile());

                getUploadImages(userSession.getCurrentAddress(), orderId, imeiSerialImageInBase64, aadharImageInBase64, declarationImageInBase64, first.getAbsoluteFile(), second.getAbsoluteFile(), third.getAbsoluteFile(), fourth.getAbsoluteFile());
            } else {
                Toast.makeText(mContext, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    private void getAlertDialogForSuccess(String sms, String orderId) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setMessage(sms + "\nOrder Number :- " + orderId);
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

    private void getDisplayDiagnoseReportBottomSheet() {
        try {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mContext, R.style.BottomSheetDialogTheme);
            bottomSheetDialog.setContentView(R.layout.bottom_dialog_layout);
            bottomSheetDialog.setCancelable(false);
            bottomSheetDialog.setCanceledOnTouchOutside(false);
            bottomSheetDialog.show();

            ImageView closeAlertImg = bottomSheetDialog.findViewById(R.id.closeAlertImg);
            ImageView deviceOnImg = bottomSheetDialog.findViewById(R.id.deviceOnImg);
            ImageView deviceOffImg = bottomSheetDialog.findViewById(R.id.deviceOffImg);
            ImageView repairYesImg = bottomSheetDialog.findViewById(R.id.repairYesImg);
            ImageView repairNoImg = bottomSheetDialog.findViewById(R.id.repairNoImg);
            ImageView warrantyFirstImg = bottomSheetDialog.findViewById(R.id.warrantyFirstImg);
            ImageView warrantySecondImg = bottomSheetDialog.findViewById(R.id.warrantySecondImg);
            ImageView warrantyThirdImg = bottomSheetDialog.findViewById(R.id.warrantyThirdImg);
            ImageView warrantyFourthImg = bottomSheetDialog.findViewById(R.id.warrantyFourthImg);
            ImageView displayFlowlessImg = bottomSheetDialog.findViewById(R.id.displayFlowlessImg);
            ImageView displayScratchImg = bottomSheetDialog.findViewById(R.id.displayScratchImg);
            ImageView displayDotsImg = bottomSheetDialog.findViewById(R.id.displayDotsImg);
            ImageView displayNotWorkImg = bottomSheetDialog.findViewById(R.id.displayNotWorkImg);
            ImageView accessoriesEarphoneImg = bottomSheetDialog.findViewById(R.id.accessoriesEarphoneImg);
            ImageView accessoriesImeiImg = bottomSheetDialog.findViewById(R.id.accessoriesImeiImg);
            ImageView accessoriesChargerImg = bottomSheetDialog.findViewById(R.id.accessoriesChargerImg);
            ImageView issueValumeImg = bottomSheetDialog.findViewById(R.id.issueValumeImg);
            ImageView issueHomeImg = bottomSheetDialog.findViewById(R.id.issueHomeImg);
            ImageView issueWifiImg = bottomSheetDialog.findViewById(R.id.issueWifiImg);
            ImageView issueChargingImg = bottomSheetDialog.findViewById(R.id.issueChargingImg);
            ImageView issueBattaryImg = bottomSheetDialog.findViewById(R.id.issueBattaryImg);
            ImageView issueSpeakerImg = bottomSheetDialog.findViewById(R.id.issueSpeakerImg);
            ImageView issueMicrophoneImg = bottomSheetDialog.findViewById(R.id.issueMicrophoneImg);
            ImageView issueGsmImg = bottomSheetDialog.findViewById(R.id.issueGsmImg);
            ImageView issueEarphoneImg = bottomSheetDialog.findViewById(R.id.issueEarphoneImg);
            ImageView issueFingerPrintImg = bottomSheetDialog.findViewById(R.id.issueFingerPrintImg);
            ImageView bodyFlowlessImg = bottomSheetDialog.findViewById(R.id.bodyFlowlessImg);
            ImageView bodyScratchImg = bottomSheetDialog.findViewById(R.id.bodyScratchImg);
            ImageView bodyBrokenImg = bottomSheetDialog.findViewById(R.id.bodyBrokenImg);
            ImageView bodyLooseImg = bottomSheetDialog.findViewById(R.id.bodyLooseImg);
            ImageView bodyMissingImg = bottomSheetDialog.findViewById(R.id.bodyMissingImg);
            ImageView silverOkayImg = bottomSheetDialog.findViewById(R.id.silverOkayImg);
            ImageView silverDiscoloredImg = bottomSheetDialog.findViewById(R.id.silverDiscoloredImg);
            ImageView silverDentedImg = bottomSheetDialog.findViewById(R.id.silverDentedImg);
            ImageView silverBrokenImg = bottomSheetDialog.findViewById(R.id.silverBrokenImg);
            ImageView cameraOkayImg = bottomSheetDialog.findViewById(R.id.cameraOkayImg);
            ImageView cameraScratchedImg = bottomSheetDialog.findViewById(R.id.cameraScratchedImg);
            ImageView cameraBlurImg = bottomSheetDialog.findViewById(R.id.cameraBlurImg);
            ImageView cameraBrokenImg = bottomSheetDialog.findViewById(R.id.cameraBrokenImg);
            ImageView cameraCrackedImg = bottomSheetDialog.findViewById(R.id.cameraCrackedImg);

            closeAlertImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });

            if (!userSession.getDeviceConditionYesNo().equalsIgnoreCase("")) {
                deviceOnImg.setImageResource(R.drawable.check_icon);
                deviceOffImg.setImageResource(R.drawable.close_icon);
            } else {
                deviceOffImg.setImageResource(R.drawable.check_icon);
                deviceOnImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getRepairDetailsYesNo().equalsIgnoreCase("")) {
                repairYesImg.setImageResource(R.drawable.check_icon);
                repairNoImg.setImageResource(R.drawable.close_icon);
            } else {
                repairNoImg.setImageResource(R.drawable.check_icon);
                repairYesImg.setImageResource(R.drawable.close_icon);
            }

            if (userSession.getDeviceHasUndergoneRepairsMonths().equalsIgnoreCase("0 to 3 months")) {
                warrantyFirstImg.setImageResource(R.drawable.check_icon);
                warrantySecondImg.setImageResource(R.drawable.close_icon);
                warrantyThirdImg.setImageResource(R.drawable.close_icon);
                warrantyFourthImg.setImageResource(R.drawable.close_icon);
            } else if (userSession.getDeviceHasUndergoneRepairsMonths().equalsIgnoreCase("3 to 10 months")) {
                warrantyFirstImg.setImageResource(R.drawable.close_icon);
                warrantySecondImg.setImageResource(R.drawable.check_icon);
                warrantyThirdImg.setImageResource(R.drawable.close_icon);
                warrantyFourthImg.setImageResource(R.drawable.close_icon);
            } else if (userSession.getDeviceHasUndergoneRepairsMonths().equalsIgnoreCase("More than 10 Months")) {
                warrantyFirstImg.setImageResource(R.drawable.close_icon);
                warrantySecondImg.setImageResource(R.drawable.close_icon);
                warrantyThirdImg.setImageResource(R.drawable.close_icon);
                warrantyFourthImg.setImageResource(R.drawable.check_icon);
            } else {
                warrantyFirstImg.setImageResource(R.drawable.close_icon);
                warrantySecondImg.setImageResource(R.drawable.close_icon);
                warrantyThirdImg.setImageResource(R.drawable.check_icon);
                warrantyFourthImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getFlawlessValueOfDisplayAndTouchScreen().equalsIgnoreCase("")) {
                displayFlowlessImg.setImageResource(R.drawable.check_icon);
            } else {
                displayFlowlessImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getMinorScratchesOfDisplayAndTouchScreen().equalsIgnoreCase("")) {
                displayScratchImg.setImageResource(R.drawable.check_icon);
            } else {
                displayScratchImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getShadedWhiteDotsOfDisplayAndTouchScreen().equalsIgnoreCase("")) {
                displayDotsImg.setImageResource(R.drawable.check_icon);
            } else {
                displayDotsImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getBrokenDeadPixelLiquidOfDisplayAndTouchScreen().equalsIgnoreCase("")) {
                displayNotWorkImg.setImageResource(R.drawable.check_icon);
            } else {
                displayNotWorkImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getEarphoneOfAccessoriesDetails().equalsIgnoreCase("")) {
                accessoriesEarphoneImg.setImageResource(R.drawable.check_icon);
            } else {
                accessoriesEarphoneImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getBoxWithSameImeiOfAccessoriesDetails().equalsIgnoreCase("")) {
                accessoriesImeiImg.setImageResource(R.drawable.check_icon);
            } else {
                accessoriesImeiImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getOriginalChargerOfAccessoriesDetails().equalsIgnoreCase("")) {
                accessoriesChargerImg.setImageResource(R.drawable.check_icon);
            } else {
                accessoriesChargerImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getVolumeNotWorkingOfIssueDetails().equalsIgnoreCase("")) {
                issueValumeImg.setImageResource(R.drawable.check_icon);
            } else {
                issueValumeImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getPowerHomeButtonFaultyOfIssueDetails().equalsIgnoreCase("")) {
                issueHomeImg.setImageResource(R.drawable.check_icon);
            } else {
                issueHomeImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getWifiBlueToothGPSOfIssueDetails().equalsIgnoreCase("")) {
                issueWifiImg.setImageResource(R.drawable.check_icon);
            } else {
                issueWifiImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getChargingDefectOfIssueDetails().equalsIgnoreCase("")) {
                issueChargingImg.setImageResource(R.drawable.check_icon);
            } else {
                issueChargingImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getBatteryFaultyLowOfIssueDetails().equalsIgnoreCase("")) {
                issueBattaryImg.setImageResource(R.drawable.check_icon);
            } else {
                issueBattaryImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getSpeakerNotWorkingOfIssueDetails().equalsIgnoreCase("")) {
                issueSpeakerImg.setImageResource(R.drawable.check_icon);
            } else {
                issueSpeakerImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getMicrophoneNotWorkingOfIssueDetails().equalsIgnoreCase("")) {
                issueMicrophoneImg.setImageResource(R.drawable.check_icon);
            } else {
                issueMicrophoneImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getGSMCallFunctionNotWorkingOfIssueDetails().equalsIgnoreCase("")) {
                issueGsmImg.setImageResource(R.drawable.check_icon);
            } else {
                issueGsmImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getEarphoneJackNotWorkingOfIssueDetails().equalsIgnoreCase("")) {
                issueEarphoneImg.setImageResource(R.drawable.check_icon);
            } else {
                issueEarphoneImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getFingerprintSensorNotWorkingOfIssueDetails().equalsIgnoreCase("")) {
                issueFingerPrintImg.setImageResource(R.drawable.check_icon);
            } else {
                issueFingerPrintImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getFlawlessOfDeviceBodyDetails().equalsIgnoreCase("")) {
                bodyFlowlessImg.setImageResource(R.drawable.check_icon);
            } else {
                bodyFlowlessImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getScratchedOfDeviceBodyDetails().equalsIgnoreCase("")) {
                bodyScratchImg.setImageResource(R.drawable.check_icon);
            } else {
                bodyScratchImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getCrackedOfDeviceBodyDetails().equalsIgnoreCase("")) {

            } else {

            }

            if (!userSession.getBrokenOfDeviceBodyDetails().equalsIgnoreCase("")) {
                bodyBrokenImg.setImageResource(R.drawable.check_icon);
            } else {
                bodyBrokenImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getOkOfDeviceBodyDetails().equalsIgnoreCase("")) {

            } else {

            }

            if (!userSession.getLooseOfDeviceBodyDetails().equalsIgnoreCase("")) {
                bodyLooseImg.setImageResource(R.drawable.check_icon);
            } else {
                bodyLooseImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getMissingOfDeviceBodyDetails().equalsIgnoreCase("")) {
                bodyMissingImg.setImageResource(R.drawable.check_icon);
            } else {
                bodyMissingImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getOkOfBezel().equalsIgnoreCase("")) {
                silverOkayImg.setImageResource(R.drawable.check_icon);
            } else {
                silverOkayImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getDiscoloredOfBezel().equalsIgnoreCase("")) {
                silverDiscoloredImg.setImageResource(R.drawable.check_icon);
            } else {
                silverDiscoloredImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getDentedOfBezel().equalsIgnoreCase("")) {
                silverDentedImg.setImageResource(R.drawable.check_icon);
            } else {
                silverDentedImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getBrokenOfBezel().equalsIgnoreCase("")) {
                silverBrokenImg.setImageResource(R.drawable.check_icon);
            } else {
                silverBrokenImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getOkOfCamera().equalsIgnoreCase("")) {
                cameraOkayImg.setImageResource(R.drawable.check_icon);
            } else {
                cameraOkayImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getScratchedOfCamera().equalsIgnoreCase("")) {
                cameraScratchedImg.setImageResource(R.drawable.check_icon);
            } else {
                cameraScratchedImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getBlurOfCamera().equalsIgnoreCase("")) {
                cameraBlurImg.setImageResource(R.drawable.check_icon);
            } else {
                cameraBlurImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getCrackedOfCamera().equalsIgnoreCase("")) {
                cameraCrackedImg.setImageResource(R.drawable.check_icon);
            } else {
                cameraCrackedImg.setImageResource(R.drawable.close_icon);
            }

            if (!userSession.getBrokenOfCamera().equalsIgnoreCase("")) {
                cameraBrokenImg.setImageResource(R.drawable.check_icon);
            } else {
                cameraBrokenImg.setImageResource(R.drawable.close_icon);
            }
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    private void getUploadImages(String currentAddress, String orderId, File imeiSerialImageInBase64, File aadharImageInBase64, File declarationImageInBase64, File first, File second, File third, File fourth) {
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        try {

            RequestBody currentAddressR = RequestBody.create(MediaType.parse("text/plain"), currentAddress);
            RequestBody orderIdR = RequestBody.create(MediaType.parse("text/plain"), orderId);

            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), imeiSerialImageInBase64);
            MultipartBody.Part imeiSerialFile = MultipartBody.Part.createFormData("Image1", imeiSerialImageInBase64.getName(), requestBody);

            RequestBody requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), aadharImageInBase64);
            MultipartBody.Part aadharImageFile = MultipartBody.Part.createFormData("Image2", aadharImageInBase64.getName(), requestBody1);

            RequestBody requestBody2 = RequestBody.create(MediaType.parse("multipart/form-data"), declarationImageInBase64);
            MultipartBody.Part declarationImageFile = MultipartBody.Part.createFormData("Image3", declarationImageInBase64.getName(), requestBody2);

            RequestBody requestBody3 = RequestBody.create(MediaType.parse("multipart/form-data"), first);
            MultipartBody.Part displayTouchScreen = MultipartBody.Part.createFormData("DisplayTouchScreen", first.getName(), requestBody3);

            RequestBody requestBody4 = RequestBody.create(MediaType.parse("multipart/form-data"), second);
            MultipartBody.Part deviceBodyBackPanel = MultipartBody.Part.createFormData("DeviceBodyBackPanel", second.getName(), requestBody4);

            RequestBody requestBody5 = RequestBody.create(MediaType.parse("multipart/form-data"), third);
            MultipartBody.Part silverFrame = MultipartBody.Part.createFormData("SilverFrame", third.getName(), requestBody5);

            RequestBody requestBody6 = RequestBody.create(MediaType.parse("multipart/form-data"), fourth);
            MultipartBody.Part mainCamera = MultipartBody.Part.createFormData("SilverFrame", fourth.getName(), requestBody6);

            ApiCallService apiCallService = ApiNetworkClient.getSncRiderRetrofit().create(ApiCallService.class);
            Call<UploadDeviceImagesResponse> call = null;
            if (first.getAbsoluteFile().equals("/")) {
                call = apiCallService.getUploadDeviceImagesB("Bearer " + userSession.getToken(), currentAddressR, orderIdR, imeiSerialFile, aadharImageFile, declarationImageFile, displayTouchScreen);
                System.out.println("Calling Method :- " + "getUploadDeviceImagesB");
            } else if (first.getAbsoluteFile().equals("/") && second.getAbsoluteFile().equals("/")) {
                call = apiCallService.getUploadDeviceImagesC("Bearer " + userSession.getToken(), currentAddressR, orderIdR, imeiSerialFile, aadharImageFile, declarationImageFile, displayTouchScreen, deviceBodyBackPanel);
                System.out.println("Calling Method :- " + "getUploadDeviceImagesC");
            } else if (first.getAbsoluteFile().equals("/") && second.getAbsoluteFile().equals("/") && third.getAbsoluteFile().equals("/")) {
                call = apiCallService.getUploadDeviceImagesD("Bearer " + userSession.getToken(), currentAddressR, orderIdR, imeiSerialFile, aadharImageFile, declarationImageFile, displayTouchScreen, deviceBodyBackPanel, silverFrame);
                System.out.println("Calling Method :- " + "getUploadDeviceImagesD");
            } else if (first.getAbsoluteFile().equals("/") && second.getAbsoluteFile().equals("/") && third.getAbsoluteFile().equals("/") && fourth.getAbsoluteFile().equals("/")) {
                call = apiCallService.getUploadDeviceImagesE("Bearer " + userSession.getToken(), currentAddressR, orderIdR, imeiSerialFile, aadharImageFile, declarationImageFile, displayTouchScreen, deviceBodyBackPanel, silverFrame, mainCamera);
                System.out.println("Calling Method :- " + "getUploadDeviceImagesE");
            } else {
                call = apiCallService.getUploadDeviceImagesA("Bearer " + userSession.getToken(), currentAddressR, orderIdR, imeiSerialFile, aadharImageFile, declarationImageFile);
                System.out.println("Calling Method :- " + "getUploadDeviceImagesA");
            }
            call.enqueue(new retrofit2.Callback<UploadDeviceImagesResponse>() {
                @Override
                public void onResponse(Call<UploadDeviceImagesResponse> call, Response<UploadDeviceImagesResponse> response) {
                    if (response.isSuccessful()) {
                        Log.d("Response error :- ", response.body().toString());
                        if (response.body().getStatusCode() == 200) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            if (response.body().getData().getStatusMessage().equalsIgnoreCase("")) {
                                Toast.makeText(mContext, "Image Upload successfully!", Toast.LENGTH_SHORT).show();
                                getConfirmOrderForPickupRider(gsonObject);
                            } else {
                                Toast.makeText(mContext, "Your Image is not uploaded successfully!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            Log.d("Response error :- ", response.body().toString());
                            System.out.println("Error response :- " + response.body().getErrorMessage().toString());
                            Toast.makeText(mContext, "Your Image is already uploaded successfully!", Toast.LENGTH_SHORT).show();
//                            Toast.makeText(mContext, response.body().getErrorMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Log.d("Response error :- ", response.body().toString());
                        Toast.makeText(mContext, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UploadDeviceImagesResponse> call, Throwable t) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    System.out.println("Throwable Image :- " + t.getMessage());
                }
            });
        } catch (Exception exp) {
            exp.getStackTrace();
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            System.out.println("Exception :- " + exp.getStackTrace());
        }
    }

    private void getConfirmOrderForPickupRider(JsonObject gsonObject) {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        try {
            ApiCallService apiClient = ApiNetworkClient.getSncRiderRetrofit().create(ApiCallService.class);
            Call<ConfirmOrderResponse> responseCall = apiClient.getConfirmOrder(gsonObject);
            responseCall.enqueue(new retrofit2.Callback<ConfirmOrderResponse>() {
                @Override
                public void onResponse(Call<ConfirmOrderResponse> call, Response<ConfirmOrderResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatusCode() == 200) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            if (response.body().getData() != null) {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                System.out.println("Your Confirm Order Number :- " + response.body().getData().getOrderId());
                                getAlertDialogForSuccess("You have done confirm your order!", userSession.getOrderNumber());
                            } else {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                Toast.makeText(mContext, "Your Order is not Confirmed!", Toast.LENGTH_SHORT).show();
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
                public void onFailure(Call<ConfirmOrderResponse> call, Throwable t) {
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

    ActivityResultLauncher<Intent> launchSerialActivity = registerForActivityResult(new
            ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            try {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Bitmap image = null;
                    imeiSerialNumberImg.setImageURI(photoURI);
                    if (data != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            imeiSerialImageInBase64 = new File(serialPhotoPath);
                        } else {
                            image = (Bitmap) data.getExtras().get("data");
                            imeiSerialNumberImg.setImageBitmap(image);
                            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                            image.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                            File imeiSerialImage = new File(Environment.getExternalStorageDirectory(), "imeiSerialImage.jpg");
                            imeiSerialImageInBase64 = new File(imeiSerialImage.getAbsolutePath());
                            FileOutputStream fo;
                            try {
                                fo = new FileOutputStream(imeiSerialImageInBase64);
                                fo.write(bytes.toByteArray());
                                fo.close();
                                System.out.println("Image of imei serial image else :- " + imeiSerialImageInBase64.getPath());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        System.out.println("Image Path :- not found!");
                    }
                }
            } catch (Exception exp) {
                exp.getStackTrace();
            }
        }
    });

    ActivityResultLauncher<Intent> launchAadharActivity = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            try {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Bitmap image = null;
                    aadharImg.setImageURI(photoURI);
                    if (data != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            aadharImageInBase64 = new File(serialPhotoPath);
                        } else {
                            image = (Bitmap) data.getExtras().get("data");
                            aadharImg.setImageBitmap(image);
                            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                            image.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                            File aadharImage = new File(Environment.getExternalStorageDirectory(), "aadharImage.jpg");
                            aadharImageInBase64 = new File(aadharImage.getAbsolutePath());
                            FileOutputStream fo;
                            try {
                                fo = new FileOutputStream(aadharImageInBase64);
                                fo.write(bytes.toByteArray());
                                fo.close();
                                System.out.println("Image of aadhar image else :- " + aadharImageInBase64.getPath());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        System.out.println("Image Path :- not found!");
                    }
                }
            } catch (Exception exp) {
                exp.getStackTrace();
            }
        }
    });

    ActivityResultLauncher<Intent> launchDeclarationActivity = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            try {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Bitmap image = null;
                    declarationImg.setImageURI(photoURI);
                    if (data != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            declarationImageInBase64 = new File(serialPhotoPath);
                        } else {
                            image = (Bitmap) data.getExtras().get("data");
                            declarationImg.setImageBitmap(image);
                            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                            image.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                            File declarationImage = new File(Environment.getExternalStorageDirectory(), "declarationImage.jpg");
                            declarationImageInBase64 = new File(declarationImage.getAbsolutePath());
                            FileOutputStream fo;
                            try {
                                fo = new FileOutputStream(declarationImageInBase64);
                                fo.write(bytes.toByteArray());
                                fo.close();
                                System.out.println("Image of declaration image else :- " + declarationImageInBase64.getPath());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        System.out.println("Image Path :- not found!");
                    }
                }
            } catch (Exception exp) {
                exp.getStackTrace();
            }
        }
    });

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        serialPhotoPath = image.getAbsolutePath();
        return image;
    }
}