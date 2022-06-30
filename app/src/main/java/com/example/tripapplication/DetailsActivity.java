package com.example.tripapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tripapplication.databinding.ActivityDetailsBinding;
import com.example.tripapplication.model.RecentsData;
import com.example.tripapplication.model.TopPlacesData;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;
    private Boolean isLike =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        String key = bundle.getString("key");
        if (key.equals("recent")) {
            RecentsData data = (RecentsData) bundle.getSerializable("recent");

            binding.tvPlaceDetails.setText(data.getPlaceName().toString());
            binding.imgBG.setImageResource(data.getImageUrl());
            binding.tvAbout.setText(data.getStatus().toString());
            binding.tvPriceDetails.setText(data.getPrice().toString());
        } else if (key.equals("top")) {
            TopPlacesData data1 = (TopPlacesData) bundle.getSerializable("toprecent");

            binding.tvPlaceDetails.setText(data1.getPlaceName().toString());
            binding.imgBG.setImageResource(data1.getImageUrl());
            binding.tvAbout.setText(data1.getStatus().toString());
            binding.tvPriceDetails.setText(data1.getPrice().toString());
        } else {
            return;
        }

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        binding.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isLike){
                    binding.imgLike.setImageResource(R.drawable.like_red);
                    isLike= true;
                }
                else {
                binding.imgLike.setImageResource(R.drawable.like);
                isLike= false;
                }
            }
            }
        );


}}