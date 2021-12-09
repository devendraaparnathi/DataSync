package com.example.datasync.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datasync.R;
import com.example.datasync.Pojo.UserDataPojo;

import java.util.ArrayList;

public class SyncDataListAdapter extends RecyclerView.Adapter<SyncDataListAdapter.ViewHolder> {

    private ArrayList<UserDataPojo> pojoUserArrayList;
    public Context context;

    public SyncDataListAdapter(ArrayList<UserDataPojo> pojoUserArrayList, Context context) {
        this.pojoUserArrayList = pojoUserArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public SyncDataListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user_details, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SyncDataListAdapter.ViewHolder holder, int position) {

        UserDataPojo data = pojoUserArrayList.get(position);

        holder.tvlName.setText(data.getContactName());
        holder.tvlNumber.setText(data.getMobileNo());
        holder.tvlEmail.setText(data.getEmailId());

    }

    @Override
    public int getItemCount() {
        return pojoUserArrayList.size();
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
