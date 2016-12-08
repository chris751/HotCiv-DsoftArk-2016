package hotciv.standard.Factories;

import hotciv.framework.*;
import hotciv.framework.Factories.GameFactory;
import hotciv.standard.*;
import thirdparty.ThirdPartyFractalGenerator;

/**
 * Created by Christian on 08/12/2016.
 */
public class AdapterFactory implements GameFactory {
    @Override
    public AgingStrategy createAgingStrategy() {
        return new AlphaAging();
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return new AlphaWin();
    }

    @Override
    public UnitActionStrategy createActionStrategy() {
        return new AlphaUnitAction();
    }

    @Override
    public WorldStrategy createWorldStrategy() {
        return new DeltaWorld(new FractalAdapter(new ThirdPartyFractalGenerator()).getLayout());
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return new AlphaBattle();
    }
}
