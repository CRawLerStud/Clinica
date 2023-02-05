package com.example.clinica.utils.observer;

import com.example.clinica.utils.utils.Event;

public interface Observable<E extends Event> {

    void addObserver(Observer<E> observer);
    void removeObserver(Observer<E> observer);
    void notifyObserver(E event);
}
