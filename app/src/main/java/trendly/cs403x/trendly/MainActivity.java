package trendly.cs403x.trendly;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView menuListView;
    private String[] menuListItems;
    private String activityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityTitle = getTitle().toString();
        menuListItems = getResources().getStringArray(R.array.hamburger_menu_list_items);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        menuListView = (ListView) findViewById(R.id.menu_list_drawer);

        if (savedInstanceState == null) {
            MyFragment myFragment = new MyFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_layout_container, myFragment, null)
                    .commit();
        }

        setupNavMenu();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorites) {
            Intent i = new Intent(MainActivity.this, FavoritesActivity.class);
            startActivity(i);
        }

        if (id == R.id.action_cart) {
            Intent i = new Intent(MainActivity.this, CartActivity.class);
            startActivity(i);
        }

        // Activate the navigation drawer toggle
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void setupNavMenu() {
        menuListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menuListItems));
        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Load fragment based on clicked item.
                MyFragment myFragment = new MyFragment();
                BlankFragment blankFragment = new BlankFragment();
                switch (position) {
                    case 0:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_layout_container, myFragment, null)
                                .commit();
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_layout_container, blankFragment, null)
                                .commit();
                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_layout_container, blankFragment, null)
                                .commit();
                        break;
                    case 3:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_layout_container, blankFragment, null)
                                .commit();
                        break;
                    case 4:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_layout_container, blankFragment, null)
                                .commit();
                        break;
                    default:
                        break;
                }
                drawerLayout.closeDrawer(menuListView);
            }
        });

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.menu_open, R.string.menu_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Menu");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(activityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.removeDrawerListener(drawerToggle);
        drawerLayout.addDrawerListener(drawerToggle);
    }

}
