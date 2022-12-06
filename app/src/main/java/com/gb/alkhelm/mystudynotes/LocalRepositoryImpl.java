package com.gb.alkhelm.mystudynotes;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

public class LocalRepositoryImpl implements CardSource {

    private List<CardData> dataSource;
    private Resources resources;

    LocalRepositoryImpl(Resources resources) {
        dataSource = new ArrayList<CardData>();
        this.resources = resources;
    }

    public LocalRepositoryImpl init() {
        String[] listItem = resources.getStringArray(R.array.listOfNoteArray);

        for (int i = 0; i < listItem.length; i++) {
            dataSource.add(new CardData(listItem[i], "Заметка", false));
        }
        return this;
    }

    // Возвращает длинну коллекции
    @Override
    public int size() {
        return dataSource.size();
    }

    // Возвращает список всех карточек
    @Override
    public List<CardData> getAllCardsData() {
        return dataSource;
    }

    // Получение карточки по позиции
    @Override
    public CardData getCardData(int position) {
        return dataSource.get(position);
    }
}
