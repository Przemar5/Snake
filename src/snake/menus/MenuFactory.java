package snake.menus;

import snake.Screen;
import snake.menus.options.*;

public class MenuFactory
{
    public static Menu createMainMenu(Screen screen)
    {
        return new Menu(screen, "Snake", new Option[]{
                OptionFactory.createPlayOption(screen),
                OptionFactory.createExitOption(screen),
        });
    }

    public static Menu createGameFinishMenu(Screen screen, String title)
    {
        return new Menu(screen, title, new Option[]{
                OptionFactory.createPlayOption(screen, "Play again"),
                OptionFactory.createOpenMainMenuOption(screen),
        });
    }

    public static Menu createWonGameMenu(Screen screen)
    {
        return MenuFactory.createGameFinishMenu(screen, "You have won!");
    }

    public static Menu createLostGameMenu(Screen screen, int score)
    {
        return MenuFactory.createGameFinishMenu(screen,
                "You scored " + score + " points!");
    }

    public static Menu createGameStopMenu(Screen screen)
    {
        return new Menu(screen, "Paused", new Option[]{
                OptionFactory.createResumeOption(screen),
                OptionFactory.createOpenMainMenuOption(screen),
        });
    }
}
