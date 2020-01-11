package com.example.mitchrv.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mitchrv.APIs.InterfaceMainActivity;
import com.example.mitchrv.R;
import com.example.mitchrv.adapters.BoardsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import timber.log.Timber;

public class BoardsFragment extends Fragment implements BoardsRecyclerViewAdapter.OnViewListener {

    ArrayList<String> boardChars = new ArrayList<>();
    ArrayList<String> boardNames = new ArrayList<>();

    BoardsRecyclerViewAdapter boardsRecyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_boards_recycler, null);
        initRecyclerView(view);
        Timber.d("Хуй соси");
        return view;
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.boards_recycler);
        boardChars.addAll(Arrays.asList("pr", "b", "vg", "soc", "trv", "sex", "tes"));
        boardNames.addAll(Arrays.asList("Программирование", "Бред", "Видеоигры", "Social", "Путешествия", "Секс и отношения", "The Elder Scrolls"));
        boardsRecyclerViewAdapter = new BoardsRecyclerViewAdapter(getActivity(), boardChars, boardNames, this);
        recyclerView.setAdapter(boardsRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL));
    }

    @Override
    public void onViewClick(int position) {
        Timber.d("onViewClick: clicked!");
    }
}
