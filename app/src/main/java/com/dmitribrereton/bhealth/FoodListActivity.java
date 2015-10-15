package com.dmitribrereton.bhealth;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class FoodListActivity extends AppCompatActivity implements SearchManager.OnDismissListener, SearchManager.OnCancelListener {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        mToolbar = (Toolbar) findViewById(R.id.actionbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        handleIntent(getIntent());

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_food_list, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);
        searchManager.setOnDismissListener(this);
        searchManager.setOnCancelListener(this);
        SearchView searchView =
                (SearchView)menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        MenuItemCompat.setOnActionExpandListener(menu.findItem(R.id.search),
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        FoodListFragment fragment = (FoodListFragment) getSupportFragmentManager().findFragmentById(R.id.foodListFragment);
                        fragment.getArguments().putString("query", null);
                        fragment.setupAdapter();
                        return true;
                    }
                });
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.meal_info) {
            startActivity(new Intent(getApplicationContext(), MealInfoActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            FoodListFragment fragment = (FoodListFragment) getSupportFragmentManager().findFragmentById(R.id.foodListFragment);
            fragment.getArguments().putString("query", query);
            fragment.setupAdapter();
        }
    }

    @Override
    public void onDismiss() {
        FoodListFragment fragment = (FoodListFragment) getSupportFragmentManager().findFragmentById(R.id.foodListFragment);
        fragment.getArguments().putString("query", null);
        fragment.setupAdapter();
    }

    @Override
    public void onCancel() {
        FoodListFragment fragment = (FoodListFragment) getSupportFragmentManager().findFragmentById(R.id.foodListFragment);
        fragment.getArguments().putString("query", null);
        fragment.setupAdapter();
    }
}
