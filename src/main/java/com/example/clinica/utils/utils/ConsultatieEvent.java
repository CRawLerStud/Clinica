package com.example.clinica.utils.utils;

import com.example.clinica.models.Consultatie;

public class ConsultatieEvent implements Event {

    private final Consultatie consultatie;
    private final ChangeEventType changeEventType;

    public ConsultatieEvent(Consultatie consultatie, ChangeEventType changeEventType) {
        this.consultatie = consultatie;
        this.changeEventType = changeEventType;
    }

    public Consultatie getConsultatie() {
        return consultatie;
    }

    public ChangeEventType getChangeEventType() {
        return changeEventType;
    }
}
