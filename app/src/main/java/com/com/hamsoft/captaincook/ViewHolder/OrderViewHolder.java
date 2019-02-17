package com.com.hamsoft.captaincook.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.com.hamsoft.captaincook.Interface.ItemClickListener;
import com.com.hamsoft.captaincook.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hamsoft technologies
 */

public class OrderViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

    @BindView(R.id.order_id)
    public TextView txtOrderId;

    @BindView(R.id.order_phone)
    public TextView txtOrderPhone;

    @BindView(R.id.order_address)
    public TextView txtOrderAddress;

    @BindView(R.id.order_status)
    public TextView txtOrderStatus;

    private ItemClickListener itemClickListener;

    public OrderViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }
}
