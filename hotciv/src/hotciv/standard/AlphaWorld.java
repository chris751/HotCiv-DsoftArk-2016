package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;

/**
 * Created by Christian on 15/11/2016.
 */
public class AlphaWorld implements WorldStrategy {

    HashMap<Position, Tile> worldTileMap = new HashMap<Position, Tile>();
    static HashMap<Position, CityImpl> cityMap = new HashMap<Position, CityImpl>();
    public static HashMap<Position, Unit> unitMap = new HashMap<Position, Unit>();

    public AlphaWorld() {
        createTileMap();
    }

    @Override
    public Tile createTile(String terrain) {
        Tile tile = new TileImpl(terrain);
        return tile;
    }

    public void createTileMap(){
        for(int i = 0; i<GameConstants.WORLDSIZE;i++){
            for(int j = 0; j<GameConstants.WORLDSIZE;j++){
                worldTileMap.put(new Position(i,j), createTile(GameConstants.PLAINS));
            }
            worldTileMap.put(new Position(0,1), createTile(GameConstants.HILLS));
            worldTileMap.put(new Position(1,0), createTile(GameConstants.OCEANS));
            worldTileMap.put(new Position(2,2), createTile(GameConstants.MOUNTAINS));

            cityMap.put(new Position(1,1), new CityImpl(Player.RED));
            cityMap.put(new Position(4,1), new CityImpl(Player.BLUE));

            unitMap.put(new Position(2,0), new UnitImpl(Player.RED, GameConstants.ARCHER));
            unitMap.put(new Position(3,2), new UnitImpl(Player.BLUE, GameConstants.LEGION));
            unitMap.put(new Position(4,3), new UnitImpl(Player.RED, GameConstants.SETTLER));
        }
    }

    public HashMap<Position, Unit> createUnit(Position p, Player owner, String unitType, HashMap<Position,Unit> unitMap){
        this.unitMap = unitMap;
        UnitImpl unit = new UnitImpl(owner, unitType);
        this.unitMap.put(p, unit);
        return this.unitMap;
    }


    public HashMap<Position, CityImpl> createCity(Position p, Player owner, HashMap<Position,CityImpl> cityMap) {
        this.cityMap = cityMap;
        CityImpl city = new CityImpl(owner);
        this.cityMap.put(p, city);
        return this.cityMap;
    }

    public HashMap<Position, Unit> slayTheUnitAtPosition(Position p, HashMap<Position, Unit> unitMap){
        this.unitMap = unitMap;
        this.unitMap.remove(p);
        return this.unitMap;
    }

    public HashMap<Position, Tile> getWorldTileMap() {
        return worldTileMap;
    }

    @Override
    public HashMap<Position, Unit> getUnitMap() {
        return unitMap;
    }

    @Override
    public HashMap<Position, CityImpl> getCityMap() {
        return cityMap;
    }
}
