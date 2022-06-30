package com.example.tripapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripapplication.DetailsActivity;
import com.example.tripapplication.R;
import com.example.tripapplication.model.TopPlacesData;

import java.util.List;

public class TopPlacesAdapter extends RecyclerView.Adapter<TopPlacesAdapter.TopPlacesViewHolder> {

    private Context context;
    private List<TopPlacesData> topPlacesData;

    public TopPlacesAdapter(Context context, List<TopPlacesData> topPlacesData) {
        this.context = context;
        this.topPlacesData = topPlacesData;
    }

    @NonNull
    @Override
    public TopPlacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.top_places_row_item, parent, false);
        return new TopPlacesViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TopPlacesViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.countryName.setText(topPlacesData.get(position).getCountryName());
        holder.placeName.setText(topPlacesData.get(position).getPlaceName());
        holder.price.setText(topPlacesData.get(position).getPrice());
        holder.placeImage.setImageResource(topPlacesData.get(position).getImageUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);
                TopPlacesData topplacesdata = new TopPlacesData(topPlacesData.get(position).getPlaceName(), topPlacesData.get(position).getCountryName(), topPlacesData.get(position).getPrice(), topPlacesData.get(position).getImageUrl(), topPlacesData.get(position).getStatus());
                Bundle bundle = new Bundle();
                bundle.putSerializable("toprecent", topplacesdata);
                bundle.putString("key", "top");
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topPlacesData.size();
    }

    public static final class TopPlacesViewHolder extends RecyclerView.ViewHolder {

        ImageView placeImage;
        TextView placeName, countryName, price;

        public TopPlacesViewHolder(@NonNull View itemView) {
            super(itemView);
            placeImage = itemView.findViewById(R.id.place_image);
            placeName = itemView.findViewById(R.id.place_name);
            countryName = itemView.findViewById(R.id.country_name);
            price = itemView.findViewById(R.id.price);
        }
    }
}
