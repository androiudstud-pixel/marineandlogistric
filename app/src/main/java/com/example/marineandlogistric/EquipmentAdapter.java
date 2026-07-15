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

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.ViewHolder> {
    private ArrayList<Equipment> list;

    public EquipmentAdapter(ArrayList<Equipment> list) { this.list = list; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.equipment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Equipment item = list.get(position);
        holder.name.setText(item.getName());
        holder.type.setText(item.getType());
        holder.serial.setText(item.getSerial());

        holder.itemView.setOnLongClickListener(v -> {
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Delete Equipment")
                    .setMessage("Remove " + item.getName() + " from inventory?")
                    .setPositiveButton("Delete", (dialog, which) -> {
                        DatabaseHelper db = new DatabaseHelper(v.getContext());
                        if (db.deleteEquipment(item.getId())) {
                            list.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, list.size());
                            Toast.makeText(v.getContext(), "Equipment Removed", Toast.LENGTH_SHORT).show();
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
        TextView name, type, serial;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvEqName);
            type = itemView.findViewById(R.id.tvEqType);
            serial = itemView.findViewById(R.id.tvEqSerial);
        }
    }
}
