package com.example.mitchrv.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mitchrv.R;
import com.example.mitchrv.adapters.PostsRecyclerViewAdapter;
import com.example.mitchrv.adapters.ThreadsRecyclerViewAdapter;
import com.example.mitchrv.model.Messages;
import com.example.mitchrv.viewmodels.PostsFragmentViewModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class PostsFragment extends Fragment implements ThreadsRecyclerViewAdapter.OnViewListener {

    private PostsRecyclerViewAdapter msgRecyclerViewAdapter;
    private PostsFragmentViewModel postsFragmentViewModel;

    @Override
    public void onViewClick(int position) {
        Timber.d("onViewClick: clicked!");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_messages_recycler, null);
        String boardChar = getArguments().getString("boardChar");
        int num = getArguments().getInt("num");
        Timber.d("onCreateView: called %s", num);
        initImageBitmaps(view, boardChar, num);
        return view;
    }

    private void initImageBitmaps(View view, String boardChar, int num) {
        Timber.d("initImageBitmaps called");
        postsFragmentViewModel = ViewModelProviders.of(this)
                .get(PostsFragmentViewModel.class);

        postsFragmentViewModel.getMessages(boardChar, num).subscribe(new Observer<Messages>() {

            @Override
            public void onSubscribe(Disposable d) { }

            @Override
            public void onNext(Messages s) {
                Timber.d("onNext: called");
            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e, "onError: called");
            }

            @Override
            public void onComplete() {
                initRecyclerView(view);
                msgRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

    }

    private void initRecyclerView(View view) {

        Timber.d("initRecyclerView: called");

        RecyclerView msgRecyclerView = view.findViewById(R.id.msg_recycler);
        msgRecyclerView.setHasFixedSize(false);
        msgRecyclerViewAdapter = new PostsRecyclerViewAdapter(
                getActivity(),
                postsFragmentViewModel.getComments(),
                postsFragmentViewModel.getImageUrls(),
                this);
        msgRecyclerView.setAdapter(msgRecyclerViewAdapter);
        msgRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        //adds a divider line between the rows
        msgRecyclerView.addItemDecoration(
                new DividerItemDecoration(
                        getActivity(), LinearLayoutManager.VERTICAL));

    }

}
