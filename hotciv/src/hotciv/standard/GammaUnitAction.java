package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.UnitActionStrategy;
import hotciv.framework.WorldStrategy;


/**
 * Created by Christian on 24/11/2016.
 */
public class GammaUnitAction implements UnitActionStrategy {

    WorldStrategy gameWorld;
    Position p;
    @Override
    public void unitAction(GameImpl game, Position p) {
        this.p = p;
        if (game.getUnitAt(p).getTypeString().equals(GameConstants.SETTLER)) {
            gameWorld = game.getWorld();
            gameWorld.createCity(p, game.getUnitAt(p).getOwner(), game.cityMap);
            gameWorld.slayTheUnitAtPosition(p, game.unitMap);
        }else if(game.getUnitAt(p).getTypeString().equals(GameConstants.ARCHER)){
            UnitImpl unit = (UnitImpl)game.getUnitAt(p);
            unit.setFortified();
        }

    }
}
