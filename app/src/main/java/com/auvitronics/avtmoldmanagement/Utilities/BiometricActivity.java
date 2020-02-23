package com.auvitronics.avtmoldmanagement.Utilities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.auvitronics.avtmoldmanagement.R;

import java.util.concurrent.Executor;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.auvitronics.avtmoldmanagement.Utilities.Constants.TAG;

public class BiometricActivity extends AppCompatActivity {

    @BindView(R.id.biometric_login)
    Button biometricLogin;

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biomertic);
        ButterKnife.bind(this);


        executor = ContextCompat.getMainExecutor(this);

        biometricPrompt = new BiometricPrompt(BiometricActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(BiometricActivity.this, "Auth Error : " + errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(BiometricActivity.this, "Auth Success : ", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onAuthenticationSucceeded: ");

            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(BiometricActivity.this, "Auth Failed : ", Toast.LENGTH_SHORT).show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Login via Biometric")
                .setSubtitle("Login Using Your Biometric Credentials")
                .setNegativeButtonText("User Account Password")
                .build();

        biometricPrompt.authenticate(promptInfo);
        // Prompt appears when user clicks "Log in".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.
        biometricLogin.setOnClickListener(view -> {
            biometricPrompt.authenticate(promptInfo);
        });
    }
}
