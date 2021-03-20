package snake.menus.options;

import snake.Core;
import snake.Game;
import snake.Screen;
import snake.menus.Menu;
import snake.menus.MenuFactory;

public class OptionFactory
{
    public static Option createPlayOption(Screen screen)
    {
        return new Option(screen, "Play", true, Game::startNewGame);
    }

    public static Option createPlayOption(Screen screen, String title)
    {
        return new Option(screen, title, true, Game::startNewGame);
    }

    public static Option createOpenMainMenuOption(Screen screen)
    {
        return new Option(screen, "Main menu", false, (game) -> {
            Menu menu = MenuFactory.createMainMenu(game.getScreen());
            game.goToMenu(menu);
        });
    }

    public static Option createResumeOption(Screen screen)
    {
        return new Option(screen, "Resume", true, Game::startPlaying);
    }

    public static Option createExitOption(Screen screen)
    {
        return new Option(screen, "Exit", false, Core::stop);
    }
}
