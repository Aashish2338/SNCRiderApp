package com.amsys.sncriderapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amsys.sncriderapp.Activities.DeviceLeadDetailsActivity;
import com.amsys.sncriderapp.Model.RiderOrderConfirmPickupData;
import com.amsys.sncriderapp.R;
import com.amsys.sncriderapp.Utilities.UserSession;
import com.amsys.sncriderapp.ViewHolders.CompleteOrderViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CompleteOrderAdapter extends RecyclerView.Adapter<CompleteOrderViewHolder> {

    private Context mContext;
    private UserSession userSession;
    private List<RiderOrderConfirmPickupData> data;

    public CompleteOrderAdapter(Context mContext, List<RiderOrderConfirmPickupData> data) {
        this.mContext = mContext;
        this.data = data;
        userSession = new UserSession(mContext);

    }

    @NonNull
    @Override
    public CompleteOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.complete_order_item_layout, parent, false);
        return new CompleteOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompleteOrderViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if (data.get(position).getOrderNumber() != null) {
            holder.orderId.setText(data.get(position).getOrderNumber());
        } else {
            holder.orderId.setText("N/A");
        }
        holder.productName.setText(data.get(position).getProductName());
        holder.endUserName.setText(data.get(position).getFullName());

        String input = data.get(position).getCompleteAddress();
        String output = input.substring(0, 1).toLowerCase() + input.substring(1);
        holder.address.setText(output);

        Picasso.get().load(data.get(position).getProductImgUrl1()).error(R.drawable.no_image_available).into(holder.deviceImage, new Callback() {
            @Override
            public void onSuccess() {
                Log.d("TAG", "onSuccess");
            }

            @Override
            public void onError(Exception exception) {
                System.out.println("Image Loading Error :- " + exception.getMessage());
            }
        });

//        Glide.with(mContext).load("http://images.xtracover.com/APPLE_APPLE-IPHONE-11--PURPLE--128-GB-_9828.JPG").into(holder.deviceImage);
//        Glide.with(mContext).load(data.get(position).getProductImgUrl1().replaceAll("https", "http")).into(holder.deviceImage);

        holder.showDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSession.setOrderId(data.get(position).getOrderId());
                userSession.setExactPrice(data.get(position).getExactPriceByEndUser());
                userSession.setDistrictId(data.get(position).getDistrictId());
                userSession.setPageDirectionStatus("Complete Leads");
                mContext.startActivity(new Intent(mContext, DeviceLeadDetailsActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
