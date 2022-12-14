package com.gb.alkhelm.mystudynotes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NoteFragmentChild extends Fragment {

    public static final String KEY_NOTE = "key_note";
    private Note note;

    public static NoteFragmentChild newInstance(Note note) {
        NoteFragmentChild fragment = new NoteFragmentChild();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_NOTE, note);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_child, container, false);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        note = getArguments().getParcelable(KEY_NOTE);


        String[] listNote = getResources().getStringArray(R.array.NoteArray);
        String listNoteName = listNote[note.getNoteIndex()];
        TextView textView = view.findViewById(R.id.NoteTextView);
        textView.setText(listNoteName);

    }
}