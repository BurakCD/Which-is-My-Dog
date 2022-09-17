package com.ethadien.whichismydog.service;

import com.ethadien.whichismydog.model.BreedImages;

import retrofit2.Callback;

public abstract class DogImagesCallback implements Callback<BreedImages> {
    private String breed;

    protected DogImagesCallback(String breed ){
        this.breed = breed;
    }

    protected String getBreed(){
        return this.breed;
    }
}
