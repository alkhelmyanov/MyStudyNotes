package com.gb.alkhelm.mystudynotes;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListNoteFragment extends Fragment {


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
        initView((LinearLayout) view);
    }


    private void initView(LinearLayout view) {
        String[] listNote = getResources().getStringArray(R.array.listOfNoteArray); // Создали массив строк, для него вызываем получить ресурсы.получить массив строк (R.array.имя)

        for (int i = 1; i < listNote.length; i++) { // i = 1, чтобы дефолтное значение не отображалось в списке
            String listNoteName = listNote[i];
            TextView textView = new TextView(getContext()); // создали новый textView привязанный к контексту нашего активити
            textView.setTextSize(30f);
            textView.setGravity(1);
            textView.setText(listNoteName);
            view.addView(textView);

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
    }

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