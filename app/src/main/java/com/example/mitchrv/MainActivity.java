package com.example.mitchrv;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.mitchrv.APIs.BoardsInterface;
import com.example.mitchrv.APIs.InterfaceMainActivity;
import com.google.android.material.navigation.NavigationView;

import dagger.android.support.DaggerAppCompatActivity;
import timber.log.Timber;

public class MainActivity extends DaggerAppCompatActivity implements InterfaceMainActivity, BoardsInterface, NavigationView.OnNavigationItemSelectedListener {
    //vars
    NavController navController;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
            {

                public void onDrawerClosed(View view)
                {
                    supportInvalidateOptionsMenu();
                    //drawerOpened = false;
                }

                public void onDrawerOpened(View drawerView)
                {
                    supportInvalidateOptionsMenu();
                    //drawerOpened = true;
                }
            };
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            drawerLayout.addDrawerListener(mDrawerToggle);
            mDrawerToggle.syncState();

        }

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

/*        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();*/

        getSupportFragmentManager().registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
            @Override
            public void onFragmentPreAttached(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull Context context) {
                super.onFragmentPreAttached(fm, f, context);
                Timber.i("Fragment %s onFragmentPreAttached", Fragment.class.getSimpleName());
            }

            @Override
            public void onFragmentAttached(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull Context context) {
                super.onFragmentAttached(fm, f, context);
                Timber.i("Fragment %s onFragmentAttached", Fragment.class.getSimpleName());

            }

            @Override
            public void onFragmentPreCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @Nullable Bundle savedInstanceState) {
                super.onFragmentPreCreated(fm, f, savedInstanceState);
                Timber.i("Fragment %s onFragmentPreCreated", Fragment.class.getSimpleName());

            }

            @Override
            public void onFragmentCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @Nullable Bundle savedInstanceState) {
                super.onFragmentCreated(fm, f, savedInstanceState);
                Timber.i("Fragment %s onFragmentCreated", Fragment.class.getSimpleName());

            }

            @Override
            public void onFragmentActivityCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @Nullable Bundle savedInstanceState) {
                super.onFragmentActivityCreated(fm, f, savedInstanceState);
                Timber.i("Fragment %s onFragmentActivityCreated", Fragment.class.getSimpleName());

            }

            @Override
            public void onFragmentViewCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull View v, @Nullable Bundle savedInstanceState) {
                super.onFragmentViewCreated(fm, f, v, savedInstanceState);
                Timber.i("Fragment %s onFragmentViewCreated", Fragment.class.getSimpleName());

            }

            @Override
            public void onFragmentStarted(@NonNull FragmentManager fm, @NonNull Fragment f) {
                super.onFragmentStarted(fm, f);
                Timber.i("Fragment %s onFragmentStarted", Fragment.class.getSimpleName());

            }

            @Override
            public void onFragmentResumed(@NonNull FragmentManager fm, @NonNull Fragment f) {
                super.onFragmentResumed(fm, f);
                Timber.i("Fragment %s onFragmentResumed", Fragment.class.getSimpleName());

            }

            @Override
            public void onFragmentPaused(@NonNull FragmentManager fm, @NonNull Fragment f) {
                super.onFragmentPaused(fm, f);
                Timber.i("Fragment %s onFragmentPaused", Fragment.class.getSimpleName());

            }

            @Override
            public void onFragmentStopped(@NonNull FragmentManager fm, @NonNull Fragment f) {
                super.onFragmentStopped(fm, f);
                Timber.i("Fragment %s onFragmentStopped", Fragment.class.getSimpleName());

            }

            @Override
            public void onFragmentSaveInstanceState(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull Bundle outState) {
                super.onFragmentSaveInstanceState(fm, f, outState);
                Timber.i("Fragment %s onFragmentSaveInstanceState", Fragment.class.getSimpleName());

            }

            @Override
            public void onFragmentViewDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
                super.onFragmentViewDestroyed(fm, f);
                Timber.i("Fragment %s onFragmentViewDestroyed", Fragment.class.getSimpleName());

            }

            @Override
            public void onFragmentDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
                super.onFragmentDestroyed(fm, f);
                Timber.i("Fragment %s onFragmentDestroyed", Fragment.class.getSimpleName());

            }

            @Override
            public void onFragmentDetached(@NonNull FragmentManager fm, @NonNull Fragment f) {
                super.onFragmentDetached(fm, f);
                Timber.i("Fragment %s onFragmentDetached", Fragment.class.getSimpleName());

            }
        }, false);

        if (navController == null)
            navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else super.onBackPressed();
    }

    @Override
    public void inflateFragment(String boardChar, String imageUrl, String imageName, int name) {

        Timber.d("inflateFragment: called %s", name);

        Bundle args = new Bundle();
        args.putString("boardChar", boardChar);
        args.putString("image_url", imageUrl);
        args.putString("image_name", imageName);
        args.putInt("num", name);

        navController.navigate(R.id.postsFragment, args);
    }

    @Override
    public void inflateThreadsFragment(String boardChar) {
        Timber.d("inflateThreadsFragment called %s", boardChar);

        Bundle args = new Bundle();
        args.putString("boardChar", boardChar);

        navController.navigate(R.id.threadsFragment, args);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Bundle args = new Bundle();
        switch (menuItem.getItemId()) {
            case R.id.themed :
                args.putString("boardGroup", "Themed");
                break;
            case R.id.art :
                args.putString("boardGroup", "Art");
                break;
            case R.id.politics :
                args.putString("boardGroup", "Politics");
                break;
            case R.id.tech :
                args.putString("boardGroup", "Tech");
                break;
            case R.id.games :
                args.putString("boardGroup", "Games");
                break;
            case R.id.japan :
                args.putString("boardGroup", "Japan");
                break;
            case R.id.different18 :
                args.putString("boardGroup", "Diff");
                break;
            case R.id.adult18 :
                args.putString("boardGroup", "Adult");
                break;
        }
        navController.navigate(R.id.boardsFragment, args);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
