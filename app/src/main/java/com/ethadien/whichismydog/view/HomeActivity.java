package com.ethadien.whichismydog.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.snackbar.Snackbar;
import com.ethadien.whichismydog.R;
import com.ethadien.whichismydog.adapter.RecyclerViewAdapter;
import com.ethadien.whichismydog.databinding.ActivityHomeBinding;
import com.ethadien.whichismydog.model.Answer;
import com.ethadien.whichismydog.service.ApiUtils;
import com.ethadien.whichismydog.service.BreedsInterface;

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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        Toolbar toolbar = binding.toolBar;

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setSubtitle(R.string.breeds);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            binding.setActivity(this);
        }catch (Exception e){
            Log.e("Hata", "çalışmadı");
        }

        Ibreeds = ApiUtils.getBreedsDaoInterface();
        //allBreeds();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bring_random_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bringRandom:
                IFeelLucky();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*public void allBreeds(){

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
    }*/


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
        try {
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
            binding.recyclerView.setAdapter(RWAdapter = new RecyclerViewAdapter(breedsList));
        }catch (Exception e){
         Log.e("hata","initRecyclerView: "+e.toString());
        }
            }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}