package com.auvitronics.avtmoldmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.auvitronics.avtmoldmanagement.Utilities.Constants;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btnSelectProductionOrder)
    Button btnSelectProductionOrder;
    @BindView(R.id.btnSignInMain)
    Button btnSignInMain;
    @BindView(R.id.btnSelectWorkStation)
    Button btnSelectWorkStation;
    @BindView(R.id.btnSelectOperators)
    Button btnSelectOperators;
    @BindView(R.id.btnSelectHelpers)
    Button btnSelectHelpers;
    @BindView(R.id.btnStartProduction)
    Button btnStartProduction;
    @BindView(R.id.btnScanMold)
    Button btnScanMold;
    @BindView(R.id.tvPONumber)
    TextView tvPONumber;
    @BindView(R.id.tvWorkstation)
    TextView tvWorkstation;
    @BindView(R.id.tvMold)
    TextView tvMold;
    @BindView(R.id.tvSupervisor)
    TextView tvSupervisor;


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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the navigation_items; this adds items to the action bar if it is present.
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


    @OnClick(R.id.btnSelectProductionOrder)
    public void onSelectProductionOrder() {
        Intent intent = new Intent(MainActivity.this, BarcodeScanner.class);
        startActivityForResult(intent, 1);
    }

    @OnClick(R.id.btnSignInMain)
    public void onSignInMainClicked() {
        Intent intent = new Intent(MainActivity.this, BarcodeScanner.class);
        startActivityForResult(intent, 2);
    }

    @OnClick(R.id.btnSelectWorkStation)
    public void onSelectWorkStations() {
        Intent intent = new Intent(MainActivity.this, BarcodeScanner.class);
        startActivityForResult(intent, 3);
    }

    @OnClick(R.id.btnScanMold)
    public void onScanMold() {
        Intent intent = new Intent(MainActivity.this, BarcodeScanner.class);
        startActivityForResult(intent, 4);
    }

    @OnClick(R.id.btnSelectOperators)
    public void onSelectOperators() {
        //  TODO:: create an activity to select max 5 Operators
        Intent intent = new Intent(MainActivity.this, BarcodeScanner.class);
        startActivityForResult(intent, 5);
    }

    @OnClick(R.id.btnSelectHelpers)
    public void onSelectHelpers() {
        //  TODO:: create an actviity to select max 5 Helpers
        Intent intent = new Intent(MainActivity.this, BarcodeScanner.class);
        startActivityForResult(intent, 6);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // RequestCOde is 0, which we sent in intent, this way we can use multiple intents
        Log.d(Constants.TAG, "onActivityResult: in heree");
        if (resultCode == CommonStatusCodes.CANCELED) {
            Toast.makeText(this, "Nothing Received", Toast.LENGTH_SHORT).show();
            return;
        }

        switch (requestCode) {
            case 1:
                if (resultCode == CommonStatusCodes.SUCCESS) {
                    Barcode production_order = data.getParcelableExtra("scanned_data");
                    tvPONumber.setText("Prod-Order # : " + production_order.displayValue);
                    //TODO:: Enter Supervisor Data in Shared Prefs
                    btnSignInMain.setEnabled(true);
                }
                break;
            case 2:
                if (resultCode == CommonStatusCodes.SUCCESS) {
                    Barcode barcode = data.getParcelableExtra("scanned_data");
                    tvSupervisor.setText("Supervisor : " + barcode.displayValue);
                    btnSignInMain.setEnabled(false);
                    btnSelectWorkStation.setEnabled(true);
                    //TODO:: Enter Supervisor Data in Shared Prefs
                }
                break;
            case 3:
                if (resultCode == CommonStatusCodes.SUCCESS) {
                    Barcode workstation = data.getParcelableExtra("scanned_data");
                    btnSelectWorkStation.setEnabled(false);
                    btnScanMold.setEnabled(true);
                    tvWorkstation.setText("Workstation : " + workstation.displayValue);
                    //TODO:: Enter Workstation Data in Shared Prefs
                }
                break;
            case 4:
                if (resultCode == CommonStatusCodes.SUCCESS) {
                    Barcode mold = data.getParcelableExtra("scanned_data");
                    btnSelectOperators.setEnabled(true);
                    btnSelectHelpers.setEnabled(true);
                    tvMold.setText("Mold : " + mold.displayValue);
                    //TODO:: Enter Mold Data in Shared Prefs
                }
                break;
            case 5:
                if (resultCode == CommonStatusCodes.SUCCESS) {
                    Barcode operators = data.getParcelableExtra("scanned_data");
                    //TODO:: Enter Supervisor Data in Shared Prefs
                }
                break;
            case 6:
                if (resultCode == CommonStatusCodes.SUCCESS) {
                    Barcode helpers = data.getParcelableExtra("scanned_data");
                    //TODO:: Enter Supervisor Data in Shared Prefs
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
