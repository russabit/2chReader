package com.example.mitchrv.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mitchrv.APIs.InterfaceMainActivity;
import com.example.mitchrv.R;
import com.example.mitchrv.adapters.ThreadsRecyclerViewAdapter;
import com.example.mitchrv.viewmodels.RecyclerFragmentViewModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;


public class ThreadsFragment extends Fragment implements ThreadsRecyclerViewAdapter.OnViewListener {

    private ThreadsRecyclerViewAdapter recyclerViewAdapter;
    private InterfaceMainActivity mInterfaceMainActivity;
    private RecyclerFragmentViewModel mRecyclerFragmentViewModel;
    ProgressBar progressBar;
    boolean progressBarIsShowing;

    @Override
    public void onViewClick(int position) {
        Timber.d("onViewClick: clicked!");
        mInterfaceMainActivity.inflateFragment(
                mRecyclerFragmentViewModel.getImageUrls().get(position),
                mRecyclerFragmentViewModel.getNames().get(position),
                mRecyclerFragmentViewModel.getNums().get(position));
    }

    @Override
    public void onAttach(@NonNull Context context) {
            super.onAttach(context);
            mInterfaceMainActivity = (InterfaceMainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, null);

        initImageBitmaps(view);

        return view;
    }

    private void initImageBitmaps(View view) {


        //this or getActivity() inside of()
        mRecyclerFragmentViewModel = ViewModelProviders
                .of(this)
                .get(RecyclerFragmentViewModel.class);


        mRecyclerFragmentViewModel.init();

        //was observable.subscribe(...
        mRecyclerFragmentViewModel.getFeed().subscribe(new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                progressBar = view.findViewById(R.id.progressbar);
                if (!progressBarIsShowing) {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBarIsShowing = true;
                    Timber.d("pidor");
                }
            }

            @Override
            public void onNext(String path) {
                Timber.d("onNext: called");
                progressBar = view.findViewById(R.id.progressbar);
                progressBar.setVisibility(View.GONE);
                Timber.d("Called after progress bar killed");
            }

            @Override
            public void onError(Throwable e) { }

            @Override
            public void onComplete() {
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });

        initRecyclerView(view);

    }

    private void initRecyclerView(View view) {

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        recyclerViewAdapter = new ThreadsRecyclerViewAdapter(getActivity(),
                mRecyclerFragmentViewModel.getNames(),
                mRecyclerFragmentViewModel.getImageUrls(), this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //adds a divider line between the rows
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL));
    }

}
