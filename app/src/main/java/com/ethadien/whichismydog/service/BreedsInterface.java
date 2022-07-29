package com.ethadien.whichismydog.service;

import com.ethadien.whichismydog.model.Answer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BreedsInterface {

    @GET("breeds/list")
    Call<Answer> allBreeds();

    @GET("breed/{breed}/images")
    Call<Answer> listBreedImages(@Path("breed") String breed);

    @GET("breeds/image/random/1")
    Call<Answer> getRandom();

}
