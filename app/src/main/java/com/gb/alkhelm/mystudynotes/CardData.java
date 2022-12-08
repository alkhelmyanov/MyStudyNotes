package com.gb.alkhelm.mystudynotes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class CardData implements Parcelable {
    private String listItem;
    private String noteType;
    private Boolean favorite;
    private Date date;

    public CardData(String listItem, String noteType, Boolean favorite, Date date) {
        this.listItem = listItem;
        this.noteType = noteType;
        this.favorite = favorite;
        this.date = date;
    }

    protected CardData(Parcel in) {
        listItem = in.readString();
        noteType = in.readString();
        byte tmpFavorite = in.readByte();
        favorite = tmpFavorite == 0 ? null : tmpFavorite == 1;
        date = new Date(in.readLong());
    }

    public static final Creator<CardData> CREATOR = new Creator<CardData>() {
        @Override
        public CardData createFromParcel(Parcel in) {
            return new CardData(in);
        }

        @Override
        public CardData[] newArray(int size) {
            return new CardData[size];
        }
    };

    public String getList_item() {
        return listItem;
    }

    public String getNote_type() {
        return noteType;
    }

    public Boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(listItem);
        parcel.writeString(noteType);
        parcel.writeByte((byte) (favorite == null ? 0 : favorite ? 1 : 2));
        parcel.writeLong(date.getTime());
    }
}
