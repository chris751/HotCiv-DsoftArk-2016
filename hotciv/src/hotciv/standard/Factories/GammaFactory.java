package hotciv.standard.Factories;

import hotciv.framework.*;
import hotciv.framework.Factories.GameFactory;
import hotciv.standard.*;

/**
 * Created by Christian on 02/12/2016.
 */
public class GammaFactory implements GameFactory {
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
        return new GammaUnitAction();
    }

    @Override
    public WorldStrategy createWorldStrategy() {
        return new AlphaWorld();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return new AlphaBattle();
    }
}
