package snake.play.elements;

import snake.Drawable;
import snake.Screen;

import java.awt.*;
import java.util.Random;

public class Board implements Drawable
{
    public final int WIDTH = 800;
    public final int HEIGHT = 600;
    public final int TILES_X = 80;
    public final int TILES_Y = 60;
    public Tile[][] tiles;
    public int foodX;
    public int foodY;
    private int borderWidth = 10;
    private Screen screen;

    public Board(Screen screen)
    {
        this.screen = screen;
        Random r = new Random();
        foodX = r.nextInt(TILES_X);
        foodY = r.nextInt(TILES_Y);
        int tileWidth = Math.round(WIDTH / TILES_X);
        int tileHeight = Math.round(HEIGHT / TILES_Y);
        tiles = new Tile[TILES_X][TILES_Y];

        for (int i = 0; i < TILES_X; i++) {
            for (int j = 0; j < TILES_Y; j++) {
                tiles[i][j] = new Tile(
                        (screen.getWidth()-WIDTH)/2 + i*tileWidth,
                        (screen.getHeight()-HEIGHT)/2 + j*tileHeight,
                        tileWidth,
                        tileHeight);
            }
        }
    }

    @Override
    public void draw(Graphics2D g)
    {
        for (int i = 0; i < TILES_X; i++) {
            for (int j = 0; j < TILES_Y; j++) {
                if (i == foodX && j == foodY)
                    tiles[i][j].draw(g, Color.RED);
                else
                    tiles[i][j].draw(g);
            }
        }

        g.setColor(Color.GREEN);
        // Top
        g.fillRect((screen.getWidth()-WIDTH)/2 - borderWidth,
                (screen.getHeight()-HEIGHT)/2 - borderWidth,
                WIDTH + (2*borderWidth),
                borderWidth);
        // Bottom
        g.fillRect((screen.getWidth()-WIDTH)/2 - borderWidth,
                (screen.getHeight()+HEIGHT)/2,
                WIDTH + (2*borderWidth),
                borderWidth);
        // Left
        g.fillRect((screen.getWidth()-WIDTH)/2 - borderWidth,
                (screen.getHeight()-HEIGHT)/2,
                borderWidth,
                HEIGHT);
        // Right
        g.fillRect((screen.getWidth()+WIDTH)/2,
                (screen.getHeight()-HEIGHT)/2,
                borderWidth,
                HEIGHT);
    }
}
