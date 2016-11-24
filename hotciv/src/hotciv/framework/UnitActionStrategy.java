package hotciv.framework;

import hotciv.standard.GameImpl;

/**
 * Created by Christian on 24/11/2016.
 */
public interface UnitActionStrategy {

    void unitAction(GameImpl game, Position p);
}
