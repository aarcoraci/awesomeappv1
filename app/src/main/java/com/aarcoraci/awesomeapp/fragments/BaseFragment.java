package com.aarcoraci.awesomeapp.fragments;

import android.support.v4.app.Fragment;

import com.aarcoraci.awesomeapp.MainActivity;

public class BaseFragment extends Fragment {

    public String TAG = "";
    protected AppSection section = AppSection.NONE;

    public BaseFragment() {
    }

    protected MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }

    public AppSection getAppSection(){
        return this.section;
    }
}



