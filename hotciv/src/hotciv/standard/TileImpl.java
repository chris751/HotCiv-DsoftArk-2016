package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Tile;

/**
 * Created by Christian on 11/11/2016.
 */
public class TileImpl implements Tile{

    private String type;

    public TileImpl(String type) {
        this.type = type;

    }

    //The tile has to be either ocean or mountain, an if statement is used to differenciete.
    @Override
    public String getTypeString() {
        return type;
    }
}
