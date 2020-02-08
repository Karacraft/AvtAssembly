package com.auvitronics.avtmoldmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.auvitronics.avtmoldmanagement.DB.AppDatabase;
import com.auvitronics.avtmoldmanagement.Models.Person;
import com.auvitronics.avtmoldmanagement.Utilities.BarcodeScanner;
import com.auvitronics.avtmoldmanagement.Utilities.Constants;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btnOpenBarCodeScanner)
    Button btnOpenBarCodeScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        setSupportActionBar(toolbar);


//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        int resp = doesDeviceSupportBiomertic();
//        if(resp == Constants.BIOMERTIC_AVAILABLE)
//        {
//
//        }
        AppDatabase appDb = AppDatabase.getInstance(this);
        Person person = new Person(1459, "Syed Ahmed", "SUPERVISOR", 32000, (32000 / 176));
        appDb.personDao().insertPerson(person);
        Person person1 = new Person(21350, "Muhammad Maqbool", "OPERATOR", 30000, (30000 / 176));
        appDb.personDao().insertPerson(person1);
        Person person2 = new Person(21993, "Sumair Ali", "HELPER", 18000, (18000 / 176));
        appDb.personDao().insertPerson(person2);

        List<Person> persons = appDb.personDao().getPersonList();
        for (Person p :
                persons) {

            Log.d(Constants.TAG, "onCreate: " + p.toString());
        }

        Person me = appDb.personDao().getPersonById(1459);
        Log.d(Constants.TAG, me.toString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnSignInMain)
    public void onSignInMainClicked(){
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivityForResult(intent,0);
    }

    @OnClick(R.id.btnOpenBarCodeScanner)
    public void onViewClicked() {
        Intent intent = new Intent(MainActivity.this, BarcodeScanner.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // RequestCOde is 0, which we sent in intent, this way we can use multiple intents
        if (requestCode == 0 && data != null) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                Barcode barcode = data.getParcelableExtra("barcode");
//                tvBarCode.setText(barcode.displayValue);

            } else {
//                tvBarCode.setText("No Barcode Detected");
            }
            if (resultCode == CommonStatusCodes.CANCELED) {
//                tvBarCode.setText("Operation Cancelled");
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
