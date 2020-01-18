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

public class BoardsArtFragment extends Fragment implements BoardsRecyclerViewAdapter.OnViewListener {

    //inject with dagger later?

    private ArrayList<String> boardCharsArt = new ArrayList<>();
    private ArrayList<String> boardNamesArt = new ArrayList<>();



    private BoardsInterface boardsInterface;

    {
        boardCharsArt.addAll(Arrays.asList("de","di","diy","mus","pa","p","wrk"));
        boardNamesArt.addAll(Arrays.asList("Дизайн", "Столовая","Хобби","Музыканты","Живопись","Фотография","Работа"));

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
        if (boardCharsArt.size() == 0 && boardNamesArt.size() == 0) {
            }
        BoardsRecyclerViewAdapter boardsRecyclerViewAdapter = new BoardsRecyclerViewAdapter(getActivity(), boardCharsArt, boardNamesArt, this);
        recyclerView.setAdapter(boardsRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        /*recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL));*/
    }

    @Override
    public void onViewClick(int position) {
        Timber.d("onViewClick: clicked! %s", boardCharsArt.get(position));
        boardsInterface.inflateThreadsFragment(boardCharsArt.get(position));
    }
}
