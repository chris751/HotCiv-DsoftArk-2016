package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.Factories.GameFactory;
import hotciv.standard.*;

/**
 * Created by Christian on 02/12/2016.
 */
public class EpsilonFactory implements GameFactory {
    @Override
    public AgingStrategy createAgingStrategy() {
        return new AlphaAging();
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return new EpsilonWin();
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
        return new EpsilonBattle(new DiceBattleDecision());
    }
}
