package com.amsys.sncriderapp.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amsys.sncriderapp.R;

public class CompleteOrderViewHolder extends RecyclerView.ViewHolder {

    public ImageView deviceImage;
    public TextView orderId, productName, endUserName, address;
    public LinearLayout showDetails;

    public CompleteOrderViewHolder(@NonNull View itemView) {
        super(itemView);

        deviceImage = (ImageView) itemView.findViewById(R.id.deviceImage);
        orderId = (TextView) itemView.findViewById(R.id.orderId);
        productName = (TextView) itemView.findViewById(R.id.productName);
        endUserName = (TextView) itemView.findViewById(R.id.endUserName);
        address = (TextView) itemView.findViewById(R.id.address);
        showDetails = (LinearLayout) itemView.findViewById(R.id.showDetails);

    }
}
