package com.ethadien.whichismydog.adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.ethadien.whichismydog.BR;
import com.ethadien.whichismydog.R;
import com.ethadien.whichismydog.databinding.RecyclerViewDesignBinding;
import com.ethadien.whichismydog.model.Breed;
import com.ethadien.whichismydog.view.ImagesActivity;
import com.ethadien.whichismydog.viewmodel.DogBreedsViewModel;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.BreedViewHolder> {

    private int layoutId;
    private List<Breed> breeds;
    private DogBreedsViewModel viewModel;


    public RecyclerViewAdapter(@LayoutRes int layoutId, DogBreedsViewModel viewModel) {
        this.layoutId = layoutId;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public BreedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);

        return new BreedViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BreedViewHolder holder, int position) {
        holder.bind(viewModel, position);
    }

    private int getLayoutIdForPosition(int position){
        return layoutId;
    }

    public int getItemViewType(int position){
        return getLayoutIdForPosition(position);
    }

    @Override
    public int getItemCount() {
        return breeds == null ? 0 : breeds.size();
    }

    public void setDogBreeds(List<Breed> breeds){
        this.breeds = breeds;
    }





    static class BreedViewHolder extends RecyclerView.ViewHolder {
        final RecyclerViewDesignBinding binding;

        BreedViewHolder (RecyclerViewDesignBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(DogBreedsViewModel viewModel, Integer position){
            viewModel.fetchDogBreedImagesAt(position);
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }

    }
}
