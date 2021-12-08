package com.example.datasync.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_user);

        rvListUser = findViewById(R.id.rvListUser);

        sqLiteModelClassArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewListUser.this);

        sqLiteModelClassArrayList = dbHandler.readDetails();

        userLIstAdapter = new UserLIstAdapter(sqLiteModelClassArrayList, null);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewListUser.this, RecyclerView.VERTICAL, false);
        rvListUser.setLayoutManager(linearLayoutManager);
        rvListUser.setAdapter(userLIstAdapter);

    }
}