package com.amsys.sncriderapp;

import com.amsys.sncriderapp.ResponseModel.AddressResponse;
import com.amsys.sncriderapp.ResponseModel.CompleteOrderDetailResponse;
import com.amsys.sncriderapp.ResponseModel.ConfirmOrderResponse;
import com.amsys.sncriderapp.ResponseModel.DistrictsReponse;
import com.amsys.sncriderapp.ResponseModel.ExactPriceResponse;
import com.amsys.sncriderapp.ResponseModel.LoginResponse;
import com.amsys.sncriderapp.ResponseModel.OrderCountResponse;
import com.amsys.sncriderapp.ResponseModel.OtpResponse;
import com.amsys.sncriderapp.ResponseModel.RiderOrderConfirmPickupResponse;
import com.amsys.sncriderapp.ResponseModel.StateResponse;
import com.amsys.sncriderapp.ResponseModel.UploadDeviceImagesResponse;
import com.amsys.sncriderapp.ResponseModel.UserProfileResponse;
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiCallService {

    @POST("RiderApi/UserManagement/ValidateUser")
    Call<UserProfileResponse> getValidateUser(@Body JsonObject jsonObject);

    @POST("RiderApi/UserManagement/SendOtp")
    Call<OtpResponse> getOtp(@Body JsonObject jsonObject);

    @POST("RiderApi/UserManagement/Login")
    Call<LoginResponse> getLoginData(@Body JsonObject jsonObject);

    @GET("MobileApi/Location/GetAllStates")
    Call<StateResponse> getAllStates();

    @GET("MobileApi/Location/GetDistricts")
    Call<DistrictsReponse> getAllDistricts(@Query("stateId") int stateId);

    @Headers({"Accept: application/json"})
    @POST("RiderApi/UserManagement/AddUserAddress")
    Call<UserProfileResponse> addUserAddress(@Body JsonObject jsonObject, @Header("Authorization") String token);

    @GET("RiderApi/UserManagement/GetUserAddresses")
    Call<AddressResponse> getUserAddresses(@Query("mobileNo") String mobileNumber, @Header("Authorization") String token);

    @GET("RiderApi/UserManagement/GetUserProfile")
    Call<UserProfileResponse> getUserProfile(@Query("mobileNo") String mobileNumber, @Header("Authorization") String token);

    @GET("RiderApi/Order/GetOrderCounts")
    Call<OrderCountResponse> getCountOrderNumber(@Query("riderId") int riderId);

    @GET("RiderApi/Order/GetRiderOrderlistForConfirmPickup")
    Call<RiderOrderConfirmPickupResponse> getRiderOrderListPickup();

    @GET("RiderApi/Order/GetCompleteOrderDetail")
    Call<CompleteOrderDetailResponse> getCompleteOrderDetails(@Query("orderId") String userId);

    @POST("RiderApi/Order/ConfirmedSchedulePickup")
    Call<CompleteOrderDetailResponse> getConfirmedSchedulePickup(@Body JsonObject jsonObject);

    @GET("RiderApi/Order/GetConfirmedPickupRiderOrderlist")
    Call<RiderOrderConfirmPickupResponse> getConfirmedPickupRiderOrderDetail(@Query("riderId") int userId);

    @POST("RiderApi/Pricing/GetExactPrice")
    Call<ExactPriceResponse> getExactPrice(@Body JsonObject jsonObject);

    @POST("RiderApi/Order/RejectOrder")
    Call<ConfirmOrderResponse> getRejectOrder(@Body JsonObject jsonObject);

    @POST("RiderApi/Order/ConfirmOrder")
    Call<ConfirmOrderResponse> getConfirmOrder(@Body JsonObject jsonObject);

    @Multipart
    @POST("RiderApi/Order/UploadDeviceImages")
    Call<UploadDeviceImagesResponse> getUploadDeviceImagesA(@Header("Authorization") String token, @Part("CurrentRiderLocation") RequestBody currentRiderLocation, @Part("OrderId") RequestBody orderID, @Part MultipartBody.Part Image1, @Part MultipartBody.Part Image2, @Part MultipartBody.Part Image3);

    @Multipart
    @POST("RiderApi/Order/UploadDeviceImages")
    Call<UploadDeviceImagesResponse> getUploadDeviceImagesB(@Header("Authorization") String token, @Part("CurrentRiderLocation") RequestBody currentRiderLocation, @Part("OrderId") RequestBody orderID, @Part MultipartBody.Part Image1, @Part MultipartBody.Part Image2, @Part MultipartBody.Part Image3, @Part MultipartBody.Part DisplayTouchScreen);

    @Multipart
    @POST("RiderApi/Order/UploadDeviceImages")
    Call<UploadDeviceImagesResponse> getUploadDeviceImagesC(@Header("Authorization") String token, @Part("CurrentRiderLocation") RequestBody currentRiderLocation, @Part("OrderId") RequestBody orderID, @Part MultipartBody.Part Image1, @Part MultipartBody.Part Image2, @Part MultipartBody.Part Image3, @Part MultipartBody.Part DisplayTouchScreen, @Part MultipartBody.Part DeviceBodyBackPanel);

    @Multipart
    @POST("RiderApi/Order/UploadDeviceImages")
    Call<UploadDeviceImagesResponse> getUploadDeviceImagesD(@Header("Authorization") String token, @Part("CurrentRiderLocation") RequestBody currentRiderLocation, @Part("OrderId") RequestBody orderID, @Part MultipartBody.Part Image1, @Part MultipartBody.Part Image2, @Part MultipartBody.Part Image3, @Part MultipartBody.Part DisplayTouchScreen, @Part MultipartBody.Part DeviceBodyBackPanel, @Part MultipartBody.Part SilverFrame);


    @Multipart
    @POST("RiderApi/Order/UploadDeviceImages")
    Call<UploadDeviceImagesResponse> getUploadDeviceImagesE(@Header("Authorization") String token, @Part("CurrentRiderLocation") RequestBody currentRiderLocation, @Part("OrderId") RequestBody orderID, @Part MultipartBody.Part Image1, @Part MultipartBody.Part Image2, @Part MultipartBody.Part Image3, @Part MultipartBody.Part DisplayTouchScreen, @Part MultipartBody.Part DeviceBodyBackPanel, @Part MultipartBody.Part SilverFrame, @Part MultipartBody.Part MainCamera);
}