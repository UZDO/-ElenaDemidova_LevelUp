package ru.levelp.at.homework2;

public class LuckyTicketApp {

    public boolean luckyTicketAssert(int x) {
        if (x < 99999) {
            throw new IllegalArgumentException("В номере билета меньше 6 цифр");
        }

        if (x > 999999) {
            throw new IllegalArgumentException("В номере билета больше 6 цифр");
        }

        return ((x % 10) + (x / 10) % 10 + (x / 100) % 10 == (x / 1000) % 10 + (x / 10000) % 10 + (x / 100000) % 10);
    }
}
