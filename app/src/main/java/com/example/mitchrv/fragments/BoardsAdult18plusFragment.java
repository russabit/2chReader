package com.example.mitchrv.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mitchrv.APIs.BoardsInterface;
import com.example.mitchrv.R;
import com.example.mitchrv.adapters.BoardsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import timber.log.Timber;

public class BoardsAdult18plusFragment extends Fragment implements BoardsRecyclerViewAdapter.OnViewListener {

    //inject with dagger later?
    private ArrayList<String> boardCharsAdult18plus = new ArrayList<>();
    private ArrayList<String> boardNamesAdult18plus = new ArrayList<>();


    private BoardsInterface boardsInterface;

    {
        boardCharsAdult18plus.addAll(Arrays.asList("fg","fur","gg","ga","hc","e","fet","sex","fag"));
        boardNamesAdult18plus.addAll(Arrays.asList("Трапы","Фурри","Красивые девушки","Геи","Hardcore","Extreme pron","Фетиш","Секс и отношения","Фагготрия"));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        boardsInterface = (BoardsInterface) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_boards_recycler, null);
        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.boards_recycler);
        if (boardCharsAdult18plus.size() == 0 && boardNamesAdult18plus.size() == 0) {
            }
        BoardsRecyclerViewAdapter boardsRecyclerViewAdapter = new BoardsRecyclerViewAdapter(getActivity(), boardCharsAdult18plus, boardNamesAdult18plus, this);
        recyclerView.setAdapter(boardsRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        /*recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL));*/
    }

    @Override
    public void onViewClick(int position) {
        Timber.d("onViewClick: clicked! %s", boardCharsAdult18plus.get(position));
        boardsInterface.inflateThreadsFragment(boardCharsAdult18plus.get(position));
    }
}
