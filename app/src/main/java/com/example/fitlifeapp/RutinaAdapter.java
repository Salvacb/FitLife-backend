package com.example.fitlifeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RutinaAdapter extends RecyclerView.Adapter<RutinaAdapter.RutinaViewHolder> {

    private final List<Rutina> rutinaList;
    private final OnRutinaClickListener listener;

    public RutinaAdapter(List<Rutina> rutinaList, OnRutinaClickListener listener) {
        this.rutinaList = rutinaList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RutinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rutina, parent, false);
        return new RutinaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RutinaViewHolder holder, int position) {
        Rutina rutina = rutinaList.get(position);
        holder.diaTextView.setText(rutina.getDia());
        holder.descripcionTextView.setText(rutina.getDescripcion());
        holder.itemView.setOnClickListener(v -> {
            listener.onRutinaClick(rutina);
        });

    }

    @Override
    public int getItemCount() {
        return rutinaList.size();
    }

    public static class RutinaViewHolder extends RecyclerView.ViewHolder {
        TextView diaTextView, descripcionTextView;

        public RutinaViewHolder(@NonNull View itemView) {
            super(itemView);
            diaTextView = itemView.findViewById(R.id.diaTextView);
            descripcionTextView = itemView.findViewById(R.id.descripcionTextView);
        }
    }
}
