package com.aarcoraci.awesomeapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aarcoraci.awesomeapp.R;
import com.aarcoraci.awesomeapp.adapters.entities.BaseDrawerItem;
import com.aarcoraci.awesomeapp.adapters.entities.StandartDrawerItem;

import java.util.Vector;

/**
 * Created by Angel on 4/27/2015.
 */
public class DrawerItemAdapter extends ArrayAdapter<BaseDrawerItem> {

    private Vector<BaseDrawerItem> items;
    private Context currentContext;


    public DrawerItemAdapter(Context context, int textViewResourceId,
                             Vector<BaseDrawerItem> items ) {
        super(context, textViewResourceId, items);
        this.items = items;
        this.currentContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;


        // section
        LayoutInflater vi = (LayoutInflater) currentContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = vi.inflate(R.layout.listviewitem_drawer_item, null);


        StandartDrawerItem currentItem = (StandartDrawerItem) items
                .get(position);

        if (currentItem != null) {
            TextView label = (TextView) v
                    .findViewById(R.id.listviewitem_drawer_item_label);
            label.setText(currentItem.getLabel());
        }

        return v;
    }


}
