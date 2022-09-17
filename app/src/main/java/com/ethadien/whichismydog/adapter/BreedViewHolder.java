package com.ethadien.whichismydog.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ethadien.whichismydog.databinding.RecyclerViewDesignBinding;


/*public class BreedViewHolderx extends RecyclerView.ViewHolder {
    public RecyclerViewDesignBinding binding;



    public BreedViewHolderx(View view){
        super(view);
        binding = RecyclerViewDesignBinding.bind(view);
    }

    public void setText(String breed){
        binding.RWText.setText(breed);
    }


}*/

public class BreedViewHolder extends RecyclerView.ViewHolder {

    static BreedViewHolder create(LayoutInflater inflater, ViewGroup parent, int viewType) {
        RecyclerViewDesignBinding binding = DataBindingUtil.inflate(inflater, viewType, parent, false);
        return new BreedViewHolder(binding);
    }

    private RecyclerViewDesignBinding binding;

    public BreedViewHolder(RecyclerViewDesignBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void setText(String breed){
        binding.RWText.setText(breed);
    }

    /*public void bindto(Object data) {
        binding.setVariable(BR.data, data);
        binding.executePendingBindings();
    }*/
}