package com.cvsr.photobank.photobank;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import com.cvsr.photobank.photobank.ImageList;
import com.cvsr.photobank.photobank.HelplineFragment;
import com.cvsr.photobank.photobank.R;

public class MenuActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private FloatingActionButton fab;


    // index to identify current nav menu item
    public static int navItemIndex = 0;

    private static  String TAG_FAB ="list";
    private static final String HELP_LINE = "helpline";
    private static final String TAG_FAMILY = "family";
    private static final String TAG_TACTICS = "tips";
    private static final String TAG_NAVIGATE = "navigate";
    public static String CURRENT_TAG = HELP_LINE;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler menuHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        menuHandler = new Handler();
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
//        fab = (FloatingActionButton) findViewById(R.id.fab);

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = HELP_LINE;
            loadHomeFragment();
        }
    }

    /*
     * Returns respected fragment that user
     * selected from navigation menu
     */

    private void loadHomeFragment() {

        selectNavMenu();

        setToolbarTitle();

        // if user select the current navigation menu again, close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            // show or hide the fab button
//            toggleFab();
            return;
        }


        // The fragment is loaded with cross fade effect using runnable
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            menuHandler.post(mPendingRunnable);
        }

//        toggleFab();

        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {

            case 1:
                HelplineFragment starredEventsFragment = new HelplineFragment();
                return starredEventsFragment;

            case 2:
                TipsFragment tipsFragment = new TipsFragment();
                return tipsFragment;

            default:
                return new HelplineFragment();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_helpline:
                        navItemIndex = 0;
                        CURRENT_TAG = HELP_LINE;
                        break;
                    case R.id.nav_tactics:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_TACTICS;
                        break;
                    case R.id.nav_profile:
                        startActivity(new Intent(MenuActivity.this, ImageList.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_share:
                        // launch new intent instead of loading fragment
                        // TODO : Share APP

                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


        android.support.v7.app.ActionBarDrawerToggle actionBarDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;///
        }

        if (shouldLoadHomeFragOnBackPress) {
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = HELP_LINE;
                loadHomeFragment();
                return;
            }
        }
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        // show menu only when home fragment is selected
        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.main, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

