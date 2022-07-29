package com.ethadien.whichismydog.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.ethadien.whichismydog.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int progressStart = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new CountDownTimer(5100,100){

            @Override
            public void onTick(long l) {
                binding.progressBar.setProgress(progressStart += 2);
                binding.progressBar.setMax(100);
                //her 100 milisaniyede yüzde 2 = 1 saniyede yüzde 20 gönlüm el vermedi
                //biterken progress bar dolmadığı için süreyi 5100 ms ye uzattım
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                finish();
            }
        }.start();
    }

}