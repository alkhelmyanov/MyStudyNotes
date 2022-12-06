package com.gb.alkhelm.mystudynotes;

public class CardData {
    private String listItem;
    private String noteType;
    private Boolean favorite;

    public CardData(String listItem, String noteType, Boolean favorite) {
        this.listItem = listItem;
        this.noteType = noteType;
        this.favorite = favorite;
    }

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

}
