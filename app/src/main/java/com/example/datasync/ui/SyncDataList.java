package com.example.datasync.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.datasync.Api.ApiClient;
import com.example.datasync.Api.ApiInterface;
import com.example.datasync.R;
import com.example.datasync.Pojo.UserDataPojo;
import com.example.datasync.adapter.SyncDataListAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncDataList extends AppCompatActivity {

    private SyncDataListAdapter syncDataListAdapter;
    private RecyclerView rvSyncDataList;
    private ArrayList<UserDataPojo> pojoUserArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_data_list);

        rvSyncDataList = findViewById(R.id.rvSyncDataList);

        pojoUserArrayList = new ArrayList<>();

        showData();
    }

    private void showData() {

        ApiInterface retrofitAPI = ApiClient.getRetrofit().create(ApiInterface.class);

        Call<ArrayList<UserDataPojo>> call = retrofitAPI.getDataList();

        call.enqueue(new Callback<ArrayList<UserDataPojo>>() {
            @Override
            public void onResponse(Call<ArrayList<UserDataPojo>> call, Response<ArrayList<UserDataPojo>> response) {
                Log.d("BHAKTI", "SUCCESS: ");

                pojoUserArrayList = response.body();

                for (int i=0; i<pojoUserArrayList.size();i++)
                {
                    syncDataListAdapter = new SyncDataListAdapter(pojoUserArrayList,SyncDataList.this);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(SyncDataList.this, RecyclerView.VERTICAL, false);
                    rvSyncDataList.setLayoutManager(layoutManager);
                    rvSyncDataList.setAdapter(syncDataListAdapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UserDataPojo>> call, Throwable t) {
                Log.d("BHAKTI", "FAIL: ");
            }
        });
    }
}