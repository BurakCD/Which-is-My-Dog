package com.pixselect.whichismydog.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.pixselect.whichismydog.R;
import com.pixselect.whichismydog.adapter.RecyclerViewAdapter;
import com.pixselect.whichismydog.databinding.ActivityHomeBinding;
import com.pixselect.whichismydog.databinding.RecyclerViewDesignBinding;
import com.pixselect.whichismydog.model.Answer;
import com.pixselect.whichismydog.service.ApiUtils;
import com.pixselect.whichismydog.service.BreedsInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;

    List<String> breedsList = new ArrayList<>();
    private BreedsInterface Ibreeds;
    private RecyclerViewAdapter RWAdapter;
    RecyclerViewDesignBinding recyclerBinding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Ibreeds = ApiUtils.getBreedsDaoInterface();
        allBreeds();


        Intent intent = new Intent(HomeActivity.this, ImagesActivity.class);
        startActivity(intent);


        /*recyclerBinding.breedRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ImagesActivity.class);
                intent.putExtra("Breed", recyclerBinding.RWText.getText().toString());
                startActivity(intent);
            }
        });*/
        
        // TODO: ana ekrandaki ırklara tıklanınca geriye string döndürme özelliğini unutma.



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


    private void throwError() {
        Snackbar.make(binding.getRoot(), R.string.connectionStatus, 3000).show();
    }

    private void initRecyclerView() {
        RWAdapter = new RecyclerViewAdapter(breedsList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(RWAdapter);
    }

}