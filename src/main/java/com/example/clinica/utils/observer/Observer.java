package com.example.clinica.utils.observer;

import com.example.clinica.utils.utils.Event;

public interface Observer<E extends Event> {
    void update(E event);
}
