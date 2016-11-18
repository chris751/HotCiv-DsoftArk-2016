package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.WinningStrategy;

/**
 * Created by Christian on 18/11/2016.
 */
public class AlphaWin implements WinningStrategy{

    int winAge = 3000;

    @Override
    public Player getWinner(int age) {
        if(winAge == age){
            return Player.RED;
        }
        else {
            return null;
        }


    }
}
