package com.arimbimega.sportlinenews.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arimbimega.sportlinenews.Model.Articles;
import com.arimbimega.sportlinenews.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.ListViewHolder> {

    private ArrayList<Articles> articlesArrayList;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public SportAdapter(ArrayList<Articles> articlesArrayList) {
        this.articlesArrayList = articlesArrayList;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public SportAdapter.ListViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sport, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull SportAdapter.ListViewHolder holder, int position) {

        holder.tvSportTglPub.setText(articlesArrayList.get(position).getPublishedAt());
        holder.tvSportTitle.setText(articlesArrayList.get(position).getTitle());
        holder.tvSportPub.setText(articlesArrayList.get(position).getSource().getName());
        holder.tvSportDesc.setText(articlesArrayList.get(position).getDescription());
        Glide.with(holder.itemView.getContext())
                .load(articlesArrayList.get(position).getUrlToImage())
                .into(holder.imgItemSport);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(articlesArrayList.get(holder.getBindingAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tvSportTglPub, tvSportTitle, tvSportPub, tvSportDesc;
        ImageView imgItemSport;

        public ListViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            tvSportTglPub = itemView.findViewById(R.id.item_tglPub);
            tvSportTitle = itemView.findViewById(R.id.item_judulBerita);
            tvSportPub = itemView.findViewById(R.id.item_penerbitBerita);
            tvSportDesc = itemView.findViewById(R.id.item_descBerita);
            imgItemSport = itemView.findViewById(R.id.item_imgSport);

        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Articles data);
    }
}
