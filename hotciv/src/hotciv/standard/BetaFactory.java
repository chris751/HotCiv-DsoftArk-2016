package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.Factories.GameFactory;
import hotciv.standard.*;

/**
 * Created by Christian on 02/12/2016.
 */
public class BetaFactory implements GameFactory {
    @Override
    public AgingStrategy createAgingStrategy() {
        return new BetaAging();
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return new BetaWin();
    }

    @Override
    public UnitActionStrategy createActionStrategy() {
        return new AlphaUnitAction();
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
