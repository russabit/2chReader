package com.example.mitchrv.fragments;

import android.content.Context;
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

import com.example.mitchrv.APIs.BoardsInterface;
import com.example.mitchrv.APIs.InterfaceMainActivity;
import com.example.mitchrv.R;
import com.example.mitchrv.adapters.BoardsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import timber.log.Timber;

public class BoardsFragment extends Fragment implements BoardsRecyclerViewAdapter.OnViewListener {

    private ArrayList<String> boardChars = new ArrayList<>();
    private ArrayList<String> boardNames = new ArrayList<>();
    private BoardsInterface boardsInterface;

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
        if (boardChars.size() == 0 && boardNames.size() == 0) {
            boardChars.addAll(Arrays.asList("au", "bi", "biz", "c", "cc", "em", "fa", "fiz", "fl", "ftb", "hi", "me", "mg", "mlp", "mo", "mov","mu","ne","psy","re","sci","sf"));
            boardNames.addAll(Arrays.asList("Автомобили", "Велосипеды", "Бизнес", "Комиксы", "Криптовалюты", "Другие страны", "Мода и стиль", "Физкультура", "Ин.языки", "Футбол", "История", "Медицина", "Магия", "Пони", "Мотоциклы", "Фильмы", "Музыка", "Животные", "Психология", "Религия", "Наука", "Научная фантастика"));
        }
        BoardsRecyclerViewAdapter boardsRecyclerViewAdapter = new BoardsRecyclerViewAdapter(getActivity(), boardChars, boardNames, this);
        recyclerView.setAdapter(boardsRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL));
    }

    @Override
    public void onViewClick(int position) {
        Timber.d("onViewClick: clicked! %s", boardChars.get(position));
        boardsInterface.inflateThreadsFragment(boardChars.get(position));
    }
}
