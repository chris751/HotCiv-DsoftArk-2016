package hotciv.standard;

import hotciv.framework.*;
import javafx.geometry.Pos;

import java.util.HashMap;

/**
 * Created by Christian on 15/11/2016.
 */
public class WorldImpl implements World {
    static Position p;

    static Position archer = new Position(2,0);
    static Position legion = new Position(3,2);
    static Position settler = new Position(4,3);




    static HashMap<Position, Tile> worldTileMap = new HashMap<Position, Tile>();
    static HashMap<Position, City> cityMap = new HashMap<Position, City>();
    static HashMap<Position, Unit> unitMap = new HashMap<Position, Unit>();

    public WorldImpl() {
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
}
