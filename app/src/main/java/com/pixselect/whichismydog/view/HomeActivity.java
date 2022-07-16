package com.pixselect.whichismydog.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.google.android.material.snackbar.Snackbar;
import com.pixselect.whichismydog.databinding.ActivityHomeBinding;
import com.pixselect.whichismydog.model.Breeds;
import com.pixselect.whichismydog.service.ApiUtils;
import com.pixselect.whichismydog.service.BreedsDAOInterface;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;

    List<String> breeds = null;
    List<List<String>> subBreeds = null;
    Map<String,List<String>> breedMap = null;

    ExpandableListAdapter expandableListAdapter;
    private BreedsDAOInterface Ibreeds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Ibreeds = ApiUtils.getBreedsDaoInterface();



        //for(String s:)

        Log.e("Dog Breed****",breeds.get(2));

        //for(int i:)
        //subBreeds()


        binding.expandableListView.setAdapter(expandableListAdapter);
        binding.expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            int lastExpandedPosition = -1;

            @Override
            public void onGroupExpand(int i) {
                if (lastExpandedPosition != -1 && i == lastExpandedPosition){
                    binding.expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = i;
            }
        });
        binding.expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                String selected = expandableListAdapter.getChild(i,i1).toString();
                Snackbar.make(view,"Selected"+selected, 2000).show();

                return false;
            }
        });
        binding.expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });




    }

    public List<String> allBreeds(){
        List<String> breedList = null;

        Ibreeds.allBreeds().enqueue(new Callback<Breeds>() {
            @Override
            public void onResponse(Call<Breeds> call, Response<Breeds> response) {
                List<String> breeds = response.body().getMessage();

                for (String b:breeds){
                    //Log.e("**************","***********");
                    //Log.e("Breed Name",b);
                    breedList.add(b);
                }

            }

            @Override
            public void onFailure(Call<Breeds> call, Throwable t) {

            }
        });
        return breedList;
    }

    public List<List<String>> subBreeds(String breed){
        List<List<String>> subBreeds = null;

        Ibreeds.listSubBreed(breed).enqueue(new Callback<Breeds>() {
            @Override
            public void onResponse(Call<Breeds> call, Response<Breeds> response) {
                List<String> subBreeds = response.body().getMessage();

                for (String s:subBreeds){


                }
            }

            @Override
            public void onFailure(Call<Breeds> call, Throwable t) {

            }
        });

        return subBreeds;
    }


}