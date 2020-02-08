package com.karacraft.testlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        delayLoading.start();
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
//        Intent intent = new Intent(this, ActivityToStart.class);
//        startActivity(intent);
//        finish();
    }
}
