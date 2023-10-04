package com.amsys.sncriderapp.Activities;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.amsys.sncriderapp.ApiCallService;
import com.amsys.sncriderapp.R;
import com.amsys.sncriderapp.ResponseModel.OrderCountResponse;
import com.amsys.sncriderapp.Utilities.ApiNetworkClient;
import com.amsys.sncriderapp.Utilities.GPSTracker;
import com.amsys.sncriderapp.Utilities.NetworkStatus;
import com.amsys.sncriderapp.Utilities.UserSession;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements LocationListener, View.OnClickListener {

    private Context mContext;
    private UserSession userSession;
    private ImageView userProfile_Img, deviceLeadImg, orderImg;
    private LinearLayout deviceLeads_Lt, completeOrder_Lt;
    private TextView deviceLeadTxt, completeOrderTxt, riderCurrentLocation;
    private static final int MY_CAMERA_PERMISSION_CODE = 12345;
    private double latitude, longitude;
    private String address, city, state, country, knownName, postalCode, riderLocation = "";
    private GPSTracker gps;
    private Runnable runnable;
    private int openLeads;
    private static final int PERMISSION_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mContext = this;
        userSession = new UserSession(mContext);
        gps = new GPSTracker(mContext);

        getLayoutDashboardUiIdFind();

        deviceLeads_Lt.setOnClickListener(this);
        completeOrder_Lt.setOnClickListener(this);
        userProfile_Img.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (checkPermission()) {
                System.out.println("Permission already granted!");
            } else {
                requestPermission();
            }
        } else {
            System.out.println("Permission already granted before android version 10!");
        }

        System.out.println("Token key :- " + userSession.getToken());

        if (NetworkStatus.isNetworkAvailable(mContext)) {
            getCountOfOpenAcceptedLeads(userSession.getUserId());
        } else {
            Toast.makeText(mContext, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getCountOfOpenAcceptedLeads(int userId) {
        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        try {
            ApiCallService apiClient = ApiNetworkClient.getSncRiderRetrofit().create(ApiCallService.class);
            Call<OrderCountResponse> call = apiClient.getCountOrderNumber(userId);
            call.enqueue(new Callback<OrderCountResponse>() {
                @Override
                public void onResponse(Call<OrderCountResponse> call, Response<OrderCountResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatusCode() == 200) {
                            if (response.body().getData() != null) {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                openLeads = response.body().getData().getTotalOpenOrders();
                                deviceLeadTxt.setText(response.body().getData().getTotalOpenOrders().toString());
                                completeOrderTxt.setText(response.body().getData().getTotalConfirmedOrders().toString());
                            } else {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                deviceLeadTxt.setText("0");
                                completeOrderTxt.setText("0");
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
                public void onFailure(Call<OrderCountResponse> call, Throwable t) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    System.out.println("Throwable :- " + t.getStackTrace());
                }
            });
        } catch (Exception exp) {
            exp.getStackTrace();
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    private void getLayoutDashboardUiIdFind() {
        try {
            userProfile_Img = (ImageView) findViewById(R.id.userProfile_Img);
            deviceLeadImg = (ImageView) findViewById(R.id.deviceLeadImg);
            orderImg = (ImageView) findViewById(R.id.orderImg);
            deviceLeads_Lt = (LinearLayout) findViewById(R.id.deviceLeads_Lt);
            completeOrder_Lt = (LinearLayout) findViewById(R.id.completeOrder_Lt);
            deviceLeadTxt = (TextView) findViewById(R.id.deviceLeadTxt);
            completeOrderTxt = (TextView) findViewById(R.id.completeOrderTxt);
            riderCurrentLocation = (TextView) findViewById(R.id.riderCurrentLocation);

//            checkCurrentAddressOfUserStatus();

        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.deviceLeads_Lt:
                startActivity(new Intent(mContext, DeviceLeadsActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                break;

            case R.id.completeOrder_Lt:
                startActivity(new Intent(mContext, CompleteOrderActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                break;

            case R.id.userProfile_Img:
                startActivity(new Intent(mContext, UserProfileActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
        }
    }

    public void checkCurrentAddressOfUserStatus() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    getCurrentAddressOfUser();
                }
            });
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, final int id) {
                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, final int id) {
                dialog.cancel();
            }
        });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private void getCurrentAddressOfUser() {
        try {
            gps = new GPSTracker(mContext);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (mContext.checkSelfPermission(ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{ACCESS_FINE_LOCATION}, MY_CAMERA_PERMISSION_CODE);
                    } else {    // Check if GPS enabled
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (gps.canGetLocation()) {
                                    Location location = gps.getLocation();
                                    latitude = gps.getLatitude();
                                    longitude = gps.getLongitude();
                                    if (location != null) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                            getAddress(mContext, latitude, longitude);
                                        }
                                    }
                                } else {
                                    gps.showSettingsAlert();
                                }
                            }
                        });
                    }
                }
            }
        } catch (Exception exp) {
            exp.getStackTrace();
        }
    }

    public void getAddress(Context context, double LATITUDE, double LONGITUDE) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (addresses != null && addresses.size() > 0) {
            address = addresses.get(0).getAddressLine(0);
            city = addresses.get(0).getLocality();
            state = addresses.get(0).getAdminArea();
            country = addresses.get(0).getCountryName();
            postalCode = addresses.get(0).getPostalCode();
            knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
            String riderAddress = ", " + state + " " + postalCode + ", " + country;
            Log.d("Address list :- ", String.valueOf(addresses.size()));

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (!address.equalsIgnoreCase("") || !(address == null)) {
                        riderLocation = address;
                        System.out.println("Address by Network or GPS :- " + riderLocation);
                        System.out.println("State, Pin Code and Country by Network or GPS :- " + riderAddress);
                        String actualAddress = riderLocation.replaceAll("" + riderAddress, "");
                        System.out.println("Actual Address by Network or GPS :- " + actualAddress);
                        riderCurrentLocation.setText(actualAddress);
                        userSession.setCurrentAddress(riderLocation);
                    } else {
                        riderCurrentLocation.setText("N/A");
                        System.out.println("Address by Network or GPS :- " + riderLocation);
                        userSession.setCurrentAddress(riderLocation);
                    }
                    refreshCurrentAddressOfUser(10000);
                }
            });
        }
        return;
    }

    private void refreshCurrentAddressOfUser(int milliseconds) {
        Handler handler = new Handler(mContext.getMainLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                getCurrentAddressOfUser();
            }
        };
        handler.postDelayed(runnable, milliseconds);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        double speed = location.getSpeed();
        speed = (speed * 3600) / 1000;
        if (location != null) {
            getAddress(mContext, latitude, longitude);
        } else {
            System.out.println("Address not found");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {    // Code for location of Current Address
                getCurrentAddressOfUser();
                gps.getLocation();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        gps.stopUsingGPS();
    }

    @Override
    protected void onStop() {
        super.onStop();
        gps.stopUsingGPS();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gps.stopUsingGPS();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        gps.stopUsingGPS();
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
                            System.out.println("Permission Granted, Now you can access location data, camera and storage.");
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