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


    WorldStrategy gameWorld;
    HashMap<Position,Unit> unitMap;

    @Override
    public boolean winBattle(GameImpl game, Position to, Position from) {
        gameWorld = game.getWorld();
        if(game.getCityAt(to) != null) {
            gameWorld.createCity(to, game.getUnitAt(from).getOwner(), game.getCityMap());
        }
        unitMap = game.getUnitMap();
        Unit unit = unitMap.get(from);
        gameWorld.slayTheUnitAtPosition(from,unitMap);
        gameWorld.createUnit(to,unit.getOwner(),unit.getTypeString(),unitMap);

        return true;
    }
}
