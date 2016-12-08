package hotciv.standard;

import hotciv.framework.MapLayoutStrategy;

/**
 * Created by Christian on 07/12/2016.
 */
public class DeltaMapLayout implements MapLayoutStrategy {

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

    public String[] getLayout(){
        return layout;
    }
}
