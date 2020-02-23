package com.auvitronics.avtmoldmanagement;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.auvitronics.avtmoldmanagement.Interfaces.IFragmentOperations;
import com.auvitronics.avtmoldmanagement.Utilities.Constants;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        IFragmentOperations {

    @BindView(R.id.navigation_drawer)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    Fragment fragment = null;       // Create a fragment Object

    FragmentManager fragmentManager = getSupportFragmentManager();
    ActionBarDrawerToggle toggle;   //  This enables Hamburger Menu

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 200;
    private static final int INTERNET_PERMISSION_REQUEST_CODE = 201;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);

        /** Setup Drawer Listener **/
        navView.setNavigationItemSelectedListener(this);

        /** Setup Hamburger Toggle **/
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open, R.string.close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        /** Check if we have Permission to Use Camera & Internet **/
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_REQUEST_CODE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    INTERNET_PERMISSION_REQUEST_CODE);
        }


        /** Load First Fragment Here **/
        fragment = new BaseFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
        setupBaseFragment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        /** All Code should come over here for clicks **/
        int itemId = menuItem.getItemId();  //  Get Selected item id
        switch (itemId) {
            case R.id.home_screen:
                fragment = new BaseFragment();
                replaceFragment(fragment, Constants.FRAGMENT_HOME);
                break;
            case R.id.scan_production_order:
                fragment = new BarcodeFragment();
                replaceFragment(fragment, Constants.FRAGMENT_PRODUCTION_ORDER);
                break;
            case R.id.scan_supervisor:
                fragment = new BarcodeFragment();
                replaceFragment(fragment, Constants.FRAGMENT_SUPERVISOR);
                break;
            case R.id.scan_workstation:
                fragment = new BarcodeFragment();
                replaceFragment(fragment, Constants.FRAGMENT_WORKSTATION);
                break;
            case R.id.scan_mold:
                fragment = new BarcodeFragment();
                replaceFragment(fragment, Constants.FRAGMENT_MOLD);
                break;
            case R.id.scan_operators:
                fragment = new OperatorFragment();
                replaceFragment(fragment, Constants.FRAGMENT_OPERATORS);
                break;
            case R.id.scan_helpers:
                fragment = new HelperFragment();
                replaceFragment(fragment, Constants.FRAGMENT_HELPERS);
                break;
            default:
//                Toast.makeText(this, "Unknown ", Toast.LENGTH_SHORT).show();
        }
        navView.setCheckedItem(itemId); //  Set the checked item
        drawerLayout.closeDrawer(GravityCompat.START);  //   Close the Drawer
        return false;
    }

    /**
     * Creates the Fragment & Replace it with existing
     *
     * @param fragment     Fragment Activity
     * @param fragmentName Fragment Name mentioned in Constants
     */
    private void replaceFragment(Fragment fragment, String fragmentName) {

        String backStateName = fragment.getClass().getName();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStateName, 0);

        Bundle bundle = new Bundle();
        bundle.putString(Constants.FRAGMENT_NAME, fragmentName);

        if (!fragmentPopped) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            fragment.setArguments(bundle);
            ft.replace(R.id.fragment_container, fragment);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            //  Only show first fragment - Requirement
            fragmentManager.popBackStack(
                    getSupportFragmentManager().getBackStackEntryAt(0).getId(),
                    getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
            setupBaseFragment();
        }

        /** Close Drawer **/
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    /**
     * Used by Fragments to Setup SubTitle
     *
     * @param title subtitle for activity
     */
    public void setSubTitle(String title) {
        toolbar.setSubtitle(title);
    }

    /**
     * Helps in setting up Various Variables
     */
    private void setupBaseFragment() {
        toolbar.setSubtitle(R.string.app_subtitle_main);        //   Set Subtitle
        navView.setCheckedItem(R.id.home_screen);               //  Set Checked Item on NavDrawer
    }

    @Override
    public void onOperationComplete(int status, String fragmentTag, Barcode barcode) {
        if (status == CommonStatusCodes.SUCCESS) {
            Log.d(Constants.TAG, "OnBarcodeDetection: " + barcode.displayValue + " " + fragmentTag);
            fragmentManager.popBackStack();
        }
    }

    @Override
    public void onOperationComplete(int status, String fragmentTag, String data) {
        /** Empty **/
        Log.d(Constants.TAG, "onOperationComplete: ...");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Unable to continue without Camera Permission", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            case INTERNET_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Unable to continue without Internet Permission", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }
}
