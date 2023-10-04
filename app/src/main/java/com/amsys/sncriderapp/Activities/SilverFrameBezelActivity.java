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

public class SilverFrameBezelActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private UserSession userSession;
    private ImageView backScreenDetailsImg, okayImg, discoloredImg, dentedImg, brokenImg, silverFrameImage;
    private TextView okayTxt, discoloredTxt, dentedTxt, brokenTxt;
    private AppCompatButton continueScreenBtn, viewAnswerBtn, silverFrameImageBtn;
    private CardView okayCard, disclosedCard, dentedCard, brokenCard;
    private boolean okay = false, discolored = false, dented = false, broken = false;
    private LinearLayout silverFrameLayout;
    private String imageInBase64 = "";
    private File silverframe = null;
    private String serialPhotoPath;
    private Uri photoURI = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silver_frame_bezel);
        mContext = this;
        userSession = new UserSession(mContext);

        getFrameBezelLayoutUiIdFind();

        backScreenDetailsImg.setOnClickListener(this);
        continueScreenBtn.setOnClickListener(this);
        viewAnswerBtn.setOnClickListener(this);
        okayCard.setOnClickListener(this);
        okayCard.setOnClickListener(this);
        disclosedCard.setOnClickListener(this);
        dentedCard.setOnClickListener(this);
        brokenCard.setOnClickListener(this);
        silverFrameImageBtn.setOnClickListener(this);

    }

    private void getFrameBezelLayoutUiIdFind() {
        try {
            backScreenDetailsImg = (ImageView) findViewById(R.id.backScreenDetailsImg);
            okayImg = (ImageView) findViewById(R.id.okayImg);
            discoloredImg = (ImageView) findViewById(R.id.discoloredImg);
            dentedImg = (ImageView) findViewById(R.id.dentedImg);
            brokenImg = (ImageView) findViewById(R.id.brokenImg);
            silverFrameImage = (ImageView) findViewById(R.id.silverFrameImage);
            continueScreenBtn = (AppCompatButton) findViewById(R.id.continueScreenBtn);
            viewAnswerBtn = (AppCompatButton) findViewById(R.id.viewAnswerBtn);
            silverFrameImageBtn = (AppCompatButton) findViewById(R.id.silverFrameImageBtn);
            okayCard = (CardView) findViewById(R.id.okayCard);
            disclosedCard = (CardView) findViewById(R.id.disclosedCard);
            dentedCard = (CardView) findViewById(R.id.dentedCard);
            brokenCard = (CardView) findViewById(R.id.brokenCard);
            okayTxt = (TextView) findViewById(R.id.okayTxt);
            discoloredTxt = (TextView) findViewById(R.id.discoloredTxt);
            dentedTxt = (TextView) findViewById(R.id.dentedTxt);
            brokenTxt = (TextView) findViewById(R.id.brokenTxt);
            silverFrameLayout = (LinearLayout) findViewById(R.id.silverFrameLayout);

            userSession.setOkOfBezel("");
            userSession.setDiscoloredOfBezel("");
            userSession.setDentedOfBezel("");
            userSession.setBrokenOfBezel("");

            silverFrameLayout.setVisibility(View.GONE);
            continueScreenBtn.setVisibility(View.VISIBLE);
            silverFrameImageBtn.setVisibility(View.GONE);

        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backScreenDetailsImg:
                userSession.setOkOfBezel("");
                userSession.setDiscoloredOfBezel("");
                userSession.setDentedOfBezel("");
                userSession.setBrokenOfBezel("");
                onBackPressed();
                break;

            case R.id.okayCard:
                if (okay == false) {
                    okay = true;
                    userSession.setOkOfBezel("Okay");
                    okayCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    okayTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    okayImg.setVisibility(View.VISIBLE);

                    silverFrameLayout.setVisibility(View.GONE);
                    continueScreenBtn.setVisibility(View.VISIBLE);
                    silverFrameImageBtn.setVisibility(View.GONE);

                    discolored = false;
                    userSession.setDiscoloredOfBezel("");
                    disclosedCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    discoloredTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    discoloredImg.setVisibility(View.INVISIBLE);

                    dented = false;
                    userSession.setDentedOfBezel("");
                    dentedCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    dentedTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    dentedImg.setVisibility(View.INVISIBLE);

                    broken = false;
                    userSession.setBrokenOfBezel("");
                    brokenCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenImg.setVisibility(View.INVISIBLE);
                } else if (okay == true) {
                    okay = false;
                    userSession.setOkOfBezel("");
                    okayCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    okayTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    okayImg.setVisibility(View.INVISIBLE);
                    silverFrameLayout.setVisibility(View.GONE);
                    continueScreenBtn.setVisibility(View.VISIBLE);
                    silverFrameImageBtn.setVisibility(View.GONE);
                } else {
                    okayCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    okayTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    okayImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.disclosedCard:
                if (discolored == false) {
                    discolored = true;
                    userSession.setDiscoloredOfBezel("Discolored");
                    disclosedCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    discoloredTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    discoloredImg.setVisibility(View.VISIBLE);

                    silverFrameLayout.setVisibility(View.GONE);
                    continueScreenBtn.setVisibility(View.GONE);
                    silverFrameImageBtn.setVisibility(View.VISIBLE);

                    okay = false;
                    userSession.setOkOfBezel("");
                    okayCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    okayTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    okayImg.setVisibility(View.INVISIBLE);

                    dented = false;
                    userSession.setDentedOfBezel("");
                    dentedCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    dentedTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    dentedImg.setVisibility(View.INVISIBLE);

                    broken = false;
                    userSession.setBrokenOfBezel("");
                    brokenCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenImg.setVisibility(View.INVISIBLE);

                } else if (discolored == true) {
                    discolored = false;
                    userSession.setDiscoloredOfBezel("");
                    disclosedCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    discoloredTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    discoloredImg.setVisibility(View.INVISIBLE);
                    silverFrameLayout.setVisibility(View.GONE);
                    continueScreenBtn.setVisibility(View.VISIBLE);
                    silverFrameImageBtn.setVisibility(View.GONE);
                } else {
                    disclosedCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    discoloredTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    discoloredImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.dentedCard:
                if (dented == false) {
                    dented = true;
                    userSession.setDentedOfBezel("Dented");
                    dentedCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    dentedTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    dentedImg.setVisibility(View.VISIBLE);

                    silverFrameLayout.setVisibility(View.GONE);
                    continueScreenBtn.setVisibility(View.GONE);
                    silverFrameImageBtn.setVisibility(View.VISIBLE);

                    okay = false;
                    userSession.setOkOfBezel("");
                    okayCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    okayTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    okayImg.setVisibility(View.INVISIBLE);

                    discolored = false;
                    userSession.setDiscoloredOfBezel("");
                    disclosedCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    discoloredTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    discoloredImg.setVisibility(View.INVISIBLE);

                    broken = false;
                    userSession.setBrokenOfBezel("");
                    brokenCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenImg.setVisibility(View.INVISIBLE);

                } else if (dented == true) {
                    dented = false;
                    userSession.setDentedOfBezel("");
                    dentedCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    dentedTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    dentedImg.setVisibility(View.INVISIBLE);
                    silverFrameLayout.setVisibility(View.GONE);
                    continueScreenBtn.setVisibility(View.VISIBLE);
                    silverFrameImageBtn.setVisibility(View.GONE);
                } else {
                    dentedCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    dentedTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    dentedImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.brokenCard:
                if (broken == false) {
                    broken = true;
                    userSession.setBrokenOfBezel("Broken");
                    brokenCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_7000));
                    brokenImg.setVisibility(View.VISIBLE);

                    silverFrameLayout.setVisibility(View.GONE);
                    continueScreenBtn.setVisibility(View.GONE);
                    silverFrameImageBtn.setVisibility(View.VISIBLE);

                    okay = false;
                    userSession.setOkOfBezel("");
                    okayCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    okayTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    okayImg.setVisibility(View.INVISIBLE);

                    discolored = false;
                    userSession.setDiscoloredOfBezel("");
                    disclosedCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    discoloredTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    discoloredImg.setVisibility(View.INVISIBLE);

                    dented = false;
                    userSession.setDentedOfBezel("");
                    dentedCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    dentedTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    dentedImg.setVisibility(View.INVISIBLE);

                } else if (broken == true) {
                    broken = false;
                    userSession.setBrokenOfBezel("");
                    brokenCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenImg.setVisibility(View.INVISIBLE);
                    silverFrameLayout.setVisibility(View.GONE);
                    continueScreenBtn.setVisibility(View.VISIBLE);
                    silverFrameImageBtn.setVisibility(View.GONE);
                } else {
                    brokenCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenTxt.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    brokenImg.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.continueScreenBtn:
                if (discolored == true || dented == true || broken == true) {
                    JsonObject silverFrame = new JsonObject();
                    silverFrame.addProperty("okay", okay);
                    silverFrame.addProperty("discolored", discolored);
                    silverFrame.addProperty("dented", dented);
                    silverFrame.addProperty("broken", broken);
                    userSession.setSilverFrameBezelJson(silverFrame.toString());
                    System.out.println("Silver frame json :- " + silverFrame);
                    startActivity(new Intent(mContext, MainCameraActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                } else if (okay == true) {
                    JsonObject silverFrame = new JsonObject();
                    silverFrame.addProperty("okay", okay);
                    silverFrame.addProperty("discolored", discolored);
                    silverFrame.addProperty("dented", dented);
                    silverFrame.addProperty("broken", broken);
                    userSession.setSilverFrameBezelJson(silverFrame.toString());
                    System.out.println("Silver frame json :- " + silverFrame);
                    userSession.setThirdImagePath(null);
                    startActivity(new Intent(mContext, MainCameraActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                } else {
                    Toast.makeText(mContext, "Please check any option of silver frame/bezel(Around screen)!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.silverFrameImageBtn:
                if (discolored == true || dented == true || broken == true) {
                    if (silverFrameLayout.getVisibility() == View.GONE) {
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
                    Toast.makeText(mContext, "Please check any option of silver frame/bezel(Around screen)!", Toast.LENGTH_SHORT).show();
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
                    silverFrameImage.setImageURI(photoURI);
                    if (data != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            silverframe = new File(serialPhotoPath);
                        } else {
                            image = (Bitmap) data.getExtras().get("data");
                            silverFrameImage.setImageBitmap(image);
                            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                            image.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                            File imeiSerialImage = new File(Environment.getExternalStorageDirectory(), "silverframe.jpg");
                            silverframe = new File(imeiSerialImage.getAbsolutePath());
                            userSession.setThirdImagePath(silverframe.toString());
                            FileOutputStream fo;
                            try {
                                fo = new FileOutputStream(silverframe);
                                fo.write(bytes.toByteArray());
                                fo.close();
                                userSession.setThirdImagePath(silverframe.toString());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        silverFrameLayout.setVisibility(View.VISIBLE);
                        continueScreenBtn.setVisibility(View.VISIBLE);
                        silverFrameImageBtn.setVisibility(View.GONE);
                    } else {
                        System.out.println("Image Path :- not found!");
                        silverFrameLayout.setVisibility(View.GONE);
                        continueScreenBtn.setVisibility(View.GONE);
                        silverFrameImageBtn.setVisibility(View.VISIBLE);
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