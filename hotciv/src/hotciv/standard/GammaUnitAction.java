package hotciv.standard;

import hotciv.framework.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


/**
 * Created by Christian on 24/11/2016.
 */
public class GammaUnitAction implements UnitActionStrategy {

    WorldStrategy gameWorld;
    Position p;
    @Override
    public HashMap<Position, Unit> unitAction(GameImpl game, Position p) {
        this.p = p;
        this.gameWorld = game.getWorld();

        if (game.getUnitAt(p).getTypeString().equals(GameConstants.SETTLER)) {
            gameWorld.createCity(p, game.getUnitAt(p).getOwner(), game.cityMap);
            gameWorld.slayTheUnitAtPosition(p, game.unitMap);
            return null;

        }else if(game.getUnitAt(p).getTypeString().equals(GameConstants.ARCHER)){
            UnitImpl unit = (UnitImpl)game.getUnitAt(p);
            unit.setFortified();
            return null;
        }else if(game.getUnitAt(p).getTypeString().equals(OurConstants.BOMB)){

            HashMap<Position,Unit> unitMap = game.getUnitMap();


            Iterator<Position> neighborhood = get8NeighborhoodIterator(p);
            Position pos;
            while ( neighborhood.hasNext() ) {
                pos = neighborhood.next();
                if ( game.getUnitAt(pos) != null) {
                    unitMap = gameWorld.slayTheUnitAtPosition(pos,unitMap);

                }
            }
            unitMap = gameWorld.slayTheUnitAtPosition(p, unitMap);
            return unitMap;
        }
    return null;
    }

    /** return an iterator over positions that are in the 8 neighborhood
     * of a given position. The 8 neighborhood is the (normally 8)
     * positions adjacent to the center position. The center position
     * itself is not part of the iterator. PostCondition: The iterator
     * is always valid and will contain from 3 to 8 positions. It will
     * only contain positions that are valid on the game world, that is
     * a neighborhood centered in (0,0) will contain (1,0), (1,1) and
     * (0,1) but not e.g. (-1,-1). The iterator always return the
     * positions in a sequence starting at the north west position and
     * the rest given row-wise.
     * @param center the position marking the center of the 8 neighborhood.
     * @return iterator over the valid positions in the center's 8
     * neighborhood.
     */
    public static Iterator<Position> get8NeighborhoodIterator(Position center) {
        ArrayList<Position> list = new ArrayList<Position>();
        int row = center.getRow(); int col = center.getColumn();
        int r,c;
        for (int dr = -1; dr <= +1; dr++) {
            for (int dc = -1; dc <= +1; dc++) {
                r = row+dr; c = col+dc;
                // avoid positions outside the world
                if ( r >= 0 && r < GameConstants.WORLDSIZE &&
                        c >= 0 && c < GameConstants.WORLDSIZE ) {
                    // avoid center position
                    if ( r != row || c != col ){
                        list.add( new Position(r,c));
                    }
                }
            }
        }
        return list.iterator();
    }
}
