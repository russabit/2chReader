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

    //inject with dagger later?
    private ArrayList<String> boardChars = new ArrayList<>();
    private ArrayList<String> boardNames = new ArrayList<>();

    {
        boardChars.addAll(Arrays.asList("au", "bi", "biz", "c", "cc", "em", "fa", "fiz", "fl", "ftb", "hi", "me", "mg", "mlp", "mo", "mov","mu","ne","psy","re","sci","sf","sn","sp","spc","tv","un","w","wh","wm","wp","zog"));
        boardNames.addAll(Arrays.asList("Автомобили", "Велосипеды", "Бизнес", "Комиксы", "Криптовалюты", "Другие страны", "Мода и стиль", "Физкультура", "Ин.языки", "Футбол", "История", "Медицина", "Магия", "Пони", "Мотоциклы", "Фильмы", "Музыка", "Животные", "Психология", "Религия", "Наука", "Научная фантастика","Паранормальное","Спорт","Космос","Сериалы","Образование","Оружие","Warhammer","Военная техника","Обои и хайрез","Теории заговора"));
    }

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
        Timber.d("new ThemedFrag");
        if (savedInstanceState==null) {
            boardChars.addAll(Arrays.asList("au", "bi", "biz", "c", "cc", "em", "fa", "fiz", "fl", "ftb", "hi", "me", "mg", "mlp", "mo", "mov","mu","ne","psy","re","sci","sf","sn","sp","spc","tv","un","w","wh","wm","wp","zog"));
            boardNames.addAll(Arrays.asList("Автомобили", "Велосипеды", "Бизнес", "Комиксы", "Криптовалюты", "Другие страны", "Мода и стиль", "Физкультура", "Ин.языки", "Футбол", "История", "Медицина", "Магия", "Пони", "Мотоциклы", "Фильмы", "Музыка", "Животные", "Психология", "Религия", "Наука", "Научная фантастика","Паранормальное","Спорт","Космос","Сериалы","Образование","Оружие","Warhammer","Военная техника","Обои и хайрез","Теории заговора"));
        }
            String boardGroup;
            if (getArguments() != null) {
                boardGroup = getArguments().getString("boardGroup");
                boardChars.clear();
                boardNames.clear();
                switch (boardGroup) {
                    case "Themed": {
                        boardChars.addAll(Arrays.asList("au", "bi", "biz", "c", "cc", "em", "fa", "fiz", "fl", "ftb", "hi", "me", "mg", "mlp", "mo", "mov","mu","ne","psy","re","sci","sf","sn","sp","spc","tv","un","w","wh","wm","wp","zog"));
                        boardNames.addAll(Arrays.asList("Автомобили", "Велосипеды", "Бизнес", "Комиксы", "Криптовалюты", "Другие страны", "Мода и стиль", "Физкультура", "Ин.языки", "Футбол", "История", "Медицина", "Магия", "Пони", "Мотоциклы", "Фильмы", "Музыка", "Животные", "Психология", "Религия", "Наука", "Научная фантастика","Паранормальное","Спорт","Космос","Сериалы","Образование","Оружие","Warhammer","Военная техника","Обои и хайрез","Теории заговора"));
                        break;
                    }
                    case "Art": {
                        boardChars.addAll(Arrays.asList("de", "di", "diy", "mus", "pa", "p", "wrk"));
                        boardNames.addAll(Arrays.asList("Дизайн", "Столовая", "Хобби", "Музыканты", "Живопись", "Фотография", "Работа"));
                        break;
                    }
                    case "Politics": {
                        boardChars.addAll(Arrays.asList("po","news","int","hry"));
                        boardNames.addAll(Arrays.asList("Политика", "Новости", "International","Х Р Ю"));
                        break;
                    }
                    case "Tech": {
                        boardChars.addAll(Arrays.asList("gd","hw","mobi","pr","ra","s","t"));
                        boardNames.addAll(Arrays.asList("Gamedev","Комп. железо","Моб. устройства","Программирование","Радиотехника","Программы","Техника"));
                        break;
                    }
                    case "Games": {
                        boardChars.addAll(Arrays.asList("bg","cg","ruvn","tes","v","vg","wr"));
                        boardNames.addAll(Arrays.asList("Настольные игры","Консоли","Российские виз.новеллы","The Elder Scrolls","Video Games","Video Games General","Текстовые РПГ"));
                        break;
                    }
                    case "Japan": {
                        boardChars.addAll(Arrays.asList("a","fd","ja","ma","vn"));
                        boardNames.addAll(Arrays.asList("Аниме","Фэндом","Японская культура","Манга","Визуальные новеллы"));
                        break;
                    }
                    case "Diff": {
                        boardChars.addAll(Arrays.asList("d","b","soc","media","r","api","rf","o"));
                        boardNames.addAll(Arrays.asList("Дискуссии о Два.ч","Бред","Общение","Анимация","Реквесты","API","Убежище","Рисовач"));
                        break;
                    }
                    case "Adult": {
                        boardChars.addAll(Arrays.asList("fg","fur","gg","ga","hc","e","fet","sex","fag"));
                        boardNames.addAll(Arrays.asList("Трапы","Фурри","Красивые девушки","Геи","Hardcore","Extreme pron","Фетиш","Секс и отношения","Фагготрия"));
                        break;
                    }
                }
            }
        return view;
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.boards_recycler);
        BoardsRecyclerViewAdapter boardsRecyclerViewAdapter = new BoardsRecyclerViewAdapter(getActivity(), boardChars, boardNames, this);
        recyclerView.setAdapter(boardsRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        /*recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL));*/
    }

    @Override
    public void onViewClick(int position) {
        Timber.d("onViewClick: clicked! %s", boardChars.get(position));
        boardsInterface.inflateThreadsFragment(boardChars.get(position));
    }
}
