package com.com.hamsoft.captaincook.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.com.hamsoft.captaincook.Interface.ItemClickListener;
import com.com.hamsoft.captaincook.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hamsoft technologies
 */

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.food_name)
    public TextView food_name;

    @BindView(R.id.food_price)
    public TextView food_price;

    @BindView(R.id.food_image)
    public ImageView food_image;

    @BindView(R.id.btn_quick_cart)
    public ImageView quick_cart;

    private ItemClickListener itemClickListener;

    public FoodViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
