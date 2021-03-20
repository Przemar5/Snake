package snake.play;

import snake.Game;
import snake.Screen;
import snake.Settings;

import java.awt.*;
import java.util.HashMap;

public class PlayFactory
{
    public static Play createFromSettings(Game game, Settings settings)
    {
        return new Play(game);
    }
}
