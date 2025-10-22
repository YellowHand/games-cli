package minigames.game.lotto.service;


import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoService {
    private static final int LOW_BOUND = 1;
    private static final int HIGH_BOUND = 99;
    private static final int LIMIT_NUMBERS = 6;


    private Set<Integer> drawNumbers() {
        return new Random().ints(LOW_BOUND, HIGH_BOUND).limit(LIMIT_NUMBERS).boxed().collect(Collectors.toSet());
    }

    private int checkWin(Set<Integer> drawNumbers, Set<Integer> numbersPlayer) {
        drawNumbers.retainAll(numbersPlayer);
        return drawNumbers.size();
    }

}
