package minigames.game.lotto.controller;

import minigames.game.lotto.service.LottoService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class LottoController {
    private final LottoService lottoService;
    private static final int LIMIT_NUMBERS = 6;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public void run() throws IOException {
        lottoService.playRound(getNumberFromPlayer());
    }

    private Set<Integer> getNumberFromPlayer() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Set<Integer> playerNumber = new HashSet<>();
        for (int i = 0; i < LIMIT_NUMBERS; i++) {
            playerNumber.add(Integer.valueOf(bufferedReader.readLine()));
        }
        return playerNumber;
    }
    
}
