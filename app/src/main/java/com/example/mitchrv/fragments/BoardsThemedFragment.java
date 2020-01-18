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

public class BoardsThemedFragment extends Fragment implements BoardsRecyclerViewAdapter.OnViewListener {

    //inject with dagger later?
    private ArrayList<String> boardCharsThemed = new ArrayList<>();
    private ArrayList<String> boardNamesThemed = new ArrayList<>();

    private BoardsInterface boardsInterface;

    {
        boardCharsThemed.addAll(Arrays.asList("au", "bi", "biz", "c", "cc", "em", "fa", "fiz", "fl", "ftb", "hi", "me", "mg", "mlp", "mo", "mov","mu","ne","psy","re","sci","sf","sn","sp","spc","tv","un","w","wh","wm","wp","zog"));
        boardNamesThemed.addAll(Arrays.asList("Автомобили", "Велосипеды", "Бизнес", "Комиксы", "Криптовалюты", "Другие страны", "Мода и стиль", "Физкультура", "Ин.языки", "Футбол", "История", "Медицина", "Магия", "Пони", "Мотоциклы", "Фильмы", "Музыка", "Животные", "Психология", "Религия", "Наука", "Научная фантастика","Паранормальное","Спорт","Космос","Сериалы","Образование","Оружие","Warhammer","Военная техника","Обои и хайрез","Теории заговора"));
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
        if (boardCharsThemed.size() == 0 && boardNamesThemed.size() == 0) {
            }
        BoardsRecyclerViewAdapter boardsRecyclerViewAdapter = new BoardsRecyclerViewAdapter(getActivity(), boardCharsThemed, boardNamesThemed, this);
        recyclerView.setAdapter(boardsRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        /*recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL));*/
    }

    @Override
    public void onViewClick(int position) {
        Timber.d("onViewClick: clicked! %s", boardCharsThemed.get(position));
        boardsInterface.inflateThreadsFragment(boardCharsThemed.get(position));
    }
}
