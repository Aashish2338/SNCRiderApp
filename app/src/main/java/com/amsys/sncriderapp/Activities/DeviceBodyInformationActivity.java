package com.amsys.sncriderapp.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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

import com.amsys.sncriderapp.R;
import com.amsys.sncriderapp.Utilities.UserSession;
import com.google.gson.JsonObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeviceBodyInformationActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private UserSession userSession;
    private ImageView backBodyDetailsImg, flawlessImg, scratchedImg, crackedImg, brokenImg, okayImg, looseImg, missingImg, captureBodyImage;
    private TextView flawlessTxt, scratchedTxt, crackedTxt, brokenTxt, okayTxt, looseTxt, missingTxt;
    private AppCompatButton continueBodyBtn, viewAnswerBtn, captureBodyImageBtn;
    private CardView flawlessCard, scratchCard, crackedCard, brokenCard, okayCard, looseCard, missingCard;
    private boolean flawless = false, scratched = false, cracked = false, broken = false, okay = false, loose = false, missing = false;
    private LinearLayout captureBodyLayout;
    private String imageInBase64 = "";
    private File devicebodybackpanel = null;
    private String serialPhotoPath;
    private Uri photoURI = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_body_information);
        mContext = this;
        userSession = new UserSession(mContext);

        getBodyLayoutUiIdFind();

        backBodyDetailsImg.setOnClickListener(this);
        continueBodyBtn.setOnClickListener(this);
        viewAnswerBtn.setOnClickListener(this);
        flawlessCard.setOnClickListener(this);
        scratchCard.setOnClickListener(this);
//        crackedCard.setOnClickListener(this);
        brokenCard.setOnClickListener(this);
