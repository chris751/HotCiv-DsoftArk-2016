package hotciv.standard.Factories;

import hotciv.framework.*;
import hotciv.framework.Factories.GameFactory;
import hotciv.standard.*;

/**
 * Created by Christian on 02/12/2016.
 */
public class SemiFactory implements GameFactory {
    @Override
    public AgingStrategy createAgingStrategy() {
        return new BetaAging();
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return new EpsilonWin();
    }

    @Override
    public UnitActionStrategy createActionStrategy() {
        return new GammaUnitAction();
    }

    @Override
    public WorldStrategy createWorldStrategy() {
        return new DeltaWorld(new DeltaMapLayout().getLayout());
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return new EpsilonBattle(new DiceBattleDecision());
    }
}
