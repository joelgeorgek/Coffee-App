package com.example.user.mycoffeeapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    TextView NoOrderTextView;
    RecyclerView recyclerView;

    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NoOrderTextView = findViewById(R.id.noOrderText);
        recyclerView = findViewById(R.id.OrdersRecyclerView);
        myAdapter = new MyAdapter(getApplicationContext(),NoOrderTextView);
        DBHelper helper =new DBHelper(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(myAdapter);
        if(helper.numberOfRows()>0){
            NoOrderTextView.setVisibility(View.GONE);
        }else{
            NoOrderTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_order_options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.AddOrderMenuButton){
            Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
