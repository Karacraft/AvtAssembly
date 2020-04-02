package com.auvitronics.avtassembly.Intents;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.auvitronics.avtassembly.Base;
import com.auvitronics.avtassembly.BaseFragment;
import com.auvitronics.avtassembly.Utilities.Constants;
import com.auvitronics.avtassembly.Utilities.Helper;

import java.util.Date;

public class UpdateDataService extends IntentService
{
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public UpdateDataService()
    {
        super("UpdateDataService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent)
    {
        Log.i(Constants.TAG, "UpdateDataService running..." + new Date().toString());
        if (Helper.isInternetConnected(getApplicationContext()) && Base.hasDataToUpload())
        {
            //  We Can Upload Data
            new BaseFragment().uploadDataToServer();
        }
    }
}
