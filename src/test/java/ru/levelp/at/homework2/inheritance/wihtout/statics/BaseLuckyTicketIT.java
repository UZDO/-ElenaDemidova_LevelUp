package ru.levelp.at.homework2.inheritance.wihtout.statics;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import ru.levelp.at.homework2.LuckyTicketApp;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseLuckyTicketIT {
    protected LuckyTicketApp ticket;
    protected int number;

    @BeforeAll
    void beforeAll() {
        System.out.println(this.getClass().getName() + "before all");
    }

    @AfterAll
    void afterAll() {
        System.out.println(this.getClass().getName() + "after all");
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
