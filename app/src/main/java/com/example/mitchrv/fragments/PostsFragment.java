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
import com.example.mitchrv.adapters.MessagesRecyclerViewAdapter;
import com.example.mitchrv.adapters.ThreadsRecyclerViewAdapter;
import com.example.mitchrv.model.Messages;
import com.example.mitchrv.viewmodels.MessageRecyclerFragmentViewModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class PostsFragment extends Fragment implements ThreadsRecyclerViewAdapter.OnViewListener {

    private MessagesRecyclerViewAdapter msgRecyclerViewAdapter;
    private MessageRecyclerFragmentViewModel mMessageRecyclerFragmentViewModel;

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
        int num = getArguments().getInt("num");
        Timber.d("onCreateView: called %s", num);
        initImageBitmaps(view, num);
        return view;
    }

    private void initImageBitmaps(View view, int num) {
        Timber.d("initImageBitmaps called");
        mMessageRecyclerFragmentViewModel = ViewModelProviders.of(this)
                .get(MessageRecyclerFragmentViewModel.class);

        mMessageRecyclerFragmentViewModel.getMessages(num).subscribe(new Observer<Messages>() {

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
                msgRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
        initRecyclerView(view);
    }

    private void initRecyclerView(View view) {

        Timber.d("initRecyclerView: called");

        RecyclerView msgRecyclerView = view.findViewById(R.id.msg_recycler);
        msgRecyclerView.setHasFixedSize(false);
        msgRecyclerViewAdapter = new MessagesRecyclerViewAdapter(
                getActivity(),
                mMessageRecyclerFragmentViewModel.getComments(),
                mMessageRecyclerFragmentViewModel.getImageUrls(),
                this);
        msgRecyclerView.setAdapter(msgRecyclerViewAdapter);
        msgRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        //adds a divider line between the rows
        msgRecyclerView.addItemDecoration(
                new DividerItemDecoration(
                        getActivity(), LinearLayoutManager.VERTICAL));

    }

}
