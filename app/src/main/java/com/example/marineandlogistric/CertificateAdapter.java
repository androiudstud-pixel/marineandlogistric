package com.example.marineandlogistric;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CertificateAdapter extends RecyclerView.Adapter<CertificateAdapter.ViewHolder> {
    private ArrayList<Certificate> list;

    public CertificateAdapter(ArrayList<Certificate> list) { this.list = list; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.certificate_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Certificate item = list.get(position);
        holder.name.setText(item.getName());
        holder.issue.setText("Issue: " + item.getIssueDate());
        holder.expiry.setText("Expiry: " + item.getExpiryDate());
    }

    @Override
    public int getItemCount() { return list.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, issue, expiry;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvCertName);
            issue = itemView.findViewById(R.id.tvIssueDate);
            expiry = itemView.findViewById(R.id.tvExpiryDate);
        }
    }
}
