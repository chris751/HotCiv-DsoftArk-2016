package hotciv.framework;

import hotciv.standard.GameImpl;

/**
 * Created by Christian on 18/11/2016.
 */
public interface WinningStrategy {

    public Player getWinner(GameImpl game);

}
