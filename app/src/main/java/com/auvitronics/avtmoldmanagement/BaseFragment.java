package com.auvitronics.avtmoldmanagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.auvitronics.avtmoldmanagement.Utilities.Helper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.laodingView)
    LinearLayout laodingView;
    @BindView(R.id.scrollViewMain)
    ScrollView scrollViewMain;


    public BaseFragment() {
        // Required Empty Public Constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        boolean response = Helper.loadData(getContext());

        if (response) {
            setupView();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setupView() {
        laodingView.setVisibility(View.GONE);
        scrollViewMain.setVisibility(View.VISIBLE);
    }


}
