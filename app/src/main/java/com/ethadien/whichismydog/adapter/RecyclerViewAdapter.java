package com.ethadien.whichismydog.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ethadien.whichismydog.R;
import com.ethadien.whichismydog.databinding.RecyclerViewDesignBinding;
import com.ethadien.whichismydog.view.ImagesActivity;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<BreedViewHolder> {

    List<String> veriler ;
    public String breed;
    RecyclerViewDesignBinding binding;

    public RecyclerViewAdapter() {
    }

    public RecyclerViewAdapter(List<String> veriler) {
        this.veriler = veriler;
    }

    @NonNull
    @Override
    public BreedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_design, parent,false);

        return new BreedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BreedViewHolder holder, int position) {
        holder.setText(veriler.get(position));

        holder.binding.breedRow.setTag(holder);

        holder.binding.breedRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                breed = holder.binding.RWText.getText().toString();
                Context context = view.getContext();
                Intent intent = new Intent(view.getContext(), ImagesActivity.class);
                intent.putExtra("Breed", holder.binding.RWText.getText().toString());
                context.startActivity(intent);

            }
        }); // experimental
    }

    @Override
    public int getItemCount() {
        return veriler.size();
    }

}