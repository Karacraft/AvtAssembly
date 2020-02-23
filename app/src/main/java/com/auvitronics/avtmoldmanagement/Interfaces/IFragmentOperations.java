package com.auvitronics.avtmoldmanagement.Interfaces;

import com.google.android.gms.vision.barcode.Barcode;

public interface IFragmentOperations {

/**
* We will use this interface
* to communicate between the BaseActivity.class
* and Fragments
*
* Steps:
* 1- Define Interface
* 2- Implement it on BaseActivity.class
* 3- Implement the Method & Do whatever you want to
* 4- Create Interface variable if BarcodeFragment
* 5- in onActivityCreated() add the following lines to instantiate the interface
*          if ( getActivity() instanceof IFragmentOperations)
*              {
*                  myInterface = (IFragmentOperations) getActivity();
*              }
* 6- Call the method from Fragment and get the response in BaseActivity
*/

    /**
     * Helps out in passing data from Fragment to Calling Activity
     * @param status SUCCESS,CANCELLED of CommonStatusCode
     * @param fragmentTag Name of the Fragment
     */
    void onOperationComplete(int status, String fragmentTag, Barcode barcode);
    void onOperationComplete(int status, String fragmentTag, String data);

}
