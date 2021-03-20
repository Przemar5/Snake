package snake.menus;

import snake.Drawable;
import snake.Game;
import snake.Screen;
import snake.menus.options.Option;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Menu implements KeyListener, Drawable
{
    protected String title;
    protected Option[] options;
    protected int height;
    protected Screen screen;
    protected int selectedIndex = 0;

    public Menu(Screen screen, String title, Option[] options)
    {
        this.screen = screen;
        this.title = title;
        this.options = options;
    }

    public synchronized void draw(Graphics2D g)
    {
        Window w = screen.getFullScreenWindow();
        g.setColor(w.getBackground());
        g.fillRect(0, 0, screen.getWidth(), screen.getHeight());
        g.setColor(w.getForeground());
        g.drawString(title, 140, getYOfNthElement(0));

        for (int i = 0; i < options.length; i++) {
            options[i].setX(140);
            options[i].setY(getYOfNthElement(i+1));
            options[i].draw(g);
        }
    }

    protected int getYOfNthElement(int i)
    {
        return (screen.getHeight() * (i + 1)) / (options.length + 2);
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            options[selectedIndex].focusOut();
            selectedIndex = (selectedIndex + options.length + 1) % options.length;
            options[selectedIndex].focusIn();
        }
        else if (keyCode == KeyEvent.VK_DOWN) {
            options[selectedIndex].focusOut();
            selectedIndex = (selectedIndex + options.length - 1) % options.length;
            options[selectedIndex].focusIn();
        }
        else if (keyCode == KeyEvent.VK_ENTER) {
//            options[selectedIndex].execute();
        }
    }

    public void keyPressed(KeyEvent e, Game game)
    {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            options[selectedIndex].focusOut();
            selectedIndex = (selectedIndex + options.length + 1) % options.length;
            options[selectedIndex].focusIn();
        }
        else if (keyCode == KeyEvent.VK_DOWN) {
            options[selectedIndex].focusOut();
            selectedIndex = (selectedIndex + options.length - 1) % options.length;
            options[selectedIndex].focusIn();
        }
        else if (keyCode == KeyEvent.VK_ENTER) {
            options[selectedIndex].select(game);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void selectOption(Game game)
    {
        options[selectedIndex].select(game);
    }
}
