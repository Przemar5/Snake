package snake.play.elements;

import snake.Drawable;

import java.awt.*;

public class Tile extends Rectangle implements Drawable {
    public boolean hasFood = false;
    public boolean hasSnake = false;
    public Color color = Color.BLACK;

    public Tile(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    public Tile(int x, int y, int w, int h, Color c) {
        super(x, y, w, h);
        color = c;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    public void draw(Graphics2D g, Color c)
    {
        g.setColor(c);
        g.fillRect(x, y, width, height);
    }
}
