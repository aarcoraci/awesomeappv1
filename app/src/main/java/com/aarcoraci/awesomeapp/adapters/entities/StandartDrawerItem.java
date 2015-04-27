package com.aarcoraci.awesomeapp.adapters.entities;

import com.aarcoraci.awesomeapp.fragments.AppSection;

/**
 * Created by Angel on 4/27/2015.
*/
public class StandartDrawerItem extends BaseDrawerItem {

    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public StandartDrawerItem(String label, AppSection appSection){
        this.label = label;
        this.appSection = appSection;
    }
}
