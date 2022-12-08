package com.gb.alkhelm.mystudynotes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


public class EditNoteFragment extends Fragment {

    CardData cardData;
    private DatePicker datePicker;


    public static EditNoteFragment newInstance(CardData cardData) {
        EditNoteFragment fragment = new EditNoteFragment();
        Bundle args = new Bundle();
        args.putParcelable("cardData", cardData);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_edit_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // TODO Получить note индекс
       /* Note note;
        note = getArguments().getParcelable(NoteFragmentChild.KEY_NOTE);
        String[] listNote = getResources().getStringArray(R.array.NoteArray);
        String listNoteName = listNote[note.getNoteIndex()];*/


        if (getArguments() != null) {
            cardData = getArguments().getParcelable("cardData");
            ((EditText) view.findViewById(R.id.editListNoteItem)).setText(cardData.getList_item());
            ((EditText) view.findViewById(R.id.editNoteType)).setText(cardData.getNote_type());
            //((EditText) view.findViewById(R.id.editNoteTextView)).setText(listNoteName); // TODO Получить данные с карточки

            view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View it) {
                    cardData.setListItem(((TextView) view.findViewById(R.id.editListNoteItem)).getText().toString());
                    cardData.setNoteType(((TextView) view.findViewById(R.id.editNoteType)).getText().toString());
                    ((MainActivity) requireActivity()).getPublisher().sendMessage(cardData);
                    ((MainActivity) requireActivity()).getSupportFragmentManager().popBackStack();
                }
            });

        }

    }
}