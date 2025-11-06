package minigames.game.lotto.service;


import minigames.game.lotto.model.Lotto;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoService {
    private static final int LOW_BOUND = 1;
    private static final int HIGH_BOUND = 100;
    private static final int LIMIT_NUMBERS = 6;
    private final Random random;

    public LottoService() {
        this(new Random());
    }

    public LottoService(Random random) {
        this.random = random;
    }

    public Lotto playRound(Set<Integer> userNumber) {
        Set<Integer> drawNumbers = drawNumbers();
        int matchedCount = checkWin(drawNumbers, userNumber);

        return new Lotto(drawNumbers, userNumber, matchedCount);
    }


    private Set<Integer> drawNumbers() {
        return random.ints(LOW_BOUND, HIGH_BOUND).distinct().limit(LIMIT_NUMBERS).boxed().collect(Collectors.toSet());
    }

    private int checkWin(Set<Integer> drawNumbers, Set<Integer> numbersPlayer) {
        Set<Integer> draw = new HashSet<>(drawNumbers);
        draw.retainAll(numbersPlayer);
        return draw.size();
    }

}
