package com.auvitronics.avtmoldmanagement.Utilities;

import android.content.Context;
import android.util.Log;

import androidx.biometric.BiometricManager;

import static com.auvitronics.avtmoldmanagement.Utilities.Constants.TAG;

public class BiometricUtils {

    Context context;

    public BiometricUtils(Context context) {
        this.context = context;
    }

    public void doesDeviceSupportBiomertic()
    {
        BiometricManager biometricManager = BiometricManager.from(context);

        switch (biometricManager.canAuthenticate())
        {
            case BiometricManager.BIOMETRIC_SUCCESS:
                Log.d(TAG, "Biomertic is Supported");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Log.e(TAG, "No biometric features available on this device.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Log.e(TAG, "Biometric features are currently unavailable.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Log.e(TAG, "The user hasn't associated " +
                        "any biometric credentials with their account.");
                break;

        }
    }
}
