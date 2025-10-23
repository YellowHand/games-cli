package minigames.game.lotto.config;

import minigames.game.lotto.controller.LottoController;
import minigames.game.lotto.input.InputConsole;
import minigames.game.lotto.messaging.ConsoleMessage;
import minigames.game.lotto.service.LottoService;

public class LottoConfig {

    public void start() {
        ConsoleMessage consoleMessage = new ConsoleMessage();
        InputConsole inputConsole = new InputConsole(consoleMessage);
        LottoService lottoService = new LottoService();
        LottoController lottoController = new LottoController(lottoService, inputConsole, consoleMessage);
        lottoController.run();
    }
}
