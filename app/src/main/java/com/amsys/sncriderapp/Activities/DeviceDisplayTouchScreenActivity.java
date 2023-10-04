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

public class DeviceDisplayTouchScreenActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private UserSession userSession;
    private ImageView backDisplayTouchImg, flawlessImg, minorImg, dotsImg, pixelImg, screenImage;
    private AppCompatButton continueDTBtn, viewAnswerBtn, captureScreenImageTBtn;
    private CardView flawCardLayout, minorCardLayout, dotsCardLayout, brokenCardLayout;
    private TextView flawlessTxt, minorTxt, dotsTxt, pixelTxt;
    private boolean flawless = false, minorScratchesTwoOrThree = false, shadedWhiteDots = false, brokenDead = false;
    private LinearLayout screenImageLayout;
    private String imageInBase64 = "";
    private File displaytouchscreen = null;
    private String serialPhotoPath;
    private Uri photoURI = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_display_touch_screen);
        mContext = this;
        userSession = new UserSession(mContext);

        getDisplayTouchLayoutUiIdFind();

        backDisplayTouchImg.setOnClickListener(this);
        continueDTBtn.setOnClickListener(this);
        viewAnswerBtn.setOnClickListener(this);
        flawCardLayout.setOnClickListener(this);
        minorCardLayout.setOnClickListener(this);
        dotsCardLayout.setOnClickListener(this);
        brokenCardLayout.setOnClickListener(this);
        captureScreenImageTBtn.setOnClickListener(this);

    }

    private void getDisplayTouchLayoutUiIdFind() {
        try {
            backDisplayTouchImg = (ImageView) findViewById(R.id.backDisplayTouchImg);
            flawlessImg = (ImageView) findViewById(R.id.flawlessImg);
            minorImg = (ImageView) findViewById(R.id.minorImg);
            dotsImg = (ImageView) findViewById(R.id.dotsImg);
            pixelImg = (ImageView) findViewById(R.id.pixelImg);
            screenImage = (ImageView) findViewById(R.id.screenImage);
            continueDTBtn = (AppCompatButton) findViewById(R.id.continueDTBtn);
            viewAnswerBtn = (AppCompatButton) findViewById(R.id.viewAnswerBtn);
            captureScreenImageTBtn = (AppCompatButton) findViewById(R.id.captureScreenImageTBtn);
            flawCardLayout = (CardView) findViewById(R.id.flawCardLayout);
            minorCardLayout = (CardView) findViewById(R.id.minorCardLayout);
            dotsCardLayout = (CardView) findViewById(R.id.dotsCardLayout);
            brokenCardLayout = (CardView) findViewById(R.id.brokenCardLayout);
            flawlessTxt = (TextView) findViewById(R.id.flawlessTxt);
            minorTxt = (TextView) findViewById(R.id.minorTxt);
            dotsTxt = (TextView) findViewById(R.id.dotsTxt);
            pixelTxt = (TextView) findViewById(R.id.pixelTxt);
            screenImageLayout = (LinearLayout) findViewById(R.id.screenImageLayout);

            userSession.setBrokenDeadPixelLiquidOfDisplayAndTouchScreen("");
            userSession.setShadedWhiteDotsOfDisplayAndTouchScreen("");
            userSession.setMinorScratchesOfDisplayAndTouchScreen("");
            userSession.setFlawlessValueOfDisplayAndTouchScreen("");

            screenImageLayout.setVisibility(View.GONE);
            continueDTBtn.setVisibility(View.VISIBLE);
            captureScreenImageTBtn.setVisibility(View.GONE);

        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backDisplayTouchImg:
                userSession.setBrokenDeadPixelLiquidOfDisplayAndTouchScreen("");
                userSession.setShadedWhiteDotsOfDisplayAndTouchScreen("");
                userSession.setMinorScratchesOfDisplayAndTouchScreen("");
                userSession.setFlawlessValueOfDisplayAndTouchScreen("");
                onBackPressed();
                break;

            case R.id.flawCardLayout:
                if (flawless == false) {
                    flawless = true;
                    userSession.setFlawlessValueOfDisplayAndTouchScreen("Flawless");
                    flawCardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    flawlessImg.setVisibility(View.VISIBLE);

                    screenImageLayout.setVisibility(View.GONE);
                    continueDTBtn.setVisibility(View.VISIBLE);
                    captureScreenImageTBtn.setVisibility(View.GONE);

                    minorScratchesTwoOrThree = false;
                    userSession.setMinorScratchesOfDisplayAndTouchScreen("");
                    minorCardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    minorTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    minorImg.setVisibility(View.INVISIBLE);

                    shadedWhiteDots = false;
                    userSession.setShadedWhiteDotsOfDisplayAndTouchScreen("");
                    dotsCardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    dotsTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    dotsImg.setVisibility(View.INVISIBLE);

                    brokenDead = false;
                    userSession.setBrokenDeadPixelLiquidOfDisplayAndTouchScreen("");
                    brokenCardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    pixelTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    pixelImg.setVisibility(View.INVISIBLE);

                } else if (flawless == true) {
                    flawless = false;
                    userSession.setFlawlessValueOfDisplayAndTouchScreen("");
                    flawCardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessImg.setVisibility(View.INVISIBLE);
                    screenImageLayout.setVisibility(View.GONE);
                    continueDTBtn.setVisibility(View.VISIBLE);
                    captureScreenImageTBtn.setVisibility(View.GONE);

                } else {
                    flawCardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.minorCardLayout:
                if (minorScratchesTwoOrThree == false) {
                    minorScratchesTwoOrThree = true;
                    userSession.setMinorScratchesOfDisplayAndTouchScreen("2-3 Minor Scratches");
                    minorCardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    minorTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    minorImg.setVisibility(View.VISIBLE);

                    screenImageLayout.setVisibility(View.GONE);
                    continueDTBtn.setVisibility(View.GONE);
                    captureScreenImageTBtn.setVisibility(View.VISIBLE);

                    flawless = false;
                    userSession.setFlawlessValueOfDisplayAndTouchScreen("");
                    flawCardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessImg.setVisibility(View.INVISIBLE);

                    shadedWhiteDots = false;
                    userSession.setShadedWhiteDotsOfDisplayAndTouchScreen("");
                    dotsCardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    dotsTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    dotsImg.setVisibility(View.INVISIBLE);

                    brokenDead = false;
                    userSession.setBrokenDeadPixelLiquidOfDisplayAndTouchScreen("");
                    brokenCardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    pixelTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    pixelImg.setVisibility(View.INVISIBLE);

                } else if (minorScratchesTwoOrThree == true) {
                    minorScratchesTwoOrThree = false;
                    userSession.setMinorScratchesOfDisplayAndTouchScreen("");
                    minorCardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    minorTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    minorImg.setVisibility(View.INVISIBLE);
                    screenImageLayout.setVisibility(View.GONE);
                    continueDTBtn.setVisibility(View.VISIBLE);
                    captureScreenImageTBtn.setVisibility(View.GONE);
                } else {
                    minorCardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    minorTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    minorImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.dotsCardLayout:
                if (shadedWhiteDots == false) {
                    shadedWhiteDots = true;
                    userSession.setShadedWhiteDotsOfDisplayAndTouchScreen("Shaded/White Dots");
                    dotsCardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    dotsTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    dotsImg.setVisibility(View.VISIBLE);

                    screenImageLayout.setVisibility(View.GONE);
                    continueDTBtn.setVisibility(View.GONE);
                    captureScreenImageTBtn.setVisibility(View.VISIBLE);

                    flawless = false;
                    userSession.setFlawlessValueOfDisplayAndTouchScreen("");
                    flawCardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessImg.setVisibility(View.INVISIBLE);

                    minorScratchesTwoOrThree = false;
                    userSession.setMinorScratchesOfDisplayAndTouchScreen("");
                    minorCardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    minorTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    minorImg.setVisibility(View.INVISIBLE);

                    brokenDead = false;
                    userSession.setBrokenDeadPixelLiquidOfDisplayAndTouchScreen("");
                    brokenCardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    pixelTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    pixelImg.setVisibility(View.INVISIBLE);
                } else if (shadedWhiteDots == true) {
                    shadedWhiteDots = false;
                    userSession.setShadedWhiteDotsOfDisplayAndTouchScreen("");
                    dotsCardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    dotsTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    dotsImg.setVisibility(View.INVISIBLE);
                    screenImageLayout.setVisibility(View.GONE);
                    continueDTBtn.setVisibility(View.VISIBLE);
                    captureScreenImageTBtn.setVisibility(View.GONE);
                } else {
                    dotsCardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    dotsTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    dotsImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.brokenCardLayout:
                if (brokenDead == false) {
                    brokenDead = true;
                    userSession.setBrokenDeadPixelLiquidOfDisplayAndTouchScreen("Broken Dead Pixel Liquid Mark or Dots not work properly");
                    brokenCardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    pixelTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    pixelImg.setVisibility(View.VISIBLE);

                    screenImageLayout.setVisibility(View.GONE);
                    continueDTBtn.setVisibility(View.GONE);
                    captureScreenImageTBtn.setVisibility(View.VISIBLE);

                    flawless = false;
                    userSession.setFlawlessValueOfDisplayAndTouchScreen("");
                    flawCardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    flawlessImg.setVisibility(View.INVISIBLE);

                    minorScratchesTwoOrThree = false;
                    userSession.setMinorScratchesOfDisplayAndTouchScreen("");
                    minorCardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    minorTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    minorImg.setVisibility(View.INVISIBLE);

                    shadedWhiteDots = false;
                    userSession.setShadedWhiteDotsOfDisplayAndTouchScreen("");
                    dotsCardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    dotsTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    dotsImg.setVisibility(View.INVISIBLE);

                } else if (brokenDead == true) {
                    brokenDead = false;
                    userSession.setBrokenDeadPixelLiquidOfDisplayAndTouchScreen("");
                    brokenCardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    pixelTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    pixelImg.setVisibility(View.INVISIBLE);
                    screenImageLayout.setVisibility(View.GONE);
                    continueDTBtn.setVisibility(View.VISIBLE);
                    captureScreenImageTBtn.setVisibility(View.GONE);
                } else {
                    brokenCardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    pixelTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    pixelImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.continueDTBtn:
                if (minorScratchesTwoOrThree == true || shadedWhiteDots == true || brokenDead == true) {
                    JsonObject displayTouch = new JsonObject();
                    displayTouch.addProperty("flawless", flawless);
                    displayTouch.addProperty("minorScratchesTwoOrThree", minorScratchesTwoOrThree);
                    displayTouch.addProperty("shaded_WhiteDots", shadedWhiteDots);
                    displayTouch.addProperty("broken_Dead", brokenDead);
                    userSession.setDisplayTouchJson(displayTouch.toString());
                    System.out.println("Touch screen json :- " + displayTouch);
                    startActivity(new Intent(mContext, AvailableAccessoriesActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                } else if (flawless == true) {
                    JsonObject displayTouch = new JsonObject();
                    displayTouch.addProperty("flawless", flawless);
                    displayTouch.addProperty("minorScratchesTwoOrThree", minorScratchesTwoOrThree);
                    displayTouch.addProperty("shaded_WhiteDots", shadedWhiteDots);
                    displayTouch.addProperty("broken_Dead", brokenDead);
                    userSession.setDisplayTouchJson(displayTouch.toString());
                    System.out.println("Touch screen json :- " + displayTouch);
                    userSession.setFirstImagePath(null);
                    startActivity(new Intent(mContext, AvailableAccessoriesActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                } else {
                    Toast.makeText(mContext, "Please check any option of display and touch screen!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.captureScreenImageTBtn:
                if (minorScratchesTwoOrThree == true || shadedWhiteDots == true || brokenDead == true) {
                    if (screenImageLayout.getVisibility() == View.GONE) {
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
                    Toast.makeText(mContext, "Please check any option of display and touch screen!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.viewAnswerBtn:
                startActivity(new Intent(mContext, ViewAnswerDetailsActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
        }
    }

    ActivityResultLauncher<Intent> launchCameraActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    try {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Bitmap image = null;

                            screenImage.setImageURI(photoURI);
                            if (data != null) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                    displaytouchscreen = new File(serialPhotoPath);
                                } else {
                                    image = (Bitmap) data.getExtras().get("data");
                                    screenImage.setImageBitmap(image);
                                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                                    image.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                                    File imeiSerialImage = new File(Environment.getExternalStorageDirectory(), "displaytouchscreen.jpg");
                                    displaytouchscreen = new File(imeiSerialImage.getAbsolutePath());
                                    userSession.setSecondImagePath(displaytouchscreen.toString());
                                    FileOutputStream fo;
                                    try {
                                        fo = new FileOutputStream(displaytouchscreen);
                                        fo.write(bytes.toByteArray());
                                        fo.close();
                                        userSession.setSecondImagePath(displaytouchscreen.toString());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                screenImageLayout.setVisibility(View.VISIBLE);
                                continueDTBtn.setVisibility(View.VISIBLE);
                                captureScreenImageTBtn.setVisibility(View.GONE);
                            } else {
                                System.out.println("Image Path :- not found!");
                                screenImageLayout.setVisibility(View.GONE);
                                continueDTBtn.setVisibility(View.GONE);
                                captureScreenImageTBtn.setVisibility(View.VISIBLE);
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