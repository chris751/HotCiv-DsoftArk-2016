package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinningStrategy;

/**
 * Created by Christian on 18/11/2016.
 */
public class AlphaWin implements WinningStrategy{

    int winAge = 3000;
    Game game;
    @Override
    public Player getWinner(GameImpl game) {
        this.game = game;
        int currentAge = game.getAge();

        if(winAge == currentAge){
            return Player.RED;
        }
        else {
            return null;
        }
    }
}
