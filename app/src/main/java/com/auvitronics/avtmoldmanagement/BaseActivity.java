package com.auvitronics.avtmoldmanagement;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.content_frame)
    FrameLayout contentFrame;
    @BindView(R.id.navigation_drawer)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);

        /** Setup Drawer **/
        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        /** All Code should come over here for clicks **/
        Fragment fragment = null;   // Create a fragment Object
        int itemId = menuItem.getItemId();  //  Get Selected item id
        switch (itemId) {
            case R.id.scan_production_order:
//                fragment = new BarcodeScanner();
                Toast.makeText(this, "Production Number", Toast.LENGTH_SHORT).show();
                break;
            case R.id.scan_supervisor:
                Toast.makeText(this, "Supervisor ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.scan_workstation:
                Toast.makeText(this, "Workstation", Toast.LENGTH_SHORT).show();
                break;
            case R.id.scan_mold:
                Toast.makeText(this, "Mold", Toast.LENGTH_SHORT).show();
                break;
            case R.id.scan_operators:
                Toast.makeText(this, "Operator ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.scan_helpers:
                Toast.makeText(this, "Helpers ", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "Unknown ", Toast.LENGTH_SHORT).show();
        }

        /** Work on Fragment **/
//        if (fragment != null)
//        {
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.content_frame,fragment);   // Replace Fragment xml in frame
//            fragmentTransaction.commit();
//            drawerLayout.closeDrawer(Gravity.LEFT);
//            return true;
//        }

        return false;
    }
}
