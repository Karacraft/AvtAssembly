package com.auvitronics.avtmoldmanagement;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.auvitronics.avtmoldmanagement.Utilities.Constants;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BarcodeScanner extends AppCompatActivity {

    @BindView(R.id.svBarCode)
    SurfaceView svBarCode;

    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;
    Uri notifySound;
    Ringtone ringtone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);
        ButterKnife.bind(this);
        //  Sound Play
        notifySound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        ringtone = RingtoneManager.getRingtone(this, notifySound);
        //  Barcode Detector
        barcodeDetector = new BarcodeDetector.Builder(this)
                .build();

        // Camera
        cameraSource = new CameraSource
                .Builder(this,barcodeDetector)
                .setAutoFocusEnabled(true)
                .setRequestedFps(15.0f)
//                .setRequestedPreviewSize(800,600)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .build();

        svBarCode.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    cameraSource.start(svBarCode.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                Log.d(Constants.TAG, "surfaceChanged: ");
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
                Log.d(Constants.TAG, "surfaceDestroyed: ");
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

                Log.d(Constants.TAG, "release: barcodeDetector");
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if(barcodes.size()>0)
                {
                    ringtone.play();
                    Log.d(Constants.TAG, "receiveDetection : " + barcodes.valueAt(0).displayValue);
                    //  Return Back Data
                    Intent i = new Intent();
                    i.putExtra("scanned_data", barcodes.valueAt(0));
                    setResult(CommonStatusCodes.SUCCESS,i);
                    finish();

                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        setResult(CommonStatusCodes.CANCELED,i);
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
