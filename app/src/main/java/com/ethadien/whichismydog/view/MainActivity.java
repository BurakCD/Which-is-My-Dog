package com.ethadien.whichismydog.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.renderscript.ScriptGroup;

import com.ethadien.whichismydog.R;
import com.ethadien.whichismydog.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int progressStart = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new CountDownTimer(1,100){

            @Override
            public void onTick(long l) {
                binding.progressBar.setProgress(progressStart += 2);
                binding.progressBar.setMax(100);
                //her 100 milisaniyede yüzde 2 = 1 saniyede yüzde 20
                //biterken progress bar dolmadığı için süreyi 5100 ms ye uzattım
            }

            @Override
            public void onFinish() {
                binding.progressBar.setProgress(100);
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                finish();
                binding = null;
            }
        }.start();
    }

}