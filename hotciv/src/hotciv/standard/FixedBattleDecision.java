package hotciv.standard;

import hotciv.framework.BattleDescisionStrategy;

/**
 * Created by Christian on 02/12/2016.
 */
public class FixedBattleDecision implements BattleDescisionStrategy{


    @Override
    public boolean winDecision(int defendStr, int attackStr) {
        if(attackStr > defendStr){
            return true;
        }else{
            return false;
        }
    }

}
