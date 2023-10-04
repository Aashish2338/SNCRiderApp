package com.amsys.sncriderapp.Utilities;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiNetworkClient {

    private static final String Base_Url = "http://103.251.94.83:82/";
    private static Retrofit retrofit = null;

   /* public static Retrofit getSncRiderRetrofitWithToken(String token) {
        if (retrofit == null) {
            Interceptor interceptorLogin = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest = chain.request().newBuilder().addHeader("Authorization", token).build();
                    return chain.proceed(newRequest);
                }
            };

            CookieHandler cookieHandlerLogin = new CookieManager();
            OkHttpClient clientLogin = new OkHttpClient.Builder()
                    .addNetworkInterceptor(interceptorLogin)
                    .cookieJar(new JavaNetCookieJar(cookieHandlerLogin))
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder().baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(clientLogin).build();
        }
        return retrofit;
    }*/

    public static Retrofit getSncRiderRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptorLogin = new HttpLoggingInterceptor();
            interceptorLogin.setLevel(HttpLoggingInterceptor.Level.BODY);

            CookieHandler cookieHandlerLogin = new CookieManager();
            OkHttpClient clientLogin = new OkHttpClient.Builder().addNetworkInterceptor(interceptorLogin).cookieJar(new JavaNetCookieJar(cookieHandlerLogin)).connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();

            retrofit = new Retrofit.Builder().baseUrl(Base_Url).addConverterFactory(GsonConverterFactory.create()).client(clientLogin).build();
        }
        return retrofit;
    }
}