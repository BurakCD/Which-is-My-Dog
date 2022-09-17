package com.ethadien.whichismydog.model;

import android.os.Handler;

import androidx.databinding.BaseObservable;

import com.ethadien.whichismydog.service.Api;

import retrofit2.Callback;

public class Breed extends BaseObservable {
    private String breed;

    public Breed(String breed) {
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void getImages(Callback<BreedImages> callback){
        Api.getApi().allBreeds(this.breed).enqueue(callback);
    }
}
