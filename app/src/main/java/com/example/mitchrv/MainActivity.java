package com.example.mitchrv;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mitchrv.APIs.InterfaceMainActivity;
import com.example.mitchrv.fragments.MessagesRecyclerFragment;
import com.example.mitchrv.fragments.RecyclerFragment;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import timber.log.Timber;

public class MainActivity extends DaggerAppCompatActivity implements InterfaceMainActivity {
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
    public void inflateFragment(String imageUrl, String imageName, int name) {

        Timber.d("inflateFragment: called %s", name);

        Bundle args = new Bundle();
        args.putString("image_url", imageUrl);
        args.putString("image_name", imageName);
        args.putInt("num", name);

        navController.navigate(R.id.messagesRecyclerFragment, args);
    }
}
