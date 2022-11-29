package com.gb.alkhelm.mystudynotes;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {

    public Note(int noteIndex) {
        this.noteIndex = noteIndex;
    }

    public int getNoteIndex() {
        return noteIndex;
    }

    public void setNoteIndex(int noteIndex) {
        this.noteIndex = noteIndex;
    }

    private int noteIndex;


    protected Note(Parcel in) {
        noteIndex = in.readInt();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(noteIndex);
    }
}
