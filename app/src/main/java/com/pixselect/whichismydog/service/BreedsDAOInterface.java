package com.pixselect.whichismydog.service;

import com.pixselect.whichismydog.model.Breeds;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BreedsDAOInterface {

    @GET("breeds/list")
    Call<Breeds> allBreeds();

    @GET("breed/{breed}/list")
    Call<Breeds> listSubBreed(@Path("breed") String breed);

    @GET("breed/{breed}/images")
    Call<Breeds> listBreedImages(@Path("breed") String breed);

    @GET("breed/{breed}/{subBreed}/images")
    Call<Breeds> listSubBreedImages(@Path("breed") String breed, @Path("subBreed") String subBreed);

    @GET("breeds/image/random")
    Call<Breeds> iFeelLucky();

}
