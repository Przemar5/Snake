package snake.play;

import snake.Game;
import snake.Playable;
import snake.Screen;
import snake.menus.Menu;
import snake.menus.MenuFactory;
import snake.play.elements.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Play implements Playable
{
    private Game game;
    private Board board;
    private Snake snake;
    private Food food;
    private boolean running = false;

    public Play(Game game)
    {
        Screen s = game.getScreen();
        this.game = game;
        this.board = new Board(s);
        this.snake = new Snake(this.board);
        Score score = Score.getInstance();
        score.value = 0;
    }

    public void run()
    {
        running = true;
    }

    public void stop()
    {
        running = false;
    }

    public void setBoard(Board b)
    {
        board = b;
    }

    public void setSnake(Snake s)
    {
        snake = s;
    }

    @Override
    public void update()
    {
        snake.update();
        checkGameResult();
    }

    private void checkGameResult()
    {
        if (snake.hasCollided()) {
            onLose();
        }
//        Screen s = game.getScreen();
//        if (ball.y + ball.height >= s.getHeight()) {
//            onLose(game);
//        }
//        else if (brickBoard.isEmpty()) {
//            onWin(game);
//        }
    }

    private void onLose()
    {
        Score s = Score.getInstance();
        Menu menu = MenuFactory.createLostGameMenu(game.getScreen(), s.value);
        game.goToMenu(menu);
    }

    private void onEscape()
    {
        Menu menu = MenuFactory.createGameStopMenu(game.getScreen());
        game.goToMenu(menu);
    }

    @Override
    public void draw(Graphics2D g)
    {
        clearScreen(g);
        board.draw(g);
        snake.draw(g);
    }

    public void clearScreen(Graphics2D g)
    {
        Screen s = game.getScreen();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, s.getWidth(), s.getHeight());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_ESCAPE) {
            onEscape();
        }
        else {
            snake.keyPressed(e);
        }
    }

    public void keyPressed(KeyEvent e, Game game)
    {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_ESCAPE) {
            onEscape();
        }
        else {
            snake.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
