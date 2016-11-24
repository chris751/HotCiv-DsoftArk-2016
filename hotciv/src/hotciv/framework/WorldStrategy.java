package hotciv.framework;

import hotciv.standard.CityImpl;

import java.util.HashMap;

/**
 * Created by Christian on 15/11/2016.
 */
public interface WorldStrategy {
    //Creates a tile type at position p
    Tile createTile(String terrain);


    HashMap<Position,Tile> getWorldTileMap();
    HashMap<Position, Unit> getUnitMap();
    HashMap<Position, CityImpl> getCityMap();

    public HashMap<Position, Unit> slayTheUnitAtPosition(Position p, HashMap<Position, Unit> unitMap);
    public HashMap<Position, CityImpl> createCity(Position p, Player owner, HashMap<Position,CityImpl> cityMap);
    public HashMap<Position, Unit> createUnit(Position p, Player owner, String unitType, HashMap<Position,Unit> unitMap);

}
