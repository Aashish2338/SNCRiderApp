package com.amsys.sncriderapp.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.amsys.sncriderapp.ApiCallService;
import com.amsys.sncriderapp.R;
import com.amsys.sncriderapp.ResponseModel.ExactPriceResponse;
import com.amsys.sncriderapp.ResponseModel.UploadDeviceImagesResponse;
import com.amsys.sncriderapp.Utilities.ApiNetworkClient;
import com.amsys.sncriderapp.Utilities.NetworkStatus;
import com.amsys.sncriderapp.Utilities.UserSession;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainCameraActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private UserSession userSession;
    private ImageView backCameraImg, okayImg, scratchedImg, blurImg, crackedImg, brokenImg, mainCameraGlassImage;
    private TextView okayTxt, scratchedTxt, blurTxt, crackedTxt, brokenTxt;
    private AppCompatButton continueCameraBtn, viewAnswerBtn, captureCameraBtn;
    private CardView okayCard, scratchedCard, blurCard, crackedCard, brokenCard;
    private boolean okayFlawless = false, scratched = false, blur = false, cracked = false, broken = false;
    private JSONObject jsonMain = null;
    private LinearLayout mainCameraGlassLayout;
    private String imageInBase64 = "";
    private File maincamera = null;
    private String serialPhotoPath;
    private Uri photoURI = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_camera);
        mContext = this;
        userSession = new UserSession(mContext);

        getCameraLayoutUiIdFind();

        backCameraImg.setOnClickListener(this);
        continueCameraBtn.setOnClickListener(this);
        viewAnswerBtn.setOnClickListener(this);
        okayCard.setOnClickListener(this);
        scratchedCard.setOnClickListener(this);
        blurCard.setOnClickListener(this);
        crackedCard.setOnClickListener(this);
        brokenCard.setOnClickListener(this);
        captureCameraBtn.setOnClickListener(this);

    }

    private void getCameraLayoutUiIdFind() {
        try {
            backCameraImg = (ImageView) findViewById(R.id.backCameraImg);
            okayImg = (ImageView) findViewById(R.id.okayImg);
            scratchedImg = (ImageView) findViewById(R.id.scratchedImg);
            blurImg = (ImageView) findViewById(R.id.blurImg);
            crackedImg = (ImageView) findViewById(R.id.crackedImg);
            brokenImg = (ImageView) findViewById(R.id.brokenImg);
            mainCameraGlassImage = (ImageView) findViewById(R.id.mainCameraGlassImage);
            continueCameraBtn = (AppCompatButton) findViewById(R.id.continueCameraBtn);
            viewAnswerBtn = (AppCompatButton) findViewById(R.id.viewAnswerBtn);
            captureCameraBtn = (AppCompatButton) findViewById(R.id.captureCameraBtn);
            okayCard = (CardView) findViewById(R.id.okayCard);
            scratchedCard = (CardView) findViewById(R.id.scratchedCard);
            blurCard = (CardView) findViewById(R.id.blurCard);
            crackedCard = (CardView) findViewById(R.id.crackedCard);
            brokenCard = (CardView) findViewById(R.id.brokenCard);
            okayTxt = (TextView) findViewById(R.id.okayTxt);
            scratchedTxt = (TextView) findViewById(R.id.scratchedTxt);
            blurTxt = (TextView) findViewById(R.id.blurTxt);
            crackedTxt = (TextView) findViewById(R.id.crackedTxt);
            brokenTxt = (TextView) findViewById(R.id.brokenTxt);
            mainCameraGlassLayout = (LinearLayout) findViewById(R.id.mainCameraGlassLayout);

            userSession.setOkOfCamera("");
            userSession.setScratchedOfCamera("");
            userSession.setBlurOfCamera("");
            userSession.setCrackedOfCamera("");
            userSession.setBrokenOfCamera("");

            mainCameraGlassLayout.setVisibility(View.GONE);
            continueCameraBtn.setVisibility(View.VISIBLE);
            captureCameraBtn.setVisibility(View.GONE);

        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backCameraImg:
                userSession.setOkOfCamera("");
                userSession.setScratchedOfCamera("");
                userSession.setBlurOfCamera("");
                userSession.setCrackedOfCamera("");
                userSession.setBrokenOfCamera("");
                onBackPressed();
                break;

            case R.id.okayCard:
                if (okayFlawless == false) {
                    okayFlawless = true;
                    userSession.setOkOfCamera("Okay");
                    okayCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    okayTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    okayImg.setVisibility(View.VISIBLE);

                    mainCameraGlassLayout.setVisibility(View.GONE);
                    continueCameraBtn.setVisibility(View.VISIBLE);
                    captureCameraBtn.setVisibility(View.GONE);

                    scratched = false;
                    userSession.setScratchedOfCamera("");
                    scratchedCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    scratchedTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    scratchedImg.setVisibility(View.INVISIBLE);

                    blur = false;
                    userSession.setBlurOfCamera("");
                    blurCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    blurTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    blurImg.setVisibility(View.INVISIBLE);

                    broken = false;
                    userSession.setBrokenOfCamera("");
                    brokenCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenImg.setVisibility(View.INVISIBLE);
                } else if (okayFlawless == true) {
                    okayFlawless = false;
                    userSession.setOkOfCamera("");
                    okayCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    okayTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    okayImg.setVisibility(View.INVISIBLE);
                    mainCameraGlassLayout.setVisibility(View.GONE);
                    continueCameraBtn.setVisibility(View.VISIBLE);
                    captureCameraBtn.setVisibility(View.GONE);
                } else {
                    okayCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    okayTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    okayImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.scratchedCard:
                if (scratched == false) {
                    scratched = true;
                    userSession.setScratchedOfCamera("Scratched");
                    scratchedCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    scratchedTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    scratchedImg.setVisibility(View.VISIBLE);

                    mainCameraGlassLayout.setVisibility(View.GONE);
                    continueCameraBtn.setVisibility(View.GONE);
                    captureCameraBtn.setVisibility(View.VISIBLE);

                    okayFlawless = false;
                    userSession.setOkOfCamera("");
                    okayCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    okayTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    okayImg.setVisibility(View.INVISIBLE);

                    blur = false;
                    userSession.setBlurOfCamera("");
                    blurCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    blurTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    blurImg.setVisibility(View.INVISIBLE);

                    broken = false;
                    userSession.setBrokenOfCamera("");
                    brokenCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenImg.setVisibility(View.INVISIBLE);

                } else if (scratched == true) {
                    scratched = false;
                    userSession.setScratchedOfCamera("");
                    scratchedCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    scratchedTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    scratchedImg.setVisibility(View.INVISIBLE);
                    mainCameraGlassLayout.setVisibility(View.GONE);
                    continueCameraBtn.setVisibility(View.VISIBLE);
                    captureCameraBtn.setVisibility(View.GONE);
                } else {
                    scratchedCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    scratchedTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    scratchedImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.blurCard:
                if (blur == false) {
                    blur = true;
                    userSession.setBlurOfCamera("Blur");
                    blurCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    blurTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    blurImg.setVisibility(View.VISIBLE);

                    mainCameraGlassLayout.setVisibility(View.GONE);
                    continueCameraBtn.setVisibility(View.GONE);
                    captureCameraBtn.setVisibility(View.VISIBLE);

                    okayFlawless = false;
                    userSession.setOkOfCamera("");
                    okayCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    okayTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    okayImg.setVisibility(View.INVISIBLE);

                    scratched = false;
                    userSession.setScratchedOfCamera("");
                    scratchedCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    scratchedTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    scratchedImg.setVisibility(View.INVISIBLE);

                    broken = false;
                    userSession.setBrokenOfCamera("");
                    brokenCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenImg.setVisibility(View.INVISIBLE);

                } else if (blur == true) {
                    blur = false;
                    userSession.setBlurOfCamera("");
                    blurCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    blurTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    blurImg.setVisibility(View.INVISIBLE);
                    mainCameraGlassLayout.setVisibility(View.GONE);
                    continueCameraBtn.setVisibility(View.VISIBLE);
                    captureCameraBtn.setVisibility(View.GONE);
                } else {
                    blurCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    blurTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    blurImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.brokenCard:
                if (broken == false) {
                    broken = true;
                    userSession.setBrokenOfCamera("Broken");
                    brokenCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    brokenImg.setVisibility(View.VISIBLE);

                    mainCameraGlassLayout.setVisibility(View.GONE);
                    continueCameraBtn.setVisibility(View.GONE);
                    captureCameraBtn.setVisibility(View.VISIBLE);

                    okayFlawless = false;
                    userSession.setOkOfCamera("");
                    okayCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    okayTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    okayImg.setVisibility(View.INVISIBLE);

                    scratched = false;
                    userSession.setScratchedOfCamera("");
                    scratchedCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    scratchedTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    scratchedImg.setVisibility(View.INVISIBLE);

                    blur = false;
                    userSession.setBlurOfCamera("");
                    blurCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    blurTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    blurImg.setVisibility(View.INVISIBLE);

                } else if (broken == true) {
                    broken = false;
                    userSession.setBrokenOfCamera("");
                    brokenCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenImg.setVisibility(View.INVISIBLE);
                    mainCameraGlassLayout.setVisibility(View.GONE);
                    continueCameraBtn.setVisibility(View.VISIBLE);
                    captureCameraBtn.setVisibility(View.GONE);
                } else {
                    brokenCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.continueCameraBtn:
                if (scratched == true || blur == true || cracked == true || broken == true) {
                    JsonObject mainCamera = new JsonObject();
                    mainCamera.addProperty("okayFlawless", okayFlawless);
                    mainCamera.addProperty("scratched", scratched);
                    mainCamera.addProperty("blur", blur);
                    mainCamera.addProperty("cracked", cracked);
                    mainCamera.addProperty("broken", broken);
                    userSession.setMainCameraJson(mainCamera.toString());
                    System.out.println("Main camera json :- " + mainCamera);
                    getJsonReportsOfQuestionair();
                } else if (okayFlawless == true) {
                    JsonObject mainCamera = new JsonObject();
                    mainCamera.addProperty("okayFlawless", okayFlawless);
                    mainCamera.addProperty("scratched", scratched);
                    mainCamera.addProperty("blur", blur);
                    mainCamera.addProperty("cracked", cracked);
                    mainCamera.addProperty("broken", broken);
                    userSession.setMainCameraJson(mainCamera.toString());
                    System.out.println("Main camera json :- " + mainCamera);
                    userSession.setFourthImagePath(null);
                    getJsonReportsOfQuestionair();
                } else {
                    Toast.makeText(mContext, "Please check any option of main camera(Camera glass)!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.captureCameraBtn:
                if (scratched == true || blur == true || cracked == true || broken == true) {
                    if (mainCameraGlassLayout.getVisibility() == View.GONE) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            File photoFile = null;
                            try {
                                photoFile = createImageFile();
                                if (photoFile != null) {
                                    photoURI = FileProvider.getUriForFile(mContext, "com.amsys.sncriderapp.FileProvider", photoFile);
                                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                                    launchCameraActivity.launch(takePictureIntent);
                                }
                            } catch (IOException ex) {
                                Log.d("Tag", ex.toString());
                            }
                        } else {
                            Intent cameraAadharIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            launchCameraActivity.launch(cameraAadharIntent);
                        }
                    }
                } else {
                    Toast.makeText(mContext, "Please check any option of main camera(Camera glass)!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.viewAnswerBtn:
                startActivity(new Intent(mContext, ViewAnswerDetailsActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
        }
    }

    ActivityResultLauncher<Intent> launchCameraActivity = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            try {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Bitmap image = null;
                    mainCameraGlassImage.setImageURI(photoURI);
                    if (data != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            maincamera = new File(serialPhotoPath);
                        } else {
                            image = (Bitmap) data.getExtras().get("data");
                            mainCameraGlassImage.setImageBitmap(image);
                            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                            image.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                            File imeiSerialImage = new File(Environment.getExternalStorageDirectory(), "maincamera.jpg");
                            maincamera = new File(imeiSerialImage.getAbsolutePath());
                            userSession.setFourthImagePath(maincamera.toString());
                            FileOutputStream fo;
                            try {
                                fo = new FileOutputStream(maincamera);
                                fo.write(bytes.toByteArray());
                                fo.close();
                                userSession.setFourthImagePath(maincamera.toString());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        mainCameraGlassLayout.setVisibility(View.VISIBLE);
                        continueCameraBtn.setVisibility(View.VISIBLE);
                        captureCameraBtn.setVisibility(View.GONE);
                    } else {
                        System.out.println("Image Path :- not found!");
                        mainCameraGlassLayout.setVisibility(View.GONE);
                        continueCameraBtn.setVisibility(View.GONE);
                        captureCameraBtn.setVisibility(View.VISIBLE);
                    }
                }
            } catch (Exception exp) {
                exp.getStackTrace();
            }
        }
    });

    private void getJsonReportsOfQuestionair() {
        try {
            jsonMain = new JSONObject();
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

            jsonMain.put("tupc", userSession.getTupcId());
            jsonMain.put("ageing", userSession.getAgeingId());
            jsonMain.put("questionairs", jsonQuestion);

            jsonQuestion.put("deviceOnOff", jsonDeviceOnOff);
            jsonQuestion.put("displayTouch", jsonDisplayTouch);
            jsonQuestion.put("accessories", jsonAccessories);
            jsonQuestion.put("functionalIssue", jsonFunctionalIssue);
            jsonQuestion.put("repair", jsonRepair);
            jsonQuestion.put("warranty_Utilized", jsonWarrantyUtilized);
            jsonQuestion.put("device_Body", jsonDeviceBody);
            jsonQuestion.put("silver_Frame", jsonSilverFrame);
            jsonQuestion.put("main_Camera", jsonMainCamera);

            System.out.println("Main json data :- " + jsonMain);

            if (NetworkStatus.isNetworkAvailable(mContext)) {
                JSONObject object = jsonMain;
                JsonParser jsonParser = new JsonParser();
                JsonObject gsonObject = (JsonObject) jsonParser.parse(object.toString());
                getExactPriceForRider(gsonObject);
            } else {
                Toast.makeText(mContext, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    private void getExactPriceForRider(JsonObject gsonObject) {
        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        try {
            ApiCallService apiClient = ApiNetworkClient.getSncRiderRetrofit().create(ApiCallService.class);
            Call<ExactPriceResponse> priceResponseCall = apiClient.getExactPrice(gsonObject);
            priceResponseCall.enqueue(new Callback<ExactPriceResponse>() {
                @Override
                public void onResponse(Call<ExactPriceResponse> call, Response<ExactPriceResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatusCode() == 200) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            if (response.body().getData() != null) {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                System.out.println("Main Camera Order Id :- " + response.body().getData().toString());
                                userSession.setExactPrice(response.body().getData().getExactPrice().intValue());
                                userSession.setTupcId(response.body().getData().getTupc());
                                userSession.setAgeingId(response.body().getData().getAgeing());
                                userSession.setProductName(response.body().getData().getVariantDetail().getProductName());
                                userSession.setProductImgUrl(response.body().getData().getVariantDetail().getProductImgUrl1());
                                startActivity(new Intent(mContext, SeeDeviceDetailsActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
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
                public void onFailure(Call<ExactPriceResponse> call, Throwable t) {
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

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