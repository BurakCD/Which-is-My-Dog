package com.pixselect.whichismydog.service;

import com.pixselect.whichismydog.model.Answer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BreedsInterface {

    @GET("breeds/list")
    Call<Answer> allBreeds();

    @GET("breed/{breed}/images")
    Call<Answer> listBreedImages(@Path("breed") String breed);

    @GET("breeds/image/random")
    Call<Answer> getRandom();

}
