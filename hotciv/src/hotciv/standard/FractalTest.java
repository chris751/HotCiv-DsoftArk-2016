package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.standard.Factories.AdapterFactory;
import thirdparty.ThirdPartyFractalGenerator;

/**
 * Created by Christian on 09/12/2016.
 */
public class FractalTest {


    private static ThirdPartyFractalGenerator fractalGenerator;
    private static String fractalTileMap;
    private static String[] mapLayout = new String[100];

    public static void main(String[] args) {
        Game game = new GameImpl(new AdapterFactory());

        for(int r = 0; r <16; r++){
            for(int c = 0; c <16; c++){
                System.out.print(game.getTileAt(new Position(r,c)).getTypeString() + ", ");
            }
            System.out.println("");
        }
    }
}
