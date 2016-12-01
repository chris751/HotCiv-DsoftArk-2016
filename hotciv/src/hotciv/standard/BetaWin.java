package hotciv.standard;

import hotciv.framework.*;

/**
 * Created by Christian on 18/11/2016.
 */
public class BetaWin implements WinningStrategy{
    Game game;
    @Override
    public Player getWinner(GameImpl game) {
        this.game = game;

        City redCity = game.getCityAt(new Position(1,1));
        City blueCity = game.getCityAt(new Position(4,1));

        boolean isOwnerEqual = blueCity.getOwner().equals(redCity.getOwner());

        if(isOwnerEqual){
            return blueCity.getOwner();
        }else{
            return null;
        }


    }


}
