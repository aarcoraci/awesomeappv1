package com.aarcoraci.awesomeapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.aarcoraci.awesomeapp.adapters.DrawerItemAdapter;
import com.aarcoraci.awesomeapp.adapters.entities.BaseDrawerItem;
import com.aarcoraci.awesomeapp.adapters.entities.StandartDrawerItem;
import com.aarcoraci.awesomeapp.fragments.AppSection;
import com.aarcoraci.awesomeapp.fragments.BaseFragment;
import com.aarcoraci.awesomeapp.fragments.WelcomeFragmet;

import java.util.List;
import java.util.Vector;


public class MainActivity extends ActionBarActivity implements  ListView.OnItemClickListener {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private LinearLayout mDrawerLeft;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar defaultToolbar;
    private DrawerItemAdapter drawerAdapter;

    private FragmentManager fragmentManager;
    private Vector<BaseDrawerItem> drawerItems = new Vector<BaseDrawerItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defaultToolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        fragmentManager = getSupportFragmentManager();

        // app bar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.app_name));


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLeft = (LinearLayout) findViewById(R.id.drawer_content_wrapper);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        setUpDrawerItems();

        drawerAdapter = new DrawerItemAdapter(this, mDrawerList.getId(),
                drawerItems);

        mDrawerList.setAdapter(drawerAdapter);
        mDrawerList.setOnItemClickListener(this);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open_desc, R.string.drawer_close_desc) {
            @Override
            public void onDrawerClosed(View drawerView) {
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            switchFragment(AppSection.WELCOME, false);
        }
    }

    private void setUpDrawerItems() {

        drawerItems.clear();

        drawerItems.add(new StandartDrawerItem("Products",
                AppSection.CATEGORIES));
    }

    public void switchFragment(AppSection section, boolean addToBackStack) {
        switchFragment(section, addToBackStack, null);
    }

    public void switchFragment(AppSection section, boolean addToBackStack,
                               Bundle args) {

        // this code will avoid to re-navigate to a section if that section is the one visble
        if (getCurrentAppSection() == section) {
            mDrawerLayout.closeDrawer(mDrawerLeft);
            return;
        }

        // hide soft keyboard when changing fragments, without this the app can look unprofessional
        View focus = getCurrentFocus();
        if (focus != null) {
            hiddenKeyboard(focus);
        }

        // create the correct instance depending on the section we want to open
        BaseFragment fragment = null;

        switch (section) {
            case WELCOME:
                fragment = new WelcomeFragmet();
                break;
            default:
                break;
        }

        // UI indicating this section is selected on the drawer
        setDrawerSelected(fragment.getAppSection());

        /*
         if There is a fragment identified by this tag on the stack
         we will bring that one up. We won't be creating a new instance
         every time a section is visited. Doing this will make the stack
         go back to that position hence making the back key navigation easier.
         If you keep creating new instances you would be filling a potentially
         large stack and exiting the app can be problematic (not to mention duplicate sections
         on the back navigation)
         */
        boolean fragmentExist = fragmentManager.popBackStackImmediate(fragment.TAG, 0);
        if (fragmentExist) {
            // go to this found state, nothing to do
        } else {

            /*
            the fragment is not on the stack. This means we will create a new one
            and pass the arguments if required. It's important you add the fragment into the stack
            (if the parameter says so) using the corresponding tag (defined on the constructor of
            each fragment)
             */
            if (args != null)
                fragment.setArguments(args);

            FragmentTransaction transaction = fragmentManager
                    .beginTransaction();
            transaction.replace(R.id.content_frame, fragment, fragment.TAG);

            // some times you may not want the transaction to be added, use the method parameter for this
            if (addToBackStack)
                transaction.addToBackStack(fragment.TAG);

            transaction.commit();
        }
    }


    private AppSection getCurrentAppSection() {

        /*
        this method will return the current section
        to be highlighted on the drawer. Notice that multiple
        fragments may share a same section. This is the place to add that logic.
         */

        AppSection result = AppSection.NONE;

        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments == null || fragments.size() == 0)
            return result;

        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isVisible()) {
                if (fragment instanceof WelcomeFragmet)
                    result = AppSection.WELCOME;
            }
        }

        return result;
    }

    public void setDrawerSelected(AppSection section) {
        mDrawerList.setItemChecked(getDrawerItemPosition(section), true);
    }

    public int getDrawerItemPosition(AppSection section) {
        /*
            since the drawer array is dynamic, each position can be different
            depending on the type of app you are building. This method will
            give you the index of the section on the drawer without needing to
            hard code it
         */
        int result = -1;
        for (int i = 0; i < drawerItems.size(); i++) {
            if (drawerItems.get(i).getAppSection() == section) {
                result = i;
                break;
            }
        }
        return result;
    }

    public void hiddenKeyboard(View v) {
        InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
