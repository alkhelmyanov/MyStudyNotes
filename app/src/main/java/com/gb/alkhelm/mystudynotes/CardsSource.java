package com.gb.alkhelm.mystudynotes;

import java.util.List;

public interface CardsSource {
    int size();

    List<CardData> getAllCardsData();

    CardData getCardData(int position);


    void addCardData(CardData cardData);

    // Для контекстного меню
    void deleteCardData(int position);
    void updateCardData(int position, CardData newCardData);



}
