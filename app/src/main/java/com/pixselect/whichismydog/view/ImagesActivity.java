package com.pixselect.whichismydog.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.google.android.material.snackbar.Snackbar;
import com.pixselect.whichismydog.R;
import com.pixselect.whichismydog.adapter.GridViewAdapter;
import com.pixselect.whichismydog.databinding.ActivityImagesBinding;
import com.pixselect.whichismydog.model.Answer;
import com.pixselect.whichismydog.service.ApiUtils;
import com.pixselect.whichismydog.service.BreedsInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImagesActivity extends AppCompatActivity {
    private ActivityImagesBinding binding;
    private String incomingBreed;

    List<String> ImagesList = new ArrayList<>();
    private BreedsInterface Ibreeds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImagesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        Ibreeds = ApiUtils.getBreedsDaoInterface();

        Intent intent  = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null){
            incomingBreed = (String) bundle.get("Breed");
        }

        binding.BreedNameHolder.setText(incomingBreed);

        getImagesList();

        binding.toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

    }

    public void getImagesList(){
        Ibreeds.listBreedImages(incomingBreed).enqueue(new Callback<Answer>() {
            @Override
            public void onResponse(Call<Answer> call, Response<Answer> response) {
                List<String> BreedImagesRespond = response.body().getMessage();

                for (String s : BreedImagesRespond) {
                    Log.e("cins: ", s);
                }

                ImagesList.clear();
                ImagesList.addAll(BreedImagesRespond);
                activateAdapter(ImagesList, ImagesActivity.this);
            }

            @Override
            public void onFailure(Call<Answer> call, Throwable t) {
                throwError();
            }
        });
    }

    public void activateAdapter(List<String> ImagesList, Context context){

        GridViewAdapter adapter = new GridViewAdapter(ImagesList, context);
        binding.gridView.setAdapter(adapter);

        binding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ImagesActivity.this, SingleImageActivity.class);
                intent.putExtra("URL", ImagesList.get(i).toString());
                startActivity(intent);
            }
        });

    }

    private void throwError() {
        Snackbar.make(binding.getRoot(), R.string.connectionStatus, 3000).show();
    }


}