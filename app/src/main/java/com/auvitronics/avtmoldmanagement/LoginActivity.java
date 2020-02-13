package com.auvitronics.avtmoldmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.auvitronics.avtmoldmanagement.DB.AppDatabase;
import com.auvitronics.avtmoldmanagement.Models.Person;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.etUserName)
    EditText username;
    @BindView(R.id.etPassword)
    EditText password;
    @BindView(R.id.loading)
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSignInMain)
    public void btnSingInMain(View view) {
        //  Check for Empty
        if (TextUtils.isEmpty(username.getText())) {
            username.setError("Employee Id Required");
            return;
        }
        if (TextUtils.isEmpty(password.getText())) {
            password.setError("Employee Password Required");
            return;
        }
        //  Check if the User is in Database
        //  TODO::Later On, this will be done via database on Server
        logInUser(username.getText().toString(), password.getText().toString());

    }

    @OnClick(R.id.btnSignInQrScan)
    public void btnSignInQrScan(View view) {
        Intent intent = new Intent(LoginActivity.this, BarcodeScanner.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // RequestCOde is 0, which we sent in intent, this way we can use multiple intents
        if (requestCode == 0 && data != null) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                Barcode barcode = data.getParcelableExtra("scanned_data");
                Toast.makeText(this, String.format("Returned Data : %s ", barcode.displayValue), Toast.LENGTH_SHORT).show();
                // Login User here
            } else {
                Toast.makeText(this, "No Data Received", Toast.LENGTH_SHORT).show();
            }

            if (resultCode == CommonStatusCodes.CANCELED) {
                Toast.makeText(this, "Operation cancelled by User", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    //  Login User
    private void logInUser(String username, String password) {
        AppDatabase appDb = AppDatabase.getInstance(this);
        Person person = appDb.personDao().getPersonById(Integer.parseInt(username));
        //  TODO:: Add password signin here
        if (person != null) {
            Intent i = new Intent();
            i.putExtra("emp_id", Integer.parseInt(username));
            setResult(CommonStatusCodes.SUCCESS, i);
            finish();
        } else {
            Toast.makeText(this, String.format("Employee id: %s doesn't exist", username), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        //  user has pressed back button
        Intent i = new Intent();
        setResult(CommonStatusCodes.CANCELED, i);
        finish();
        super.onBackPressed();
    }


}
