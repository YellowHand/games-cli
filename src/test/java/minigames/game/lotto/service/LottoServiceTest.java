package minigames.game.lotto.service;

import minigames.game.lotto.model.Lotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class LottoServicePlayRoundTest {

    private Random mockRandom;
    private LottoService service;

    @BeforeEach
    void setUp() {
        mockRandom = Mockito.mock(Random.class);
        service = new LottoService(mockRandom);
    }

    @Test
    @DisplayName("INTEGRATION: playRound() should return valid Lotto object")
    void shouldPlayRoundAndReturnLottoResult() {
        // given
        LottoService lottoService = new LottoService();
        Set<Integer> userNumbers = Set.of(5, 10, 15, 20, 25, 30);

        // when
        Lotto result = lottoService.playRound(userNumbers);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getDrawNumbers()).hasSize(6);
        assertThat(result.getPlayerNumbers()).containsExactlyInAnyOrderElementsOf(userNumbers);
        assertThat(result.getMatchedCount()).isBetween(0, 6);
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "1,2,3,4,5,6;1,2,3,4,5,6;6",
            "1,2,3,4,5,6;1,2,3,10,20,30;3",
            "1,2,3,4,5,6;10,20,30,40,50,60;0"
    })
    @DisplayName("UNIT: should correctly count matched numbers for various player inputs")
    void shouldCalculateCorrectMatchCount(String draw, String player, int expected) {
        //given
        when(mockRandom.ints(Mockito.anyInt(), Mockito.anyInt()))
                .thenAnswer(invocation -> Arrays.stream(draw.split(","))
                        .map(String::trim)
                        .mapToInt(Integer::parseInt));

        Set<Integer> playerNumbers = Arrays.stream(player.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toSet());

        //when
        Lotto result = service.playRound(playerNumbers);

        //then
        assertThat(result.getMatchedCount()).isEqualTo(expected);
        assertThat(result.getPlayerNumbers()).containsExactlyInAnyOrderElementsOf(playerNumbers);
        verify(mockRandom).ints(eq(1), eq(100));
    }

    @Test
    @DisplayName("UNIT: should correctly handle empty user numbers with no matches")
    void shouldHandleEmptyUserNumbersCorrectly() {
        // given
        when(mockRandom.ints(eq(1), eq(100)))
                .thenAnswer(inv -> Arrays.stream(new int[]{1, 2, 3, 4, 5, 6}));

        Set<Integer> player = Set.of();

        // when
        Lotto result = service.playRound(player);

        // then
        assertThat(result.getMatchedCount()).isZero();
        assertThat(result.getPlayerNumbers()).isEmpty();
        assertThat(result.getDrawNumbers()).hasSize(6);
    }

    @Test
    @DisplayName("UNIT: should draw 6 unique numbers even if generator give duplicates")
    void shouldDrawSixUniqueNumbersEvenIfGeneratorGiveDuplicates() {
        // given
        when(mockRandom.ints(1, 100))
                .thenReturn(Arrays.stream(new int[]{1,1,1,2,2,3,4,5,6,7}));

        // when
        Lotto result = service.playRound(Set.of());

        // then
        assertThat(result.getDrawNumbers()).hasSize(6);
        assertThat(result.getDrawNumbers()).containsExactlyInAnyOrder(1,2,3,4,5,6);
        verify(mockRandom).ints(1, 100);
    }
}
