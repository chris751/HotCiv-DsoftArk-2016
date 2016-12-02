package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinningStrategy;

/**
 * Created by Christian on 02/12/2016.
 */
public class ZetaWin implements WinningStrategy {
    private WinningStrategy currentState;
    private WinningStrategy betaWin;
    private WinningStrategy epsilonWin;


    public ZetaWin(WinningStrategy betaWin, WinningStrategy epsilonWin) {
        this.betaWin = betaWin;
        this.epsilonWin = epsilonWin;
        this.currentState = null;
    }

    @Override
    public Player getWinner(GameImpl game) {
        if(game.getAge() >= 2000){
            currentState = betaWin;
        }else if(game.getAge() <= 2000){
            currentState = epsilonWin;
        }
        return currentState.getWinner(game);
    }
}
