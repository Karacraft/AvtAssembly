package com.auvitronics.avtmoldmanagement;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        delayLoading.start();   // Call the Thread
    }

    /** Delayed Loading of Main Activity */
    Thread delayLoading = new Thread()
    {
        @Override
        public void run()
        {
            try
            {
                Thread.sleep(3000); //For Delay

                loadMainActivity(); //Function to lead another activity after some delay
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    };

    protected void loadMainActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