//        okayCard.setOnClickListener(this);
        looseCard.setOnClickListener(this);
        missingCard.setOnClickListener(this);
        captureBodyImageBtn.setOnClickListener(this);

    }

    private void getBodyLayoutUiIdFind() {
        try {
            backBodyDetailsImg = (ImageView) findViewById(R.id.backBodyDetailsImg);
            flawlessImg = (ImageView) findViewById(R.id.flawlessImg);
            scratchedImg = (ImageView) findViewById(R.id.scratchedImg);
            crackedImg = (ImageView) findViewById(R.id.crackedImg);
            brokenImg = (ImageView) findViewById(R.id.brokenImg);
            okayImg = (ImageView) findViewById(R.id.okayImg);
            looseImg = (ImageView) findViewById(R.id.looseImg);
            missingImg = (ImageView) findViewById(R.id.missingImg);
            captureBodyImage = (ImageView) findViewById(R.id.captureBodyImage);
            continueBodyBtn = (AppCompatButton) findViewById(R.id.continueBodyBtn);
            viewAnswerBtn = (AppCompatButton) findViewById(R.id.viewAnswerBtn);
            captureBodyImageBtn = (AppCompatButton) findViewById(R.id.captureBodyImageBtn);
            flawlessCard = (CardView) findViewById(R.id.flawlessCard);
            scratchCard = (CardView) findViewById(R.id.scratchCard);
            crackedCard = (CardView) findViewById(R.id.crackedCard);
            brokenCard = (CardView) findViewById(R.id.brokenCard);
            okayCard = (CardView) findViewById(R.id.okayCard);
            looseCard = (CardView) findViewById(R.id.looseCard);
            missingCard = (CardView) findViewById(R.id.missingCard);
            flawlessTxt = (TextView) findViewById(R.id.flawlessTxt);
            scratchedTxt = (TextView) findViewById(R.id.scratchedTxt);
            crackedTxt = (TextView) findViewById(R.id.crackedTxt);
            brokenTxt = (TextView) findViewById(R.id.brokenTxt);
            okayTxt = (TextView) findViewById(R.id.okayTxt);
            looseTxt = (TextView) findViewById(R.id.looseTxt);
            missingTxt = (TextView) findViewById(R.id.missingTxt);
            captureBodyLayout = (LinearLayout) findViewById(R.id.captureBodyLayout);

            userSession.setFlawlessOfDeviceBodyDetails("");
            userSession.setScratchedOfDeviceBodyDetails("");
//            userSession.setCrackedOfDeviceBodyDetails("");
            userSession.setBrokenOfDeviceBodyDetails("");
//            userSession.setOkOfDeviceBodyDetails("");
            userSession.setLooseOfDeviceBodyDetails("");
            userSession.setMissingOfDeviceBodyDetails("");

            captureBodyLayout.setVisibility(View.GONE);
            continueBodyBtn.setVisibility(View.VISIBLE);
            captureBodyImageBtn.setVisibility(View.GONE);

        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBodyDetailsImg:
                userSession.setFlawlessOfDeviceBodyDetails("");
                userSession.setScratchedOfDeviceBodyDetails("");
//                userSession.setCrackedOfDeviceBodyDetails("");
                userSession.setBrokenOfDeviceBodyDetails("");
//                userSession.setOkOfDeviceBodyDetails("");
                userSession.setLooseOfDeviceBodyDetails("");
                userSession.setMissingOfDeviceBodyDetails("");
                onBackPressed();
                break;

            case R.id.flawlessCard:
                if (flawless == false) {
                    flawless = true;
                    userSession.setFlawlessOfDeviceBodyDetails("Flawless");
                    flawlessCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    flawlessImg.setVisibility(View.VISIBLE);

                    captureBodyLayout.setVisibility(View.GONE);
                    continueBodyBtn.setVisibility(View.VISIBLE);
                    captureBodyImageBtn.setVisibility(View.GONE);

                    missing = false;
                    userSession.setMissingOfDeviceBodyDetails("");
                    missingCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    missingTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    missingImg.setVisibility(View.INVISIBLE);

                    loose = false;
                    userSession.setLooseOfDeviceBodyDetails("");
                    looseCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    looseTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    looseImg.setVisibility(View.INVISIBLE);

                    broken = false;
                    userSession.setBrokenOfDeviceBodyDetails("");
                    brokenCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenImg.setVisibility(View.INVISIBLE);

                    scratched = false;
                    userSession.setScratchedOfDeviceBodyDetails("");
                    scratchCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    scratchedTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    scratchedImg.setVisibility(View.INVISIBLE);

                } else if (flawless == true) {
                    flawless = false;
                    userSession.setFlawlessOfDeviceBodyDetails("");
                    flawlessCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessImg.setVisibility(View.INVISIBLE);
                    captureBodyLayout.setVisibility(View.GONE);
                    continueBodyBtn.setVisibility(View.VISIBLE);
                    captureBodyImageBtn.setVisibility(View.GONE);
                } else {
                    flawlessCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.scratchCard:
                if (scratched == false) {
                    scratched = true;
                    userSession.setScratchedOfDeviceBodyDetails("Scratched");
                    scratchCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    scratchedTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    scratchedImg.setVisibility(View.VISIBLE);

                    captureBodyLayout.setVisibility(View.GONE);
                    continueBodyBtn.setVisibility(View.GONE);
                    captureBodyImageBtn.setVisibility(View.VISIBLE);

                    flawless = false;
                    userSession.setFlawlessOfDeviceBodyDetails("");
                    flawlessCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessImg.setVisibility(View.INVISIBLE);

                    missing = false;
                    userSession.setMissingOfDeviceBodyDetails("");
                    missingCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    missingTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    missingImg.setVisibility(View.INVISIBLE);

                    loose = false;
                    userSession.setLooseOfDeviceBodyDetails("");
                    looseCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    looseTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    looseImg.setVisibility(View.INVISIBLE);

                    broken = false;
                    userSession.setBrokenOfDeviceBodyDetails("");
                    brokenCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenImg.setVisibility(View.INVISIBLE);

                } else if (scratched == true) {
                    scratched = false;
                    userSession.setScratchedOfDeviceBodyDetails("");
                    scratchCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    scratchedTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    scratchedImg.setVisibility(View.INVISIBLE);
                    captureBodyLayout.setVisibility(View.GONE);
                    continueBodyBtn.setVisibility(View.VISIBLE);
                    captureBodyImageBtn.setVisibility(View.GONE);
                } else {
                    scratchCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    scratchedTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    scratchedImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.brokenCard:
                if (broken == false) {
                    broken = true;
                    userSession.setBrokenOfDeviceBodyDetails("Broken");
                    brokenCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    brokenImg.setVisibility(View.VISIBLE);

                    captureBodyLayout.setVisibility(View.GONE);
                    continueBodyBtn.setVisibility(View.GONE);
                    captureBodyImageBtn.setVisibility(View.VISIBLE);

                    flawless = false;
                    userSession.setFlawlessOfDeviceBodyDetails("");
                    flawlessCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessImg.setVisibility(View.INVISIBLE);

                    missing = false;
                    userSession.setMissingOfDeviceBodyDetails("");
                    missingCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    missingTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    missingImg.setVisibility(View.INVISIBLE);

                    loose = false;
                    userSession.setLooseOfDeviceBodyDetails("");
                    looseCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    looseTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    looseImg.setVisibility(View.INVISIBLE);

                    scratched = false;
                    userSession.setScratchedOfDeviceBodyDetails("");
                    scratchCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    scratchedTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    scratchedImg.setVisibility(View.INVISIBLE);

                } else if (broken == true) {
                    broken = false;
                    userSession.setBrokenOfDeviceBodyDetails("");
                    brokenCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenImg.setVisibility(View.INVISIBLE);
                    captureBodyLayout.setVisibility(View.GONE);
                    continueBodyBtn.setVisibility(View.VISIBLE);
                    captureBodyImageBtn.setVisibility(View.GONE);
                } else {
                    brokenCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.looseCard:
                if (loose == false) {
                    loose = true;
                    userSession.setLooseOfDeviceBodyDetails("Loose");
                    looseCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    looseTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    looseImg.setVisibility(View.VISIBLE);

                    captureBodyLayout.setVisibility(View.GONE);
                    continueBodyBtn.setVisibility(View.GONE);
                    captureBodyImageBtn.setVisibility(View.VISIBLE);

                    flawless = false;
                    userSession.setFlawlessOfDeviceBodyDetails("");
                    flawlessCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessImg.setVisibility(View.INVISIBLE);

                    missing = false;
                    userSession.setMissingOfDeviceBodyDetails("");
                    missingCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    missingTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    missingImg.setVisibility(View.INVISIBLE);

                    scratched = false;
                    userSession.setScratchedOfDeviceBodyDetails("");
                    scratchCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    scratchedTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    scratchedImg.setVisibility(View.INVISIBLE);

                    broken = true;
                    userSession.setBrokenOfDeviceBodyDetails("Broken");
                    brokenCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenImg.setVisibility(View.INVISIBLE);

                } else if (loose == true) {
                    loose = false;
                    userSession.setLooseOfDeviceBodyDetails("");
                    looseCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    looseTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    looseImg.setVisibility(View.INVISIBLE);
                    captureBodyLayout.setVisibility(View.GONE);
                    continueBodyBtn.setVisibility(View.VISIBLE);
                    captureBodyImageBtn.setVisibility(View.GONE);
                } else {
                    looseCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    looseTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    looseImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.missingCard:
                if (missing == false) {
                    missing = true;
                    userSession.setMissingOfDeviceBodyDetails("Missing");
                    missingCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    missingTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    missingImg.setVisibility(View.VISIBLE);

                    captureBodyLayout.setVisibility(View.GONE);
                    continueBodyBtn.setVisibility(View.GONE);
                    captureBodyImageBtn.setVisibility(View.VISIBLE);

                    flawless = false;
                    userSession.setFlawlessOfDeviceBodyDetails("");
                    flawlessCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessImg.setVisibility(View.INVISIBLE);

                    flawless = false;
                    userSession.setFlawlessOfDeviceBodyDetails("");
                    flawlessCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessImg.setVisibility(View.INVISIBLE);

                    scratched = false;
                    userSession.setScratchedOfDeviceBodyDetails("");
                    scratchCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    scratchedTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    scratchedImg.setVisibility(View.INVISIBLE);

                    broken = true;
                    userSession.setBrokenOfDeviceBodyDetails("Broken");
                    brokenCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenImg.setVisibility(View.INVISIBLE);

                } else if (missing == true) {
                    missing = false;
                    userSession.setMissingOfDeviceBodyDetails("");
                    missingCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    missingTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    missingImg.setVisibility(View.INVISIBLE);
                    captureBodyLayout.setVisibility(View.GONE);
                    continueBodyBtn.setVisibility(View.VISIBLE);
                    captureBodyImageBtn.setVisibility(View.GONE);
                } else {
                    missingCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    missingTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    missingImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.continueBodyBtn:
                if (scratched == true || broken == true || loose == true || missing == true) {
                    JsonObject deviceBody = new JsonObject();
                    deviceBody.addProperty("flawless", flawless);
                    deviceBody.addProperty("scratched", scratched);
                    deviceBody.addProperty("broken", broken);
                    deviceBody.addProperty("loose", loose);
                    deviceBody.addProperty("missing", missing);
                    userSession.setBodyInformationJson(deviceBody.toString());
                    System.out.println("Body information json :- " + deviceBody);
                    startActivity(new Intent(mContext, SilverFrameBezelActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                } else if (flawless == true) {
                    JsonObject deviceBody = new JsonObject();
                    deviceBody.addProperty("flawless", flawless);
                    deviceBody.addProperty("scratched", scratched);
                    deviceBody.addProperty("broken", broken);
                    deviceBody.addProperty("loose", loose);
                    deviceBody.addProperty("missing", missing);
                    userSession.setBodyInformationJson(deviceBody.toString());
                    System.out.println("Body information json :- " + deviceBody);
                    userSession.setSecondImagePath(null);
                    startActivity(new Intent(mContext, SilverFrameBezelActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                } else {
                    Toast.makeText(mContext, "Please check any option of device body(Back panel/Cover)!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.captureBodyImageBtn:
                if (scratched == true || broken == true || loose == true || missing == true) {
                    if (captureBodyLayout.getVisibility() == View.GONE) {
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
                    Toast.makeText(mContext, "Please check any option of device body(Back panel/Cover)!", Toast.LENGTH_SHORT).show();
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
                    captureBodyImage.setImageURI(photoURI);
                    if (data != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            devicebodybackpanel = new File(serialPhotoPath);
                        } else {
                            image = (Bitmap) data.getExtras().get("data");
                            captureBodyImage.setImageBitmap(image);
                            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                            image.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                            File imeiSerialImage = new File(Environment.getExternalStorageDirectory(), "devicebodybackpanel.jpg");
                            devicebodybackpanel = new File(imeiSerialImage.getAbsolutePath());
                            userSession.setSecondImagePath(devicebodybackpanel.toString());
                            FileOutputStream fo;
                            try {
                                fo = new FileOutputStream(devicebodybackpanel);
                                fo.write(bytes.toByteArray());
                                fo.close();
                                userSession.setSecondImagePath(devicebodybackpanel.toString());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        captureBodyLayout.setVisibility(View.VISIBLE);
                        continueBodyBtn.setVisibility(View.VISIBLE);
                        captureBodyImageBtn.setVisibility(View.GONE);
                    } else {
                        System.out.println("Image Path :- not found!");
                        captureBodyLayout.setVisibility(View.GONE);
                        continueBodyBtn.setVisibility(View.GONE);
                        captureBodyImageBtn.setVisibility(View.VISIBLE);
                    }
                }
            } catch (Exception exp) {
                exp.getStackTrace();
            }
        }
    });

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