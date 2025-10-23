package minigames.game.lotto.config;

import minigames.game.lotto.LottoGame;
import minigames.game.lotto.controller.LottoController;
import minigames.game.lotto.input.InputConsole;
import minigames.game.lotto.messaging.ConsoleMessage;
import minigames.game.lotto.service.LottoService;

public class LottoConfig {

    public LottoGame createGame() {
        ConsoleMessage consoleMessage = new ConsoleMessage();
        InputConsole inputConsole = new InputConsole(consoleMessage);
        LottoService lottoService = new LottoService();
        LottoController lottoController = new LottoController(lottoService, inputConsole, consoleMessage);
        return new LottoGame(lottoController);
    }
}
