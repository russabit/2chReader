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

    private ArrayList<String> boardCharsThemed = new ArrayList<>();
    private ArrayList<String> boardNamesThemed = new ArrayList<>();

    private ArrayList<String> boardCharsArt = new ArrayList<>();
    private ArrayList<String> boardNamesArt = new ArrayList<>();

    private ArrayList<String> boardCharsPoliticsAndNews = new ArrayList<>();
    private ArrayList<String> boardNamesPoliticsAndNews = new ArrayList<>();

    private ArrayList<String> boardCharsTechAndSoft = new ArrayList<>();
    private ArrayList<String> boardNamesTechAndSoft = new ArrayList<>();

    private ArrayList<String> boardCharsGames = new ArrayList<>();
    private ArrayList<String> boardNamesGames = new ArrayList<>();

    private ArrayList<String> boardCharsJapaneseCulture = new ArrayList<>();
    private ArrayList<String> boardNamesJapaneseCulture = new ArrayList<>();

    private ArrayList<String> boardCharsDifferent18plus = new ArrayList<>();
    private ArrayList<String> boardNamesDifferent18plus = new ArrayList<>();

    private ArrayList<String> boardCharsAdult18plus = new ArrayList<>();
    private ArrayList<String> boardNamesAdult18plus = new ArrayList<>();


    private BoardsInterface boardsInterface;

    {
        boardCharsThemed.addAll(Arrays.asList("au", "bi", "biz", "c", "cc", "em", "fa", "fiz", "fl", "ftb", "hi", "me", "mg", "mlp", "mo", "mov","mu","ne","psy","re","sci","sf","sn","sp","spc","tv","un","w","wh","wm","wp","zog"));
        boardNamesThemed.addAll(Arrays.asList("Автомобили", "Велосипеды", "Бизнес", "Комиксы", "Криптовалюты", "Другие страны", "Мода и стиль", "Физкультура", "Ин.языки", "Футбол", "История", "Медицина", "Магия", "Пони", "Мотоциклы", "Фильмы", "Музыка", "Животные", "Психология", "Религия", "Наука", "Научная фантастика","Паранормальное","Спорт","Космос","Сериалы","Образование","Оружие","Warhammer","Военная техника","Обои и хайрез","Теории заговора"));

        boardCharsArt.addAll(Arrays.asList("de","di","diy","mus","pa","p","wrk"));
        boardNamesArt.addAll(Arrays.asList("Дизайн", "Столовая","Хобби","Музыканты","Живопись","Фотография","Работа"));

        boardCharsPoliticsAndNews.addAll(Arrays.asList("po","news","int","hry"));
        boardNamesPoliticsAndNews.addAll(Arrays.asList("Политика", "Новости", "International","Х Р Ю"));

        boardCharsTechAndSoft.addAll(Arrays.asList("gd","hw","mobi","pr","ra","s","t"));
        boardNamesTechAndSoft.addAll(Arrays.asList("Gamedev","Комп. железо","Моб. устройства","Программирование","Радиотехника","Программы","Техника"));

        boardCharsGames.addAll(Arrays.asList("bg","cg","ruvn","tes","v","vg","wr"));
        boardNamesGames.addAll(Arrays.asList("Настольные игры","Консоли","Российские виз.новеллы","The Elder Scrolls","Video Games","Video Games General","Текстовые РПГ"));

        boardCharsJapaneseCulture.addAll(Arrays.asList("a","fd","ja","ma","vn"));
        boardNamesJapaneseCulture.addAll(Arrays.asList("Аниме","Фэндом","Японская культура","Манга","Визуальные новеллы"));

        boardCharsDifferent18plus.addAll(Arrays.asList("d","b","soc","media","r","api","rf","o"));
        boardNamesDifferent18plus.addAll(Arrays.asList("Дискуссии о Два.ч","Бред","Общение","Анимация","Реквесты","API","Убежище","Рисовач"));

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
