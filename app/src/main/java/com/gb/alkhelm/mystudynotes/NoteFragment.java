package com.gb.alkhelm.mystudynotes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NoteFragment extends Fragment {

    public static final String KEY_NOTE = "key_note";
    private Note note;

    public static NoteFragment newInstance(Note note) {
        NoteFragment fragment = new NoteFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_NOTE, note);
        fragment.setArguments(bundle);
        return fragment;
    }

    // Добавляем меню во врагмент
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment, menu); // надуватель.надувает
        menu.findItem(R.id.action_about).setVisible(false); // выбираем элемент меню (action_about) и отключаем его видимость.
        menu.findItem(R.id.action_search).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_share) {
            Toast.makeText(requireContext(), "Заметка отправлена другу", Toast.LENGTH_SHORT).show(); // всплывающее сообщение
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        note = getArguments().getParcelable(KEY_NOTE);

        setHasOptionsMenu(true); // Говорим что у фрагмента есть свое меню. Обязательно!!!!!

        String[] listNoteTitle = getResources().getStringArray(R.array.listOfNoteArray);
        String listNoteNameTitle = listNoteTitle[note.getNoteIndex()];
        TextView textViewList = view.findViewById(R.id.noteTitle);
        textViewList.setText(listNoteNameTitle);

        getChildFragmentManager().beginTransaction().replace(R.id.note_child_container, NoteFragmentChild.newInstance(note)).commit(); // вызываем ChildFragmentManager для доступа к фрагменту ребенка.

        view.findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }
}