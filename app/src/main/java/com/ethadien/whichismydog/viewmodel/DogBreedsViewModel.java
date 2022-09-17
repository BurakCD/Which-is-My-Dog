package com.ethadien.whichismydog.viewmodel;

import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.ethadien.whichismydog.R;
import com.ethadien.whichismydog.adapter.RecyclerViewAdapter;
import com.ethadien.whichismydog.model.Breed;
import com.ethadien.whichismydog.model.BreedImages;
import com.ethadien.whichismydog.model.Breeds;
import com.ethadien.whichismydog.service.DogImagesCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class DogBreedsViewModel {
    private Breeds dogBreeds;
    private RecyclerViewAdapter adapter;
    public MutableLiveData<Breed> selectedBreed;
    public ObservableArrayMap<String, String> images;
    public ObservableInt loading;
    public ObservableInt showEmpty;

    public void init(){
        dogBreeds = new Breeds();
        selectedBreed = new MutableLiveData<>();
        adapter = new RecyclerViewAdapter(R.layout.recycler_view_design, this);
        images = new ObservableArrayMap<>();
        loading = new ObservableInt(View.GONE);
        showEmpty = new ObservableInt(View.GONE);
    }

    public void fetchList(){
        dogBreeds.fetchList();
    }

    public MutableLiveData<List<Breed>> getBreeds(){
        return dogBreeds.getBreeds();
    }

    public RecyclerViewAdapter getAdapter(){
        return adapter;
    }

    public void setDogBreedsInAdapter(List<Breed> breeds){
        this.adapter.setDogBreeds(breeds);
        this.adapter.notifyDataSetChanged();
    }

    public MutableLiveData<Breed> getSelectedBreed(){
        return selectedBreed;
    }

    public void onItemClick(Integer index){
        Breed breed = getDogBreedAt(index);
        selectedBreed.setValue(breed);
    }

    public Breed getDogBreedAt(Integer index){
        if (dogBreeds.getBreeds().getValue() != null  &&
                index != null &&
                dogBreeds.getBreeds().getValue().size() > index){
            return dogBreeds.getBreeds().getValue().get(index);
        }
        return null;
    }

    public void fetchDogBreedImagesAt(Integer index ){
        Breed breed = getDogBreedAt(index);
        if (breed != null && !images.containsKey(breed.getBreed())){
            breed.getImages(new DogImagesCallback(breed.getBreed()){
                @Override
                public void onResponse(Call<BreedImages> call, Response<BreedImages> response) {

                    BreedImages body = response.body();

                    if (body.getImages() != null && body.getImages().length >0){
                        String thumbnailUrl = body.getImages()[0];
                        images.put(getBreed(), thumbnailUrl);
                    }

                }

                @Override
                public void onFailure(Call<BreedImages> call, Throwable t) {
                    Log.e("hata","fetchDogBreedImagesAt "+t.getMessage());
                }
            });
        }
    }

}
