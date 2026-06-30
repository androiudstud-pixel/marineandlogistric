package com.example.marineandlogistric;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.crew_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Crew crew = crewList.get(position);

        holder.tvCrewName.setText(crew.getName());
        holder.tvRank.setText("Rank : " + crew.getRank());
        holder.tvNationality.setText("Nationality : " + crew.getNationality());
        holder.tvPassport.setText("Passport : " + crew.getPassport());

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