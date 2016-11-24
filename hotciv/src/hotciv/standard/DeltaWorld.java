package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;

/**
 * Created by Christian on 24/11/2016.
 */
public class DeltaWorld implements WorldStrategy {

    HashMap<Position, Tile> worldTileMap = new HashMap<Position, Tile>();
    static HashMap<Position, CityImpl> cityMap = new HashMap<Position, CityImpl>();
    public static HashMap<Position, Unit> unitMap = new HashMap<Position, Unit>();

    public DeltaWorld() {
        createTileMap();
    }

    @Override
    public Tile createTile(String terrain) {
        Tile tile = new TileImpl(terrain);
        return tile;
    }

    @Override
    public HashMap<Position, Tile> getWorldTileMap() {
        return worldTileMap;
    }

    public void createTileMap() {
            // Basically we use a 'data driven' approach - code the
            // layout in a simple semi-visual representation, and
            // convert it to the actual Game representation.
            String[] layout =
                    new String[]{
                            "...ooMooooo.....",
                            "..ohhoooofffoo..",
                            ".oooooMooo...oo.",
                            ".ooMMMoooo..oooo",
                            "...ofooohhoooo..",
                            ".ofoofooooohhoo.",
                            "...ooo..........",
                            ".ooooo.ooohooM..",
                            ".ooooo.oohooof..",
                            "offfoooo.offoooo",
                            "oooooooo...ooooo",
                            ".ooMMMoooo......",
                            "..ooooooffoooo..",
                            "....ooooooooo...",
                            "..ooohhoo.......",
                            ".....ooooooooo..",
                    };
            // Conversion...

            String line;
            for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
                line = layout[r];
                for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                    char tileChar = line.charAt(c);
                    String type = "error";
                    if (tileChar == '.') {
                        type = GameConstants.OCEANS;
                    }
                    if (tileChar == 'o') {
                        type = GameConstants.PLAINS;
                    }
                    if (tileChar == 'M') {
                        type = GameConstants.MOUNTAINS;
                    }
                    if (tileChar == 'f') {
                        type = GameConstants.FOREST;
                    }
                    if (tileChar == 'h') {
                        type = GameConstants.HILLS;
                    }
                    Position p = new Position(r, c);
                    worldTileMap.put(p, createTile(type));
                }
            }
        }


    @Override
    public HashMap<Position, Unit> getUnitMap() {
        return null;
    }

    @Override
    public HashMap<Position, CityImpl> getCityMap() {
        return null;
    }

    @Override
    public HashMap<Position, Unit> slayTheUnitAtPosition(Position p, HashMap<Position, Unit> unitMap) {
        return null;
    }

    @Override
    public HashMap<Position, CityImpl> createCity(Position p, Player owner, HashMap<Position, CityImpl> cityMap) {
        return null;
    }

    @Override
    public HashMap<Position, Unit> createUnit(Position p, Player owner, String unitType, HashMap<Position, Unit> unitMap) {
        return null;
    }
}
