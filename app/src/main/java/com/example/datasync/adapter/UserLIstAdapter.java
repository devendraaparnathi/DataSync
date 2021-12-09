package com.example.datasync.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datasync.Api.ApiClient;
import com.example.datasync.Api.ApiInterface;
import com.example.datasync.Pojo.SQLitePojo;
import com.example.datasync.Pojo.UserDataPojo;
import com.example.datasync.R;
import com.example.datasync.model.SQLiteModelClass;
import com.example.datasync.ui.UpdateDetails;
import com.example.datasync.ui.ViewListUser;

import java.util.ArrayList;
import java.util.List;

public class UserLIstAdapter extends RecyclerView.Adapter<UserLIstAdapter.ViewHolder> {

    private ArrayList<SQLiteModelClass> sqLiteModelClassArrayList;
    public Context context;

    public UserLIstAdapter(ArrayList<SQLiteModelClass> sqLiteModelClassArrayList, Context context) {
        this.sqLiteModelClassArrayList = sqLiteModelClassArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserLIstAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserLIstAdapter.ViewHolder holder, int position) {

        SQLiteModelClass modelClass = sqLiteModelClassArrayList.get(position);

        holder.tvlName.setText(modelClass.getName());
        holder.tvlNumber.setText(modelClass.getNumber());
        holder.tvlEmail.setText(modelClass.getEmail());

        String cName = modelClass.getName();
        String cNumber = modelClass.getNumber();
        String cEmail = modelClass.getEmail();

        Intent intent = new Intent("Message");
        intent.putExtra("mName",cName);
        intent.putExtra("mNumber",cNumber);
        intent.putExtra("mEmail",cEmail);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    //    uploadData(modelClass.getName().toString(),modelClass.getNumber().toString(),modelClass.getEmail().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), UpdateDetails.class);

                i.putExtra("name", modelClass.getName());
                i.putExtra("number", modelClass.getNumber());
                i.putExtra("email", modelClass.getEmail());
                v.getContext().startActivity(i);
                ((Activity) v.getContext()).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return sqLiteModelClassArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvlName, tvlNumber, tvlEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvlName = itemView.findViewById(R.id.tvlName);
            tvlNumber = itemView.findViewById(R.id.tvlNumber);
            tvlEmail = itemView.findViewById(R.id.tvlEmail);
        }
    }

}
