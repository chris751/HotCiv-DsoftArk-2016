package hotciv.standard;

import hotciv.framework.BattleStrategy;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.framework.WorldStrategy;

import java.util.HashMap;

/**
 * Created by Christian on 25/11/2016.
 */
public class AlphaBattle implements BattleStrategy {

    @Override
    public boolean winBattle(GameImpl game, Position to, Position from) {
        return true;
    }
}
