package snake.play.elements;

import snake.Playable;
import snake.play.Score;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class Snake implements Playable
{
    public static final String[] directions =
            new String[]{"Up", "Down", "Left", "Right"};
    private Board board;
    private Tile[] tiles;
    private HashMap<Integer, Tile> digestationMap;
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
        this.digestationMap = new HashMap<Integer,Tile>();
    }

    @Override
    public void update()
    {
        for (int i = 0; i < tiles.length; i++) {
//            if (tiles[i] != null)
//                System.out.println(i);
//            else
//                System.out.println("-1");
        }

        if (!checkNextMoveWallCollision() && !checkNextMoveSnakeSelfEat()) {
            move();
            digest();
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

        if (board.tiles[headX][headY].hasFood) {
            eatTileCredentials(headX, headY);
        }
        else {
            unshiftPopTile(board.tiles[headX][headY]);
        }
    }

    private void eatTileCredentials(int x, int y)
    {
        board.createFood();
        board.tiles[x][y].hasFood = false;
        board.tiles[x][y].hasSnake = true;
        digestationMap.put(tiles.length, board.tiles[x][y]);
        Score s = Score.getInstance();
        s.value++;
    }

    private void digest()
    {
        Iterator<Map.Entry<Integer,Tile>> iter = digestationMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer,Tile> entry = iter.next();
            int key = entry.getKey();
            Tile tile = entry.getValue();
            iter.remove();
            if (key == 0)
                appendTail(tile);
            else
                digestationMap.put(key-1, tile);
        }
    }

    private void appendTail(Tile tile)
    {
        int len = tiles.length;
        Tile[] newTiles = new Tile[len+1];
        for (int i = 0; i < len; i++) {
            newTiles[i] = tiles[i];
        }
        newTiles[len] = tile;
        tiles = newTiles;
    }

    private boolean checkNextMoveWallCollision()
    {
        return ((headY == 0 && direction == "Up") ||
                (headY == board.TILES_Y-1 && direction == "Down") ||
                (headX == 0 && direction == "Left") ||
                (headX == board.TILES_X-1 && direction == "Right"));
    }

    private boolean checkNextMoveSnakeSelfEat()
    {
        return ((direction == "Up" && board.tiles[headX][headY-1].hasSnake) ||
                (direction == "Down" && board.tiles[headX][headY+1].hasSnake) ||
                (direction == "Left" && board.tiles[headX-1][headY].hasSnake) ||
                (direction == "Right" && board.tiles[headX+1][headY].hasSnake));
    }

    private void unshiftPopTile(Tile tile)
    {
        tiles[tiles.length-1].hasSnake = false;
        for (int i = tiles.length - 1; i > 0; i--) {
            tiles[i] = tiles[i-1];
        }
        tile.hasSnake = true;
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
            if (direction != "Down")
                direction = "Up";
        }
        else if (keyCode == KeyEvent.VK_DOWN) {
            if (direction != "Up")
                direction = "Down";
        }
        else if (keyCode == KeyEvent.VK_LEFT) {
            if (direction != "Right")
                direction = "Left";
        }
        else if (keyCode == KeyEvent.VK_RIGHT) {
            if (direction != "Left")
                direction = "Right";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void draw(Graphics2D g) {
        for (int i = tiles.length-1; i >= 0; i--) {
            Tile t = tiles[i];
            if (t != null)
                t.draw(g);
        }
    }
}
