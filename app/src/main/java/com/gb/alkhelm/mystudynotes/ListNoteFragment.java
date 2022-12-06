package com.gb.alkhelm.mystudynotes;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ListNoteFragment extends Fragment implements OnItemClickListener {

    ListNoteAdapter listNoteAdapter;

    public static ListNoteFragment newInstance() {
        ListNoteFragment fragment = new ListNoteFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initAdapter();
        initRecycler(view);

    }

    // Работаем с RecycleView который позволяет ПЕРЕиспользовать его элементы

    //Инициализируем Адаптер
    void initAdapter() {
        // 1. Создали пустой объект адаптера
        listNoteAdapter = new ListNoteAdapter();
        LocalRepositoryImpl localRepository = new LocalRepositoryImpl(requireContext().getResources()).init();
        // 2. Передаем занчение в адаптер
        //listNoteAdapter.setData(getData());
        listNoteAdapter.setData(localRepository);
        listNoteAdapter.setOnItemClickListener(this); // (this) особенности call back, передача не объекта а его интерфеса

    }

    // Иницализируем Recycle
    void initRecycler(View view) {
        // Ищем наш RecycleView
        RecyclerView recyclerView = view.findViewById(R.id.recycleView);
        // Создаем объект Layout менеджера
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        // Связывается со своим менеджером который объясняет как отображать на экране
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true); // если все ViewType одинаковые (моносписок) ускорит процесс надувания
        // Связываем свой RecycleView c адаптером который объясняет что именно ему нужно выводить
        recyclerView.setAdapter(listNoteAdapter);

    }

    // Метод для получения нашего списка
    String[] getData() {
        String[] data = getResources().getStringArray(R.array.listOfNoteArray);
        return data;
    }

    @Override
    public void onItemClick(int position) {
        String[] data = getData();
        Toast.makeText(requireContext(), "Нажали на " + data[position], Toast.LENGTH_SHORT).show();
    }

    //  Старый код без RecycleView

    /*private void initView(LinearLayout view) {
        //LinearLayout linearLayout = (LinearLayout) view; // переменная с родительским контейнером
        LayoutInflater layoutInflater = getLayoutInflater(); // создан инфлейтер для этого нового фрагмента

        String[] listNote = getResources().getStringArray(R.array.listOfNoteArray); // Создали массив строк, для него вызываем получить ресурсы.получить массив строк (R.array.имя)
        for (int i = 1; i < listNote.length; i++) { // i = 1, чтобы дефолтное значение не отображалось в списке
            String listNoteName = listNote[i];


            View listItem = layoutInflater.inflate(R.layout.fragment_list_note_item, view, false); // надуй по шаблону fragment_list_note_item, в контейнер linearLayout (view) объект которого мы создали выше
            Toast.makeText(getContext(), "номер" + i, Toast.LENGTH_SHORT).show();
            view.addView(listItem);

            TextView textView = listItem.findViewById(R.id.textView); // создали новый textView привязанный к контексту нашего активити
            textView.setTextSize(30f);
            textView.setGravity(1);
            textView.setText(listNoteName);

            final int finalI = i;
          textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Note note = new Note(finalI);
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        showLandscape(note);
                    } else {
                        showPort(note);
                    }
                }
            });

        }
    }*/

    private void showPort(Note note) {
        NoteFragment noteFragment = NoteFragment.newInstance(note);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.listNote, noteFragment).addToBackStack("").commit();// вызов фрагмента // addToBackstack - фрейм с записками (notes) накладывается поверх фрейма со списком заметок (listNote)
    }

    private void showLandscape(Note note) {
        NoteFragment noteFragment = NoteFragment.newInstance(note);
        // Отображаем фрагмент: т.к. мы находимся внутри фрагмента, сначала вызываем активити getActivity()
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.notes, noteFragment).commit();
    }


}