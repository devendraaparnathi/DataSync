package com.example.datasync.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.example.datasync.R;
import com.example.datasync.adapter.UserLIstAdapter;
import com.example.datasync.model.SQLiteModelClass;
import com.example.datasync.offline.DBHandler;

import java.util.ArrayList;

public class ViewListUser extends AppCompatActivity {

    private ArrayList<SQLiteModelClass> sqLiteModelClassArrayList;
    private DBHandler dbHandler;
    private UserLIstAdapter userLIstAdapter;
    private RecyclerView rvListUser;
    private int size = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_user);

        LocalBroadcastManager.getInstance(this).
        registerReceiver(broadcastReceiver,new IntentFilter("PRIMARY"));

        rvListUser = findViewById(R.id.rvListUser);

        sqLiteModelClassArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewListUser.this);

        sqLiteModelClassArrayList = dbHandler.readDetails();

        userLIstAdapter = new UserLIstAdapter(sqLiteModelClassArrayList, null);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewListUser.this, RecyclerView.VERTICAL, false);

        rvListUser.setLayoutManager(linearLayoutManager);
        rvListUser.setAdapter(userLIstAdapter);

    }

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            size = intent.getIntExtra("SIZE",0);
            Log.d("DEVENDRA", "onReceive: " + size);

        }
    };
}