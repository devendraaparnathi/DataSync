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
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.datasync.Api.ApiClient;
import com.example.datasync.Api.ApiInterface;
import com.example.datasync.Pojo.SQLitePojo;
import com.example.datasync.R;
import com.example.datasync.adapter.UserLIstAdapter;
import com.example.datasync.model.SQLiteModelClass;
import com.example.datasync.offline.DBHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewListUser extends AppCompatActivity {

    private ArrayList<SQLiteModelClass> sqLiteModelClassArrayList;
    private DBHandler dbHandler;
    private UserLIstAdapter userLIstAdapter;
    private RecyclerView rvListUser;
    private int size = 0;
    private String name,number,email;

    private Button btnSync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_user);

        LocalBroadcastManager.getInstance(this).
                registerReceiver(broadcastReceiver,new IntentFilter("Message"));

        rvListUser = findViewById(R.id.rvListUser);

        sqLiteModelClassArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewListUser.this);

        sqLiteModelClassArrayList = dbHandler.readDetails();

        userLIstAdapter = new UserLIstAdapter(sqLiteModelClassArrayList, null);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewListUser.this, RecyclerView.VERTICAL, false);

        rvListUser.setLayoutManager(linearLayoutManager);
        rvListUser.setAdapter(userLIstAdapter);

        btnSync = findViewById(R.id.btnSync);
        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface retrofitAPI = ApiClient.getRetrofit().create(ApiInterface.class);

                try {
                    JSONObject paramObj = new JSONObject();
                    paramObj.put("contactName",name);
                    paramObj.put("emailId",email);
                    paramObj.put("mobileNo",number);

                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(paramObj);

                    JSONObject json = new JSONObject();
                    json.put("contactList",jsonArray);

                    Call<SQLitePojo> call = retrofitAPI.postData(json);
                    call.enqueue(new Callback<SQLitePojo>() {
                        @Override
                        public void onResponse(Call<SQLitePojo> call, Response<SQLitePojo> response) {
                            Toast.makeText(ViewListUser.this, "Success", Toast.LENGTH_SHORT).show();
                            Log.d("PRABHUDHAN", "onResponse: " + json);
                        }

                        @Override
                        public void onFailure(Call<SQLitePojo> call, Throwable t) {
                            Toast.makeText(ViewListUser.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            name= intent.getStringExtra("mName");
            number= intent.getStringExtra("mNumber");
            email= intent.getStringExtra("mEmail");

        }
    };
}