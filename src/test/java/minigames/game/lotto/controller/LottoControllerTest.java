package minigames.game.lotto.controller;

import minigames.game.lotto.input.InputData;
import minigames.game.lotto.messaging.Message;
import minigames.game.lotto.model.Lotto;
import minigames.game.lotto.service.LottoService;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


class LottoControllerTest {

    @Test
    void shouldShowWelcomeAndResultInOrder() {
        // given
        LottoService service = mock(LottoService.class);
        InputData input = mock(InputData.class);
        Message message = mock(Message.class);

        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6);
        given(input.getNumbersFromPlayer()).willReturn(numbers);

        Lotto lottoResult = new Lotto(Set.of(10, 20, 30, 40, 50, 60), numbers, 0);
        given(service.playRound(numbers)).willReturn(lottoResult);

        LottoController controller = new LottoController(service, input, message);

        // when
        controller.run();

        // then
        InOrder inOrder = inOrder(message, input, service);
        inOrder.verify(message).showMessage("Welcome to Lotto!");
        inOrder.verify(input).getNumbersFromPlayer();
        inOrder.verify(service).playRound(numbers);
        inOrder.verify(message).showMessage(lottoResult.toString());
        verifyNoMoreInteractions(message, input, service);
    }

    @Test
    void shouldPassNumbersFromInputToService() {
        // given
        LottoService service = mock(LottoService.class);
        InputData input = mock(InputData.class);
        Message message = mock(Message.class);

        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6);
        given(input.getNumbersFromPlayer()).willReturn(numbers);

        Lotto lottoResult = new Lotto(Set.of(10, 20, 30, 40, 50, 60), numbers, 0);
        given(service.playRound(numbers)).willReturn(lottoResult);

        LottoController controller = new LottoController(service, input, message);

        // when
        controller.run();

        // then
        verify(service, times(1)).playRound(same(numbers));
        verifyNoMoreInteractions(service);
    }
}