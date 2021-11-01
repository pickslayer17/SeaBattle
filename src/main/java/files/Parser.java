package files;

import java.io.IOException;
import java.util.Deque;

public interface Parser{
    String serialize(Deque<GameState> gameStateHistory) throws IOException;
    Deque<GameState> deSerialize(String data) throws IOException;
}
