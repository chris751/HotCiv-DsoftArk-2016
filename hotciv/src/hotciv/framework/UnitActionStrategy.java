package hotciv.framework;

import hotciv.standard.GameImpl;

import java.util.HashMap;

/**
 * Created by Christian on 24/11/2016.
 */
public interface UnitActionStrategy {

    HashMap<Position, Unit> unitAction(GameImpl game, Position p);
}
