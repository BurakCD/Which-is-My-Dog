package com.ethadien.whichismydog.adapter;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

import com.ethadien.whichismydog.databinding.RecyclerViewDesignBinding;


public class BreedViewHolder extends RecyclerView.ViewHolder {
    public RecyclerViewDesignBinding binding;

    public BreedViewHolder(View view){
        super(view);
        binding = RecyclerViewDesignBinding.bind(view);
    }

    public void setText(String breed){
        binding.RWText.setText(breed);
    }


}
