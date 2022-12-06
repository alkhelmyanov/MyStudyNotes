package com.gb.alkhelm.mystudynotes;

import java.util.List;

public interface CardSource {
    int size();

    List<CardData> getAllCardsData();

    CardData getCardData(int position);

}
