package com.projectsky.IT_ch_backend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Frequency {
    ONCE_A_WEEK("1 раз в неделю"),
    ONCE_EVERY_TWO_WEEKS("1 раз в 2 недели");

    private final String display;

    Frequency(String display) {
        this.display = display;
    }

    @JsonValue
    public String getDisplay() {
        return display;
    }

    @JsonCreator
    public static Frequency fromDisplay(String value) {
        for (Frequency f : values()) {
            if (f.display.equalsIgnoreCase(value)) {
                return f;
            }
        }
        throw new IllegalArgumentException("Неизвестная частота: " + value);
    }
}
