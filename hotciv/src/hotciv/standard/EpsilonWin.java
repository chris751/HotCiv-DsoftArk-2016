package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinningStrategy;

import static hotciv.framework.Player.BLUE;

/**
 * Created by Christian on 01/12/2016.
 */
public class EpsilonWin implements WinningStrategy {

    @Override
    public Player getWinner(GameImpl game) {
        if(game.getBlueWinCounter() >= 3){
            return Player.BLUE;
        } else if(game.getRedWinCounter() >= 3){
            return Player.RED;
        } else {
            return null;
        }
    }
}
