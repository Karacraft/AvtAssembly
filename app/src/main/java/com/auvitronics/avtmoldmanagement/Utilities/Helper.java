package com.auvitronics.avtmoldmanagement.Utilities;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.auvitronics.avtmoldmanagement.BuildConfig;


public class Helper {

    //----------------------------------getAppVersionNumber()-----------------------------//
    public static String getAppVersionNumber(Context context){

        String versionNumber="";
        try
        {
            versionNumber = context.getPackageManager().getPackageInfo(context.getPackageName(),0).versionName;
        } catch (PackageManager.NameNotFoundException e)
        {
            if (BuildConfig.DEBUG)
            {
                Log.d (Constants.TAG, "Helper->getAppVersionNumber: " + e.getMessage());
            }
        }

        return versionNumber;
    }

}
