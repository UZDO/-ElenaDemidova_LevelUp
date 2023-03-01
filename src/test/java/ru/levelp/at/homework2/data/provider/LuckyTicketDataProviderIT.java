package ru.levelp.at.homework2.data.provider;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.levelp.at.homework2.LuckyTicketApp;
import ru.levelp.at.homework2.tags.annotations.NegativeTag;

public class LuckyTicketDataProviderIT {

    @ParameterizedTest
    @MethodSource("ru.levelp.at.homework2.data.provider.LuckyTicketExternalDataProvider#dataProvider")
    void shouldLuckyTicket(int number) {
        LuckyTicketApp ticket = new LuckyTicketApp();
        Assertions.assertTrue(ticket.luckyTicketAssert(number));
    }

    @NegativeTag
    @ParameterizedTest
    @MethodSource("ru.levelp.at.homework2.data.provider.LuckyTicketExternalDataProvider#dataProviderThrowsLess")
    void shouldBeExceptionWhenInputSymbolsLess(int number) {
        LuckyTicketApp ticket = new LuckyTicketApp();
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ticket.luckyTicketAssert(number));
    }

    @NegativeTag
    @ParameterizedTest
    @MethodSource("ru.levelp.at.homework2.data.provider.LuckyTicketExternalDataProvider#dataProviderThrowsMore")
    void shouldBeExceptionWhenInputSymbolsMore(int number) {
        LuckyTicketApp ticket = new LuckyTicketApp();
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ticket.luckyTicketAssert(number));
    }
}
