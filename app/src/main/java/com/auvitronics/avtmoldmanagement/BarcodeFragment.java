package com.auvitronics.avtmoldmanagement;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.auvitronics.avtmoldmanagement.Interfaces.IFragmentOperations;
import com.auvitronics.avtmoldmanagement.Utilities.Constants;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BarcodeFragment extends Fragment {

    @BindView(R.id.svBarCodeF)
    SurfaceView svBarcodeF;

    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;
    Uri notifySound;
    Ringtone ringtone;
    Unbinder unbinder;
    String fragmentName;

    /** Interface **/
    IFragmentOperations iFragmentOperations;

    public BarcodeFragment() {
        //  Empty Constructor Required
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_barcode, container, false);
        unbinder = ButterKnife.bind(this,view);

        fragmentName = getArguments().getString(Constants.FRAGMENT_NAME);
        ((BaseActivity) getActivity()).setSubTitle(fragmentName);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /** Let's Implement the Interface, since BaseActivity needs it **/
        if ((getActivity()) instanceof IFragmentOperations)
            iFragmentOperations = (IFragmentOperations) getActivity();

        //  Sound Play
        notifySound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        ringtone = RingtoneManager.getRingtone(getContext(), notifySound);
        //  Barcode Detector
        barcodeDetector = new BarcodeDetector.Builder(getContext())
                .build();

        // Camera
        cameraSource = new CameraSource
                .Builder(getContext(),barcodeDetector)
                .setAutoFocusEnabled(true)
                .setRequestedFps(15.0f)
//                .setRequestedPreviewSize(800,600)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .build();

//        svBarcodeF = view.findViewById(R.id.svBarCodeF);

        svBarcodeF.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    cameraSource.start(svBarcodeF.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
                Log.d(Constants.TAG, "BarcodeDetector released...");
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if(barcodes.size()>0)
                {
                    ringtone.play();
                    iFragmentOperations.onOperationComplete(CommonStatusCodes.SUCCESS,fragmentName, barcodes.valueAt(0));
                    barcodeDetector.release();
                    getActivity().getSupportFragmentManager().popBackStack();
                }

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
