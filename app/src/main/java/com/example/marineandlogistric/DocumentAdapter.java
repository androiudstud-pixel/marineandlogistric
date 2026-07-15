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

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.ViewHolder> {
    private ArrayList<Document> list;

    public DocumentAdapter(ArrayList<Document> list) { this.list = list; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.document_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Document item = list.get(position);
        holder.name.setText(item.getName());
        holder.type.setText(item.getType());
        holder.date.setText(item.getDate());

        holder.itemView.setOnLongClickListener(v -> {
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Delete Document")
                    .setMessage("Remove " + item.getName() + " from archive?")
                    .setPositiveButton("Delete", (dialog, which) -> {
                        DatabaseHelper db = new DatabaseHelper(v.getContext());
                        if (db.deleteDocument(item.getId())) {
                            list.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, list.size());
                            Toast.makeText(v.getContext(), "Document Removed", Toast.LENGTH_SHORT).show();
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
        TextView name, type, date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvDocName);
            type = itemView.findViewById(R.id.tvDocType);
            date = itemView.findViewById(R.id.tvDocDate);
        }
    }
}
