package com.ethadien.whichismydog.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static final String BASE_URL = "https://dog.ceo/api/";
    private static BreedsInterface api;

    public static BreedsInterface getApi(){
        if (api == null){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            api = retrofit.create(BreedsInterface.class);

        }

        return api;

    }


}
