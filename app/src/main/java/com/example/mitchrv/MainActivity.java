package com.example.mitchrv;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.mitchrv.APIs.BoardsInterface;
import com.example.mitchrv.APIs.InterfaceMainActivity;

import dagger.android.support.DaggerAppCompatActivity;
import timber.log.Timber;

public class MainActivity extends DaggerAppCompatActivity implements InterfaceMainActivity, BoardsInterface {
    //vars
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    // Shows the system bars by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

}
