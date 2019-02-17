package com.com.hamsoft.captaincook.ViewHolder;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.com.hamsoft.captaincook.Common.Common;
import com.com.hamsoft.captaincook.Database.Database;
import com.com.hamsoft.captaincook.Model.Order;
import com.com.hamsoft.captaincook.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hamsoft Technologies
 */

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder>{

    private List<Order> listData = new ArrayList<>();
    private Context context;

    public CartAdapter(List<Order> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.cart_layout, parent, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, final int position) {
        //TextDrawable drawable = TextDrawable.builder()
       //         .buildRound("" + listData.get(position).getQuantity(), Color.RED);
       // holder.img_cart_count.setImageDrawable(drawable);

        holder.btn_quantity.setNumber(listData.get(position).getQuantity());
        holder.btn_quantity.setOnValueChangeListener(new
                                                             ElegantNumberButton.OnValueChangeListener() {
                                                                 @Override
                                                                 public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                                                                     Order order= listData.get(position);
                                                                     order.setQuantity(String.valueOf(newValue));
                                                                     new Database(context).updateCart(order);


                                                                 }
                                                             });



        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        int price = (Integer.parseInt(listData.get(position).getPrice())) * (Integer.parseInt(listData.get(position).getQuantity()));
        holder.txt_cart_price.setText(fmt.format(price));
        holder.txt_cart_name.setText(listData.get(position).getProductName());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    , View.OnCreateContextMenuListener{

    @BindView(R.id.cart_item_name)
    public TextView txt_cart_name;

    @BindView(R.id.cart_item_Price)
    public TextView txt_cart_price;

    @BindView(R.id.btn_quantity)
    public ElegantNumberButton btn_quantity;

    public CartViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
        itemView.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select Action");
        menu.add(0,0, getAdapterPosition(), Common.DELETE);

    }
}

