package com.example.datasync;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.datasync.Api.ApiClient;
import com.example.datasync.Api.ApiInterface;
import com.example.datasync.model.SQLiteModelClass;
import com.example.datasync.offline.DBHandler;
import com.example.datasync.ui.ViewListUser;
import com.example.datasync.util.ConnectionReceiver;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ConnectionReceiver.ReceiverListener {

    private EditText etName, etNumber, etEmail;
    private Button btnAdd, btnListData;
    private DBHandler dbHandler;

    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkConnection();

        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);
        etEmail = findViewById(R.id.etEmail);
        btnListData = findViewById(R.id.btnListData);

        btnAdd = findViewById(R.id.btnAdd);

        dbHandler = new DBHandler(MainActivity.this);

        btnListData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewListUser.class));
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createUserData(etName.getText().toString(),etNumber.getText().toString(),etEmail.getText().toString());

                String name = etName.getText().toString();
                String number = etNumber.getText().toString();
                String email = etEmail.getText().toString();

                if (name.isEmpty() && number.isEmpty() && email.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter Details", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.addNewUSer(name, number, email);

                Toast.makeText(MainActivity.this, "Add Successfully", Toast.LENGTH_SHORT).show();

                etName.setText("");
                etEmail.setText("");
                etNumber.setText("");
            }
        });
    }

    private void createUserData(String name, String number, String email) {

        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

        SQLiteModelClass model = new SQLiteModelClass(name,number,email);

        retrofit2.Call<SQLiteModelClass> call = apiInterface.createUser(model);

        call.enqueue(new Callback<SQLiteModelClass>() {
            @Override
            public void onResponse(Call<SQLiteModelClass> call, Response<SQLiteModelClass> response) {
                Toast.makeText(MainActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SQLiteModelClass> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void checkConnection() {

        intentFilter = new IntentFilter();

        intentFilter.addAction("android.new.conn.CONNECTIVITY_CHANGE");

        registerReceiver(new ConnectionReceiver(), intentFilter);

        ConnectionReceiver.Listener = this;

        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();

        showSnackBar(isConnected);

    }

    private void showSnackBar(boolean isConnected) {

        String message;
        int color;

        if (isConnected) {

            message = "Internet Available";

            color = Color.WHITE;

        } else {

            message = "Network not Available";

            color = Color.RED;
        }

        Snackbar snackbar = Snackbar.make(getWindow().getDecorView().getRootView(), message, Snackbar.LENGTH_LONG);

        View view = snackbar.getView();

        TextView textView = view.findViewById(R.id.snackbar_text);

        textView.setTextColor(color);

        snackbar.show();
    }

    @Override
    public void onNetworkChange(boolean isConnected) {
        showSnackBar(isConnected);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkConnection();
    }

    @Override
    protected void onPause() {
        super.onPause();
        checkConnection();
    }
}