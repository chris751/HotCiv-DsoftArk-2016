package hotciv.framework;

import hotciv.standard.GameImpl;

/**
 * Created by Christian on 25/11/2016.
 */
public interface BattleStrategy {

    boolean winBattle(GameImpl game, Position to, Position from);
}
