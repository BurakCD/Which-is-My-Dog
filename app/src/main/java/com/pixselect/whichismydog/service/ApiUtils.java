package com.pixselect.whichismydog.service;

public class ApiUtils {

    public static final String BASE_URL = "https://dog.ceo/api/";

    public static BreedsInterface getBreedsDaoInterface(){
        return RetrofitClient.getClient(BASE_URL).create(BreedsInterface.class);
    }

}