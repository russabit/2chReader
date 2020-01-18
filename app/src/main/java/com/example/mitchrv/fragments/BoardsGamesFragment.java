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

public class BoardsGamesFragment extends Fragment implements BoardsRecyclerViewAdapter.OnViewListener {

    //inject with dagger later?
    private ArrayList<String> boardCharsGames = new ArrayList<>();
    private ArrayList<String> boardNamesGames = new ArrayList<>();


    private BoardsInterface boardsInterface;

    {
        boardCharsGames.addAll(Arrays.asList("bg","cg","ruvn","tes","v","vg","wr"));
        boardNamesGames.addAll(Arrays.asList("Настольные игры","Консоли","Российские виз.новеллы","The Elder Scrolls","Video Games","Video Games General","Текстовые РПГ"));
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
        if (boardCharsGames.size() == 0 && boardNamesGames.size() == 0) {
            }
        BoardsRecyclerViewAdapter boardsRecyclerViewAdapter = new BoardsRecyclerViewAdapter(getActivity(), boardCharsGames, boardNamesGames, this);
        recyclerView.setAdapter(boardsRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        /*recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL));*/
    }

    @Override
    public void onViewClick(int position) {
        Timber.d("onViewClick: clicked! %s", boardCharsGames.get(position));
        boardsInterface.inflateThreadsFragment(boardCharsGames.get(position));
    }
}
