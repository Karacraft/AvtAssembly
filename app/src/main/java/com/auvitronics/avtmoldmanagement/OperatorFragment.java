package com.auvitronics.avtmoldmanagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.auvitronics.avtmoldmanagement.Interfaces.IFragmentOperations;
import com.auvitronics.avtmoldmanagement.Utilities.Constants;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OperatorFragment extends Fragment {

    Unbinder unbinder;
    String fragmentName;

    IFragmentOperations iFragmentOperations;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_operator,container,false);
        unbinder = ButterKnife.bind(this, view);

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
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
