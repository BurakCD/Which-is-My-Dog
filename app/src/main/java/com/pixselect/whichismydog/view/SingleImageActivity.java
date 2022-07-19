package com.pixselect.whichismydog.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.pixselect.whichismydog.adapter.RoundedTransformation;
import com.pixselect.whichismydog.databinding.ActivitySingleImageBinding;
import com.squareup.picasso.Picasso;

public class SingleImageActivity extends AppCompatActivity {
    ActivitySingleImageBinding binding;
    String incomingURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySingleImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent  = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null){
            incomingURL = (String) bundle.get("URL");
        }

        Picasso.get().load(incomingURL).transform(new RoundedTransformation(16,0)).into(binding.ImageView);
        //RoundedTransformation picassodan implemente edilen bir transform nesnesi

        String holder = incomingURL.substring(30, incomingURL.lastIndexOf("/n"));

        binding.TextView.setText(holder);




    }
}