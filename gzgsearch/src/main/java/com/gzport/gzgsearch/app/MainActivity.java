package com.gzport.gzgsearch.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import android.widget.ImageView;

import com.example.administrator.myapplication.R;
import com.gzport.gzgsearch.fragment.DayNightPlanFragment;
import com.gzport.gzgsearch.fragment.EightHourFragment;
import com.gzport.gzgsearch.fragment.GoalsFragment;
import com.gzport.gzgsearch.fragment.ImportantShipFragment;
import com.gzport.gzgsearch.fragment.ImportantStockFragment;
import com.gzport.gzgsearch.fragment.LoadAndUnLoadFragment;
import com.gzport.gzgsearch.fragment.SectionFragment;
import com.gzport.gzgsearch.fragment.ShipWorkFragment;
import com.gzport.gzgsearch.fragment.StockFragment;
import com.gzport.gzgsearch.fragment.ThroughPutViewFragment;
import com.gzport.gzgsearch.fragment.TransportFragment;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ImageView headImageView;
    /**
     * 菜单数组
     */
    String[] menuStrings;
    /**
     * 当前选定的的界面
     */
    int currentSelect=0;
    /**
     * 所有的fragment
     */
    Map<Integer,Fragment> fragmentMap;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initView() {
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(menuStrings[currentSelect]);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);

        }

        navigationView= (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                toolbar.setTitle(menuItem.getTitle());
                drawerLayout.closeDrawers();
                currentSelect = menuItem.getOrder();
                switch (menuItem.getItemId()) {
                    case R.id.goal:
                        switchFragment(GoalsFragment.newInstance("", ""));
                        break;
                    case R.id.shipwork:
                        switchFragment(ShipWorkFragment.newInstance("", ""));
                        break;
                    case R.id.importantStock:
                        switchFragment(ImportantStockFragment.newInstance("", ""));
                        break;
                    case R.id.eighthour:
                        switchFragment(EightHourFragment.newInstance("", ""));
                        break;
                    case R.id.dayAndNight:
                        switchFragment(DayNightPlanFragment.newInstance("", ""));
                        break;
                    case R.id.throughput:
                        switchFragment(ThroughPutViewFragment.newInstance("", ""));
                        break;
                    case R.id.section:
                        switchFragment(SectionFragment.newInstance("", ""));
                        break;
                    case R.id.loadAndUnload:
                        switchFragment(LoadAndUnLoadFragment.newInstance("", ""));
                        break;

                }
                return true;
            }
        });

        drawerLayout= (DrawerLayout) findViewById(R.id.drawerLayout);

        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.main_drawer_open,R.string.main_drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                toolbar.setTitle("手机业务查询系统");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }

        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        /**
         *
         */
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_container, (Fragment) fragmentMap.get(0)).commit();

        headImageView= (ImageView) findViewById(R.id.headImage);

    }

    private void initData() {
        menuStrings=getResources().getStringArray(R.array.menu);
        fragmentMap=new HashMap<>();
        fragmentMap.put(0, ShipWorkFragment.newInstance("", ""));//重点大船作业
        fragmentMap.put(1,GoalsFragment.newInstance("", ""));//任务目标
        fragmentMap.put(2,DayNightPlanFragment.newInstance("", "")); //昼夜计划
        fragmentMap.put(3,StockFragment.newInstance("", "")); //库存情况
        fragmentMap.put(4,TransportFragment.newInstance("", "")); //输运情况
        fragmentMap.put(5,SectionFragment.newInstance("", "")); //段结表查询
        fragmentMap.put(6,EightHourFragment.newInstance("", "")); //工班计划
        fragmentMap.put(7, ThroughPutViewFragment.newInstance("", ""));//吞吐量
        fragmentMap.put(8,ImportantShipFragment.newInstance("", ""));//装卸货
        fragmentMap.put(9, ImportantStockFragment.newInstance("", ""));//重点货物库存

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent=new Intent();
        switch (id){
            case R.id.action_settings:
                intent.setClass(this,SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.action_about:
                intent.setClass(this,AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.action_loginOut:
                this.finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void switchFragment(Fragment fragment){
        if(fragment.isAdded()){
            getSupportFragmentManager().beginTransaction().show(fragment).commit();
        }else {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
        }
    }
}
