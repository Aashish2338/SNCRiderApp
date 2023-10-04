package com.amsys.sncriderapp.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amsys.sncriderapp.R;

public class DeviceLeadsViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout showDetails;
    public ImageView deviceImage;
    public TextView orderId, deviceDetailsName, tradersName, addressLocation;

    public DeviceLeadsViewHolder(@NonNull View itemView) {
        super(itemView);
        showDetails = (LinearLayout) itemView.findViewById(R.id.showDetails);
        deviceImage = (ImageView) itemView.findViewById(R.id.deviceImage);
        orderId = (TextView) itemView.findViewById(R.id.orderId);
        deviceDetailsName = (TextView) itemView.findViewById(R.id.deviceDetailsName);
        tradersName = (TextView) itemView.findViewById(R.id.tradersName);
        addressLocation = (TextView) itemView.findViewById(R.id.addressLocation);
    }
}
