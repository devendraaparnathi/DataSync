package com.example.datasync.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.datasync.R;
import com.example.datasync.offline.DBHandler;

public class UpdateDetails extends AppCompatActivity {

    private EditText etName, etNumber, etEmail;
    private Button btnUpdate, btnDelete;
    private DBHandler dbHandler;
    String name, number, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);

        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);
        etEmail = findViewById(R.id.etEmail);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        dbHandler = new DBHandler(UpdateDetails.this);

        name = getIntent().getStringExtra("name");
        number = getIntent().getStringExtra("number");
        email = getIntent().getStringExtra("email");

        etName.setText(name);
        etEmail.setText(email);
        etNumber.setText(number);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.deleteDetails(name);
                Toast.makeText(UpdateDetails.this, "Delete successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UpdateDetails.this, ViewListUser.class));
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbHandler.updateDetails(name, etName.getText().toString(), etNumber.getText().toString(), etEmail.getText().toString());
                Toast.makeText(UpdateDetails.this, "Update successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UpdateDetails.this, ViewListUser.class));
                finish();
            }
        });
    }
}