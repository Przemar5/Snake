package snake;

import java.awt.*;

public class Settings
{
    private static Settings instance;
    public Color color = Color.GREEN;
    public int wallWidth = 20;

    public static Settings getInstance()
    {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    private Settings()
    {

    }
}
