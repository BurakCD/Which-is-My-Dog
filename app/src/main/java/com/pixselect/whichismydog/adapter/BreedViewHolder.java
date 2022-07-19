package com.pixselect.whichismydog.adapter;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.pixselect.whichismydog.databinding.RecyclerViewDesignBinding;

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
