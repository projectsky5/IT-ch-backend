package com.projectsky.IT_ch_backend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Weekday {
    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота"),
    SUNDAY("Воскресенье");

    private final String display;

    Weekday(String display) {
        this.display = display;
    }

    @JsonValue
    public String getDisplay() {
        return display;
    }

    @JsonCreator
    public static Weekday fromDisplay(String value) {
        for (Weekday d : values()) {
            if (d.display.equalsIgnoreCase(value)) {
                return d;
            }
        }
        throw new IllegalArgumentException("Неизвестный день недели: " + value);
    }
}
