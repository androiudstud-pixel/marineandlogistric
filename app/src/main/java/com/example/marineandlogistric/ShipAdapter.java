package com.example.marineandlogistric;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ship_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Ship ship = shipList.get(position);

        holder.tvShipName.setText(ship.getName());
        holder.tvIMO.setText("IMO : " + ship.getImo());
        holder.tvType.setText("Type : " + ship.getType());
        holder.tvFlag.setText("Flag : " + ship.getFlag());
        holder.tvStatus.setText("Status : " + ship.getStatus());

        // Open Ship Details on Click
        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(v.getContext(), ShipDetailsActivity.class);

            intent.putExtra("name", ship.getName());
            intent.putExtra("imo", ship.getImo());
            intent.putExtra("type", ship.getType());
            intent.putExtra("flag", ship.getFlag());
            intent.putExtra("status", ship.getStatus());

            v.getContext().startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return shipList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvShipName;
        TextView tvIMO;
        TextView tvType;
        TextView tvFlag;
        TextView tvStatus;

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