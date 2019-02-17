package com.com.hamsoft.captaincook.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.com.hamsoft.captaincook.Model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hamsoft technologies
 */

public class Database extends SQLiteAssetHelper{

    private static final String DB_NAME = "EatItDB.db";
    private static final int DB_VER = 1;

    public Database(Context context) {
        super(context, DB_NAME,null, DB_VER);
    }

    public List<Order> getCart() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"ID","ProductId", "ProductName", "Quantity", "Price", "Discount"};
        String sqlTable = "OrderDetail";

        qb.setTables(sqlTable);
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        List<Order> result = new ArrayList<>();
        cursor.moveToFirst();

        int ID_index= cursor.getColumnIndex(sqlSelect[0]);
        int ProductId_index = cursor.getColumnIndex(sqlSelect[1]);
        int ProductName_index = cursor.getColumnIndex(sqlSelect[2]);
        int Quantity_index = cursor.getColumnIndex(sqlSelect[3]);
        int Price_index = cursor.getColumnIndex(sqlSelect[4]);
        int Discount_index = cursor.getColumnIndex(sqlSelect[5]);


        while (!cursor.isAfterLast()) {

            result.add(new Order(cursor.getString(ID_index)
                    , cursor.getString(ProductId_index)
                    , cursor.getString(ProductName_index)
                    , cursor.getString(Quantity_index)
                    , cursor.getString(Price_index)
                    , cursor.getString(Discount_index)));

            cursor.moveToNext();
        }
        return result;
    }

    public void addToCart(Order order) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDetail(ProductId, ProductName,Quantity, Price, Discount) VALUES('%s', '%s', '%s', '%s', '%s');"
        , order.getProductId()
        , order.getProductName()
        , order.getQuantity()
        , order.getPrice()
        , order.getDiscount());

        db.execSQL(query);
    }

    public void cleanToCart() {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetail");

        db.execSQL(query);
    }

    public int getCountCart() {
        int count=0;
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("SELECT COUNT(*) FROM OrderDetail");
        Cursor cursor =db.rawQuery(query, null);
        if (cursor.moveToFirst())
        {
            do {
                count = cursor.getInt(0);
            }while (cursor.moveToNext());
        }
        return count;
    }

    public void updateCart(Order order) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("UPDATE OrderDetail set Quantity= %s WHERE ID = %d", order.getQuantity(),order.getID() );
        db.execSQL(query);
    }

    //public void setCountCart(int countCart) {
   //     this.countCart = countCart;
    //}
}
