package hotciv.standard.Factories;

import hotciv.framework.*;
import hotciv.framework.Factories.GameFactory;

/**
 * Created by Christian on 02/12/2016.
 */
public class TestGameFactory implements GameFactory{
    @Override
    public AgingStrategy createAgingStrategy() {
        return null;
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return null;
    }

    @Override
    public UnitActionStrategy createActionStrategy() {
        return null;
    }

    @Override
    public WorldStrategy createWorldStrategy() {
        return null;
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return null;
    }
}
