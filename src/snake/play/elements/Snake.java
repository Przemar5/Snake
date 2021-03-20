package snake.play.elements;

import snake.Playable;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Snake implements Playable
{
    public static final String[] directions =
            new String[]{"Up", "Down", "Left", "Right"};
    private Board board;
    private Tile[] tiles;
    private String direction;
    private int headX = 0;
    private int headY = 0;
    private boolean collide = false;
    private int points = 0;
    private Color color = Color.GREEN;

    public Snake(Board board)
    {
        this.board = board;
        Random r = new Random();
        this.headX = r.nextInt(board.TILES_X - 1);
        this.headY = r.nextInt(board.TILES_Y - 1);
        this.direction = this.directions[r.nextInt(4)];
        this.tiles = new Tile[1];
        this.tiles[0] = board.tiles[this.headX][this.headY];
    }

    @Override
    public void update()
    {
//        System.out.println("headX = " + headX);
//        System.out.println("headY = " + headY);
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i] != null)
                System.out.println(i);
            else
                System.out.println("-1");
        }

        if (!checkNextMoveCollision()) {
            move();
        }
        else
            collide = true;
    }

    public void move()
    {
        if (direction == "Up") {
            headY--;
        }
        else if (direction == "Down") {
            headY++;
        }
        else if (direction == "Left") {
            headX--;
        }
        else if (direction == "Right") {
            headX++;
        }

        unshiftPopTile(board.tiles[headX][headY]);
    }

    private boolean checkNextMoveCollision()
    {
        return ((headY == 0 && direction == "Up") ||
                (headY == board.TILES_Y-1 && direction == "Down") ||
                (headX == 0 && direction == "Left") ||
                (headX == board.TILES_X-1 && direction == "Right"));
    }

    private void unshiftPopTile(Tile tile)
    {
//        System.out.println("Tile x: " + tile.x);
//        System.out.println("Tile y: " + tile.y);
//        System.out.println("headX: " + headX);
//        System.out.println("headY: " + headY);

        for (int i = tiles.length - 1; i > 0; i--) {
            tiles[i] = tiles[i-1];
        }
        tiles[0] = tile;
    }

    public boolean hasCollided()
    {
        return collide;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            direction = "Up";
        }
        else if (keyCode == KeyEvent.VK_DOWN) {
            direction = "Down";
        }
        else if (keyCode == KeyEvent.VK_LEFT) {
            direction = "Left";
        }
        else if (keyCode == KeyEvent.VK_RIGHT) {
            direction = "Right";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void draw(Graphics2D g) {
        for (Tile t: tiles) {
            if (t != null)
                t.draw(g, color);
        }
    }
}
