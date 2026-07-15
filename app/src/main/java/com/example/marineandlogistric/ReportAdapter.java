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

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {
    private ArrayList<Report> list;

    public ReportAdapter(ArrayList<Report> list) { this.list = list; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Report item = list.get(position);
        holder.title.setText(item.getTitle());
        holder.date.setText(item.getDate());
        holder.content.setText(item.getContent());

        holder.itemView.setOnLongClickListener(v -> {
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Delete Report")
                    .setMessage("Remove this report?")
                    .setPositiveButton("Delete", (dialog, which) -> {
                        DatabaseHelper db = new DatabaseHelper(v.getContext());
                        if (db.deleteReport(item.getId())) {
                            list.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, list.size());
                            Toast.makeText(v.getContext(), "Report Removed", Toast.LENGTH_SHORT).show();
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
        TextView title, date, content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvReportTitle);
            date = itemView.findViewById(R.id.tvReportDate);
            content = itemView.findViewById(R.id.tvReportContent);
        }
    }
}
