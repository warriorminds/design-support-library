package minds.warrior.com.designsupportlibrary;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private final long DRAWER_CLOSE_DELAY_MS = 300;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;
    private final Handler mDrawerActionHandler = new Handler();
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Setup your toolbar */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        /* Setup your drawer toggle*/
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
        /* Add your toggle to be the drawer layout listener. */
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        /* Set your NavigationItemSelectedListener to handle clicks to the menu. */
        mNavigationView.setNavigationItemSelectedListener(this);
        /* Don't forget the toggle syncState! */
        mDrawerToggle.syncState();

        /* Setup FAB */
        setupFab();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(final MenuItem menuItem) {

        /* Close drawer and use a Handler to make something when the user clicks an item.
        *  Typically it will be open a new activity or change the fragment that is showing.
        * */
        mDrawerLayout.closeDrawers();
        mDrawerLayout.closeDrawer(GravityCompat.START);
        mDrawerActionHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, menuItem.getTitle() + "", Toast.LENGTH_SHORT).show();
                if (menuItem.getItemId() == R.id.nav_first_item)
                    startActivity(new Intent(MainActivity.this, CollapsingActivity.class));
                else if(menuItem.getItemId() == R.id.nav_second_item)
                    startActivity(new Intent(MainActivity.this, TabsActivity.class));
            }
        }, DRAWER_CLOSE_DELAY_MS);
        return false;
    }

    /* Setup floating action button */
    public void setupFab(){
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Setup snackbar. The first parameter is the Snackbar parent.
                * You can do anything you want in the action onClick listener.
                * */
                Snackbar
                        .make(v, "This is a snackbar", Snackbar.LENGTH_SHORT)
                        .setAction("Snackbar action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "Snackbar action", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }
}
