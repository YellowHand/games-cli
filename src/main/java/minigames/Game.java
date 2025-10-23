package minigames;

import java.io.IOException;

public interface Game {
    void play() throws IOException;
    String getName();
}
