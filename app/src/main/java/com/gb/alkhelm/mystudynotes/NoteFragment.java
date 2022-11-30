package com.gb.alkhelm.mystudynotes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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