package ru.levelp.at.homework2.inheritance.with.statics;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.levelp.at.homework2.LuckyTicketApp;

public class BaseLuckyTicketIT {
    protected LuckyTicketApp ticket;
    protected int number;

    @BeforeAll
    static void beforeAll() {
        System.out.println(LuckyTicketPositiveIT.class.getName() + "before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println(LuckyTicketPositiveIT.class.getName() + "after all");
    }

    @BeforeEach
    void setUp() {
        System.out.println(this.getClass().getName() + "before each");
        ticket = new LuckyTicketApp();
    }

    @AfterEach
    void tearDown() {
        System.out.println(this.getClass().getName() + " tear down");
        ticket = null;
    }
}
