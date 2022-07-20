package com.pixselect.whichismydog.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.pixselect.whichismydog.R;
import com.pixselect.whichismydog.adapter.RecyclerViewAdapter;
import com.pixselect.whichismydog.databinding.ActivityHomeBinding;
import com.pixselect.whichismydog.model.Answer;
import com.pixselect.whichismydog.service.ApiUtils;
import com.pixselect.whichismydog.service.BreedsInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    List<String> breedsList = new ArrayList<>();
    private BreedsInterface Ibreeds;
    private RecyclerViewAdapter RWAdapter;
    private ActivityHomeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Ibreeds = ApiUtils.getBreedsDaoInterface();
        allBreeds();

        binding.RandomDude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IFeelLucky();
            }
        });

    }

    public void allBreeds(){

        Ibreeds.allBreeds().enqueue(new Callback<Answer>() {
            @Override
            public void onResponse(Call<Answer> call, Response<Answer> response) {
                List<String> breedResponceList = response.body().getMessage();
                breedsList.clear();
                breedsList.addAll(breedResponceList);
                initRecyclerView();
            }

            @Override
            public void onFailure(Call<Answer> call, Throwable t) {
                throwError();
            }
        });
    }

    public void IFeelLucky(){
        Ibreeds.getRandom().enqueue(new Callback<Answer>() {
            @Override
            public void onResponse(Call<Answer> call, Response<Answer> response) {
                List<String> RandomImage = response.body().getMessage();

                String goingURL = RandomImage.get(0).toString();
                Intent intent = new Intent(HomeActivity.this, SingleImageActivity.class);
                intent.putExtra("URL", goingURL);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Answer> call, Throwable t) {
                throwError();
            }
        });
    }


    private void throwError() {
        Snackbar.make(binding.getRoot(), R.string.connectionStatus, 3000).show();
    }

    private void initRecyclerView() {
        RWAdapter = new RecyclerViewAdapter(breedsList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(RWAdapter);
    }

}