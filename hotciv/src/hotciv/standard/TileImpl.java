package hotciv.standard;

import hotciv.framework.Tile;

/**
 * Created by Christian on 11/11/2016.
 */
public class TileImpl implements Tile{



//Using fake it code to return ocean as terrain
    @Override
    public String getTypeString() {
        return "ocean";
    }
}
