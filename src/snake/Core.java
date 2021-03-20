package snake;

import java.awt.*;

public class Core
{
    private static DisplayMode[] modes = {
            new DisplayMode(800, 600, 32, 0),
            new DisplayMode(800, 600, 24, 0),
            new DisplayMode(800, 600, 16, 0),
            new DisplayMode(600, 480, 32, 0),
            new DisplayMode(600, 480, 24, 0),
            new DisplayMode(600, 480, 16, 0),
    };
    private boolean running;
    protected Screen screen;
    private int fps = 10;

    public void stop()
    {
        running = false;
    }

    public void run()
    {
        try {
            init();
            gameLoop();
        }
        finally {
            screen.restoreScreen();
        }
    }

    public void init()
    {
        screen = new Screen();
        DisplayMode dm = screen.findFirstCompatibleMode(modes);
        screen.setFullScreen(dm);

        Window w = screen.getFullScreenWindow();
        w.setFont(new Font("Arial", Font.PLAIN, 32));
        w.setBackground(Color.BLACK);
        w.setForeground(Color.GREEN);
        running = true;
    }

    public void gameLoop()
    {
        long startTime = System.currentTimeMillis();
        long cumTime = startTime;

        while (running) {
            long timePassed = System.currentTimeMillis() - cumTime;
            cumTime += timePassed;

            update(timePassed);

            Graphics2D g = screen.getGraphics();
            draw(g);
            g.dispose();
            screen.update();

            try {
                Thread.sleep((int) (1000/fps));
            }
            catch (Exception ex) {
                System.out.println("An exception occured in " + getClass());
            }
        }
    }

    public void update(long timePassed)
    {
        //
    }

    public void draw(Graphics2D g)
    {
        //
    }
    
    public Screen getScreen()
    {
        return screen;
    }
}
