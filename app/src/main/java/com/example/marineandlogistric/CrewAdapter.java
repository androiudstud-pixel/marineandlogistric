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

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.ViewHolder> {

    ArrayList<Crew> crewList;

    public CrewAdapter(ArrayList<Crew> crewList) {
        this.crewList = crewList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crew_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Crew crew = crewList.get(position);

        holder.tvCrewName.setText(crew.getName());
        holder.tvRank.setText(crew.getRank());
        holder.tvNationality.setText(crew.getNationality());
        holder.tvPassport.setText(crew.getPassport());

        holder.itemView.setOnLongClickListener(v -> {
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Remove Crew Member")
                    .setMessage("Do you want to remove " + crew.getName() + "?")
                    .setPositiveButton("Remove", (dialog, which) -> {
                        DatabaseHelper db = new DatabaseHelper(v.getContext());
                        if (db.deleteCrew(crew.getId())) {
                            crewList.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, crewList.size());
                            Toast.makeText(v.getContext(), "Crew Member Removed", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return crewList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCrewName, tvRank, tvNationality, tvPassport;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCrewName = itemView.findViewById(R.id.tvCrewName);
            tvRank = itemView.findViewById(R.id.tvRank);
            tvNationality = itemView.findViewById(R.id.tvNationality);
            tvPassport = itemView.findViewById(R.id.tvPassport);
        }
    }
}
