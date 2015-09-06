package com.lyt.business.app;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.lyt.business.R;
import com.lyt.business.fragment.MsgFragment;
import com.lyt.business.fragment.OrderManaFragment;
import com.lyt.business.fragment.ResSettingFragment;
import com.lyt.business.fragment.SaleFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.main_container)
    FrameLayout mainContainer;
    @Bind(R.id.navigationView)
    NavigationView navigationView;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    Fragment[] fragments={OrderManaFragment.newInstance("",""), MsgFragment.newInstance("",""), SaleFragment.newInstance("","")};;

    boolean isReplace=true;
    Fragment currentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("管饱");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                toolbar.setTitle(menuItem.getTitle());
                drawerLayout.closeDrawers();
                switch (menuItem.getItemId()) {

                    case R.id.drawer_order_manager:
                        switchFragment(fragments[0], 0);
                        break;
                    case R.id.drawer_msg:
                        switchFragment(fragments[1], 1);
                        break;
                    case R.id.drawer_sale:
                        switchFragment(fragments[2], 2);
                        break;
                }
                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                toolbar.setTitle("管饱");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }

        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //getSupportFragmentManager().beginTransaction().add(R.id.main_container, ResSettingFragment.newInstance()).commit();
        currentFragment=ResSettingFragment.newInstance();
        switchFragment(currentFragment,0);
    }

    private void initData() {


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    private void switchFragment(Fragment to,int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (!to.isAdded()) {
            if(isReplace){
                ft.add(R.id.main_container, to).commit();
                isReplace=false;
            }else {
                ft.hide(currentFragment).add(R.id.main_container, to).commit();
            }
        } else {
            ft.hide(currentFragment).show(to).commit();
        }
        currentFragment=to;
        switch (index){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    interface onFragmentShowListenter {
        void onFragmentShow(Fragment fragment);
    }
}
