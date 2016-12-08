package hotciv.standard;

import hotciv.framework.*;
import thirdparty.ThirdPartyFractalGenerator;

/**
 * Created by Christian on 08/12/2016.
 */
public class FractalAdapter implements MapLayoutStrategy {
    private ThirdPartyFractalGenerator fractalGenerator;
    private String fractalTileMap;
    private String[] mapLayout = new String[100];

    public FractalAdapter(ThirdPartyFractalGenerator fractalGenerator) {
        this.fractalGenerator = fractalGenerator;
    }

    @Override
    public String[] getLayout() {
        for(int r = 0; r <16; r++){
            for(int c = 0; c <16; c++){
                fractalTileMap = fractalTileMap + String.valueOf(fractalGenerator.getLandscapeAt(r,c));

            }
            mapLayout[r] = fractalTileMap;
        }
        System.out.println(mapLayout);
        return mapLayout;
    }
}
