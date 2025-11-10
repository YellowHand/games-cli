package minigames.game.lotto.input;

import minigames.game.lotto.messaging.Message;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class InputConsoleTest {

    @ParameterizedTest
    @CsvSource({
            "'0\n1\n2\n3\n4\n5\n6'",
            "'100\n1\n2\n3\n4\n5\n6'",
            "'1\n2\n3\n150\n4\n5\n6'",
            "'1\n2\n3\n4\n5\n100\n6'",
            "'1\n2\n3\n4\n5\n0\n6'",
    })
    void shouldShowErrorMessageWhenRangeNumberIsInvalid(String input) {
        // given
        BufferedReader reader = new BufferedReader(new StringReader(input));
        Message message = mock(Message.class);
        InputConsole inputConsole = new InputConsole(reader, message);

        // when
        Set<Integer> result = inputConsole.getNumbersFromPlayer();

        // then
        verify(message).showMessage("Input number must be 1 to 99!");
        assertThat(result).isEqualTo(Set.of(1, 2, 3, 4, 5, 6));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "'1\n1\n2\n3\n4\n5\n6'",
            "'1\n1\n1\n2\n3\n4\n5\n6'",
            "'1\n1\n1\n2\n3\n3\n3\n4\n5\n6'"
    })
    void shouldShowErrorMessageWhenNumberIsDuplicated(String input) {
        // given
        BufferedReader reader = new BufferedReader(new StringReader(input));
        Message message = mock(Message.class);
        InputConsole inputConsole = new InputConsole(reader, message);

        // when
        Set<Integer> result = inputConsole.getNumbersFromPlayer();

        // then
        verify(message, atLeastOnce()).showMessage("Number must be unique!");
        assertThat(result).isEqualTo(Set.of(1, 2, 3, 4, 5, 6));
    }

    @Test
    void shouldShowErrorMessageWhenInputIsNotANumber() {
        // given
        String input = "Test\n1\n2\n3\n4\n5\n6\n";
        BufferedReader reader = new BufferedReader(new StringReader(input));
        Message message = mock(Message.class);
        InputConsole inputConsole = new InputConsole(reader, message);

        // when
        Set<Integer> result = inputConsole.getNumbersFromPlayer();

        // then
        verify(message).showMessage("Insert number!");
        verify(message, times(7)).showMessage("Insert number: ");
        verify(message, never()).showMessage("Input number must be 1 to 99!");
        verify(message, never()).showMessage("Number must be unique!");
        verifyNoMoreInteractions(message);
        assertThat(result).isEqualTo(Set.of(1, 2, 3, 4, 5, 6));
    }

    @Test
    void shouldCollectSixUniqueNumbersForValidInput() {
        //given
        String input = "1\n2\n3\n4\n5\n6\n";
        BufferedReader reader = new BufferedReader(new StringReader(input));
        Message message = mock(Message.class);
        InputConsole inputConsole = new InputConsole(reader, message);

        //when
        Set<Integer> result = inputConsole.getNumbersFromPlayer();

        //then
        verify(message, times(6)).showMessage("Insert number: ");
        verify(message, never()).showMessage("Input number must be 1 to 99!");
        verify(message, never()).showMessage("Number must be unique!");
        verify(message, never()).showMessage("Insert number!");
        verifyNoMoreInteractions(message);
        assertThat(result).isEqualTo(Set.of(1, 2, 3, 4, 5, 6));
    }
}
