package com.example.marineandlogistric;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShipAdapter extends RecyclerView.Adapter<ShipAdapter.ViewHolder> {

    private ArrayList<Ship> shipList;

    public ShipAdapter(ArrayList<Ship> shipList) {
        this.shipList = shipList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ship_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ship ship = shipList.get(position);

        holder.tvShipName.setText(ship.getName());
        holder.tvIMO.setText("IMO : " + ship.getImo());
        holder.tvType.setText("Type : " + ship.getType());
        holder.tvFlag.setText("Flag : " + ship.getFlag());
        holder.tvStatus.setText(ship.getStatus());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ShipDetailsActivity.class);
            intent.putExtra("shipId", ship.getId());
            intent.putExtra("name", ship.getName());
            intent.putExtra("imo", ship.getImo());
            intent.putExtra("type", ship.getType());
            intent.putExtra("flag", ship.getFlag());
            intent.putExtra("status", ship.getStatus());
            v.getContext().startActivity(intent);
        });

        holder.itemView.setOnLongClickListener(v -> {
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Delete Vessel")
                    .setMessage("Are you sure you want to remove " + ship.getName() + "?")
                    .setPositiveButton("Delete", (dialog, which) -> {
                        DatabaseHelper db = new DatabaseHelper(v.getContext());
                        if (db.deleteShip(ship.getId())) {
                            shipList.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, shipList.size());
                            Toast.makeText(v.getContext(), "Vessel Removed", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return shipList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvShipName, tvIMO, tvType, tvFlag, tvStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvShipName = itemView.findViewById(R.id.tvShipName);
            tvIMO = itemView.findViewById(R.id.tvIMO);
            tvType = itemView.findViewById(R.id.tvType);
            tvFlag = itemView.findViewById(R.id.tvFlag);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}
