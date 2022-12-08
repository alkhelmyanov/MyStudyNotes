package com.gb.alkhelm.mystudynotes;

import java.util.ArrayList;
import java.util.List;

// Сущность паблишер хранит в себе список слушателей, тех кому он будет передавать сообщения, данные.
public class Publisher {

    private List<Observer> observers; // Список подписчиков

    Publisher() {
        observers = new ArrayList<>();
    }

    // Добавляем слушателей в список
    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    // Удаляем слушателй из списка
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    // Отправляет сообщение слушателям.
    public void sendMessage(CardData cardData) {
        for (Observer observer : observers) { // проходит по всем списку слушателей
            observer.receiveMessage(cardData); // отправляет слушателям сообщение cardData
        }
    }
}
