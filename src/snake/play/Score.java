package snake.play;

public class Score
{
    private static Score instance = null;
    public int value = 0;

    public static Score getInstance()
    {
        if (instance == null)
            instance = new Score();

        return instance;
    }

    private Score()
    {
        //
    }
}
