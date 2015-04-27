package com.aarcoraci.awesomeapp.adapters.entities;

import com.aarcoraci.awesomeapp.fragments.AppSection;

/**
 * Created by Angel on 4/27/2015.
 */
public class BaseDrawerItem {
    protected AppSection appSection;

    public AppSection getAppSection() {
        return appSection;
    }

    public void setAppSection(AppSection appSection) {
        this.appSection = appSection;
    }
}
