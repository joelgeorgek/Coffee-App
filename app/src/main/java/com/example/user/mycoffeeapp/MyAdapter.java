package com.example.user.mycoffeeapp;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.service.autofill.Dataset;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    DBHelper MyDB;
    Context context;
    TextView NoOrderTextView;
    ArrayList<ArrayList<String>> MyDataset = new ArrayList<>();
    public MyAdapter(Context context,TextView textView) {
        this.NoOrderTextView=textView;
        this.context=context;
        MyDB = new DBHelper(context);
        MyDataset = getDataset(MyDB);

    }

    public ArrayList<ArrayList<String>> getDataset(DBHelper helper){
            ArrayList<ArrayList<String>> Dataset = new ArrayList<>();
            Cursor cursor = MyDB.getData();
            if(cursor.moveToFirst()) {
                Dataset.add(new ArrayList<String>());
                Dataset.add(new ArrayList<String>());
                Dataset.add(new ArrayList<String>());
                Dataset.add(new ArrayList<String>());
                do {
                    final int Number = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_NUMBER));
                    String Name = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME)) + "";
                    String Count = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_COUNT)) + "";
                    String Cost = "Cost - $" + cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PRICE)) + "";
                    Dataset.get(0).add(Number + "");
                    Dataset.get(1).add(Name);
                    Dataset.get(2).add(Count);
                    Dataset.get(3).add(Cost);
                    Log.e("HAHA", "Name : " + Name + "\nNumber : " + Number + "\nCount : " + Count + "\nCost : " + Cost);
                }while(cursor.moveToNext());
            }else {
                Dataset = new ArrayList<>();
            }
        return Dataset;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_recycler_item_layout,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
                final String Number = MyDataset.get(0).get(position);
                String Name = MyDataset.get(1).get(position);
                String Count = MyDataset.get(2).get(position);
                String Cost = MyDataset.get(3).get(position);

                //Log.e("HAHA", "Name : " + Name + "\nNumber : " + Number + "\nCount : " + Count + "\nCost : " + Cost);

                holder.OrderName.setText(Name);
                holder.OrderNumber.setText("Order "+(position+1));
                holder.OrderCount.setText(Count);
                holder.OrderCost.setText(Cost);

                holder.RemoveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyDB.deleteOrder(Integer.parseInt(Number));
                        MyDataset = getDataset(MyDB);
                        MyAdapter.this.notifyDataSetChanged();
                        if(MyDataset.size()==0){
                            NoOrderTextView.setVisibility(View.VISIBLE);
                        }else{
                            NoOrderTextView.setVisibility(View.GONE);
                        }

                    }
                });

                //holder.view.setLayoutParams(new ViewGroup.LayoutParams(0,0));
    }

    @Override
    public int getItemCount() {
        return MyDB.numberOfRows();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        View view;
        TextView OrderName;
        TextView OrderCount;
        TextView OrderNumber;
        TextView OrderCost;
        Button RemoveButton;
        public MyViewHolder(View itemView) {
            super(itemView);
            view =itemView;
            OrderNumber = itemView.findViewById(R.id.orderNumberText);
            OrderName = itemView.findViewById(R.id.orderNameText);
            OrderCount = itemView.findViewById(R.id.orderCountText);
            OrderCost = itemView.findViewById(R.id.orderPriceText);
            RemoveButton = itemView.findViewById(R.id.button_remove);
        }
    }

}
