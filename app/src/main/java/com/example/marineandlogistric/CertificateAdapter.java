package com.example.marineandlogistric;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
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
        holder.issue.setText(item.getIssueDate());
        holder.expiry.setText(item.getExpiryDate());

        holder.itemView.setOnLongClickListener(v -> {
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Delete Certificate")
                    .setMessage("Remove " + item.getName() + "?")
                    .setPositiveButton("Delete", (dialog, which) -> {
                        DatabaseHelper db = new DatabaseHelper(v.getContext());
                        if (db.deleteCertificate(item.getId())) {
                            list.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, list.size());
                            Toast.makeText(v.getContext(), "Certificate Removed", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
            return true;
        });
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
