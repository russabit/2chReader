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

public class BoardsTechAndSoftFragment extends Fragment implements BoardsRecyclerViewAdapter.OnViewListener {

    //inject with dagger later?
    private ArrayList<String> boardCharsTechAndSoft = new ArrayList<>();
    private ArrayList<String> boardNamesTechAndSoft = new ArrayList<>();

    private BoardsInterface boardsInterface;

    {
        boardCharsTechAndSoft.addAll(Arrays.asList("gd","hw","mobi","pr","ra","s","t"));
        boardNamesTechAndSoft.addAll(Arrays.asList("Gamedev","Комп. железо","Моб. устройства","Программирование","Радиотехника","Программы","Техника"));
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
        if (boardCharsTechAndSoft.size() == 0 && boardNamesTechAndSoft.size() == 0) {
            }
        BoardsRecyclerViewAdapter boardsRecyclerViewAdapter = new BoardsRecyclerViewAdapter(getActivity(), boardCharsTechAndSoft, boardNamesTechAndSoft, this);
        recyclerView.setAdapter(boardsRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        /*recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL));*/
    }

    @Override
    public void onViewClick(int position) {
        Timber.d("onViewClick: clicked! %s", boardCharsTechAndSoft.get(position));
        boardsInterface.inflateThreadsFragment(boardCharsTechAndSoft.get(position));
    }
}
