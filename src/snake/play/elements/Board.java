package snake.play.elements;

import snake.Drawable;
import snake.Screen;
import snake.play.Score;

import java.awt.*;
import java.util.Random;

public class Board implements Drawable
{
    public final int WIDTH = 800;
    public final int HEIGHT = 600;
    public final int TILES_X = 80;
    public final int TILES_Y = 60;
    public Tile[][] tiles;
    public int foodX = -1;
    public int foodY = -1;
    private int borderWidth = 10;
    private Screen screen;

    public Board(Screen screen)
    {
        this.screen = screen;
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
        createFood();
    }

    public void createFood()
    {
        removeOldFood();
        Random r = new Random();
        foodX = r.nextInt(TILES_X);
        foodY = r.nextInt(TILES_Y);
        tiles[foodX][foodY].hasFood = true;
    }

    private void removeOldFood()
    {
        if (foodX != -1 && foodY != -1) {
            tiles[foodX][foodY].hasFood = false;
        }
    }

    @Override
    public void draw(Graphics2D g)
    {
        for (int i = 0; i < TILES_X; i++) {
            for (int j = 0; j < TILES_Y; j++) {
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

        Score s = Score.getInstance();
        int fontSize = (int) HEIGHT/12;
        g.setFont(new Font("Arial", Font.PLAIN, fontSize));
        g.drawString("Score: " + s.value,
                (screen.getWidth()-WIDTH)/2,
                (screen.getHeight()+HEIGHT)/2 + fontSize + borderWidth);
    }
}
