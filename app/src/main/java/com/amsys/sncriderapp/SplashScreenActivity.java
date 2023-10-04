package com.amsys.sncriderapp;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.TransitionInflater;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.amsys.sncriderapp.Activities.DashboardActivity;
import com.amsys.sncriderapp.Activities.LoginActivity;
import com.amsys.sncriderapp.Utilities.UserSession;

public class SplashScreenActivity extends AppCompatActivity {

    private Context mContext;
    public static int SPLASH_TIME_OUT = 2000;
    private UserSession userSession;
    private static final int PERMISSION_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mContext = this;
        userSession = new UserSession(mContext);

        getRunTimePermission();

    }

    private void getRunTimePermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            if (checkPermission()) {
                setupWindowAnimations();
                getPermissionGotoNextPage();
            } else {
                requestPermission();
            }
        } else {
            System.out.println("Permission already granted below android version 10!");
            setupWindowAnimations();
            getPermissionGotoNextPage();
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(mContext, ACCESS_FINE_LOCATION);
        int result1 = ContextCompat.checkSelfPermission(mContext, CAMERA);
        int result2 = ContextCompat.checkSelfPermission(mContext, READ_EXTERNAL_STORAGE);
        int result3 = ContextCompat.checkSelfPermission(mContext, WRITE_EXTERNAL_STORAGE);

        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED &&
                result2 == PackageManager.PERMISSION_GRANTED && result3 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions((Activity) mContext, new String[]{ACCESS_FINE_LOCATION, CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    private void getPermissionGotoNextPage() {
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {
                try {
                    gotoNextPage();
                } catch (Exception e) {
                    e.getStackTrace();
                    System.out.println("SplashException :- " + e.getMessage());
                }
            }
        }, (long) SPLASH_TIME_OUT);
    }

    private void gotoNextPage() {
        try {
            if (userSession.isLoggedIn()) {
                startActivity(new Intent(mContext, DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                SplashScreenActivity.this.finish();
            } else {
                startActivity(new Intent(mContext, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                SplashScreenActivity.this.finish();
            }
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }


    private void setupWindowAnimations() {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().setEnterTransition(TransitionInflater.from(mContext).inflateTransition(R.transition.slide_from_left));
            }
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                try {
                    if (grantResults.length > 0) {
                        boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                        boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                        boolean storageReadAccepted = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                        boolean storageWriteAccepted = grantResults[3] == PackageManager.PERMISSION_GRANTED;

                        if (locationAccepted && cameraAccepted && storageReadAccepted && storageWriteAccepted) {
                            setupWindowAnimations();
                            getPermissionGotoNextPage();
                        } else {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                                    showMessageOKCancel("You need to allow access to both the permissions",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                        requestPermissions(new String[]{ACCESS_FINE_LOCATION, CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE},
                                                                PERMISSION_REQUEST_CODE);
                                                    }
                                                }
                                            });
                                    return;
                                }
                            }
                        }
                    }
                } catch (Exception exp) {
                    exp.getStackTrace();
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        try {
            new AlertDialog.Builder(mContext).setMessage(message)
                    .setPositiveButton("OK", okListener)
                    .setNegativeButton("Cancel", null)
                    .create()
                    .show();
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }
}