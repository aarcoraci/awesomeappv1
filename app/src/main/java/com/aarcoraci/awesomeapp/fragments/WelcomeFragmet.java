package com.aarcoraci.awesomeapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aarcoraci.awesomeapp.R;

/**
 * Created by Angel on 4/27/2015.
 */
public class WelcomeFragmet extends BaseFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_welcome, container, false);

        return v;
    }
}
