package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.UnitActionStrategy;


/**
 * Created by Christian on 24/11/2016.
 */
public class GammaUnitAction implements UnitActionStrategy {

    WorldImpl gameWorld;
    Position p;
    @Override
    public void unitAction(GameImpl game, Position p) {
        this.p = p;
        if (game.getUnitAt(p).getTypeString().equals(GameConstants.SETTLER)) {
            gameWorld = game.getWorld();
            gameWorld.createCity(p, game.getUnitAt(p).getOwner());
            gameWorld.slayTheUnitAtPosition(p);
        }else if(game.getUnitAt(p).getTypeString().equals(GameConstants.ARCHER)){
            UnitImpl unit = (UnitImpl)game.getUnitAt(p);
            unit.setFortified();
        }

    }
}
