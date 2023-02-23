package ru.levelp.at.homework2.data.provider;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class LuckyTicketExternalDataProvider {

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(123321),
                Arguments.of(153351)
        );
    }

    static Stream<Arguments> dataProviderThrowsLess() {
        return Stream.of(
                Arguments.of(92390),
                Arguments.of(923),
                Arguments.of(9239)
        );
    }

    static Stream<Arguments> dataProviderThrowsMore() {
        return Stream.of(
                Arguments.of(92390898),
                Arguments.of(9236876),
                Arguments.of(92391230)
        );
    }
}
