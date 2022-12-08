package com.gb.alkhelm.mystudynotes;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LocalRepositoryImpl implements CardsSource {

    private List<CardData> dataSource;
    private Resources resources;

    LocalRepositoryImpl(Resources resources) {
        dataSource = new ArrayList<CardData>();
        this.resources = resources;
    }

    public LocalRepositoryImpl init() {
        String[] listItem = resources.getStringArray(R.array.listOfNoteArray);

        for (int i = 0; i < listItem.length; i++) {
            dataSource.add(new CardData(listItem[i], "Заметка", false, Calendar.getInstance().getTime()));
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

    //Добавление новой карточки
    @Override
    public void addCardData(CardData cardData) {
        dataSource.add(cardData);
    }

    // Удаление карточки по позиции
    @Override
    public void deleteCardData(int position) {
        dataSource.remove(position);
    }

    // Изменение карточки по позиции
    @Override
    public void updateCardData(int position, CardData newCardData) {
        dataSource.set(position, newCardData);
    }
}
