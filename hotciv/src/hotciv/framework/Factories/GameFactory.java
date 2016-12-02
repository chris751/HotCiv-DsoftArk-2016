package hotciv.framework.Factories;

import hotciv.framework.*;

/**
 * Created by Christian on 02/12/2016.
 */
public interface GameFactory {

        public AgingStrategy createAgingStrategy();


    public WinningStrategy createWinningStrategy();

    UnitActionStrategy createActionStrategy();

    WorldStrategy createWorldStrategy();

    BattleStrategy createBattleStrategy();
}
