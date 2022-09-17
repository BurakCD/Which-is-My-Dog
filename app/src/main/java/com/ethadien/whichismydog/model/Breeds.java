package com.ethadien.whichismydog.model;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.ethadien.whichismydog.R;
import com.ethadien.whichismydog.service.Api;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Breeds {
    private String status;
    private List<Breed> breedList = new ArrayList<>();
    private MutableLiveData<List<Breed>> breeds = new MutableLiveData<>();
    private View mView;

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }
    public void addBreed(Breed breed){
        breedList.add(breed);
    }

    public MutableLiveData<List<Breed>> getBreeds(){
        return breeds;
    }

    public void fetchList(){
        Callback<Breeds> callback = new Callback<Breeds>() {
            @Override
            public void onResponse(Call<Breeds> call, Response<Breeds> response) {
                Breeds body = response.body();
                status = body.status;
                breeds.setValue(body.breedList);
            }

            @Override
            public void onFailure(Call<Breeds> call, Throwable t) {
                throwError();
            }
        };

        Api.getApi().allBreeds().enqueue(callback);
        }

    private void throwError() {
        Snackbar.make(mView, R.string.connectionStatus, 3000).show();
    }
    }

