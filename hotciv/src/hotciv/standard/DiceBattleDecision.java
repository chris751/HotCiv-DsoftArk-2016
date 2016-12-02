package hotciv.standard;

import hotciv.framework.BattleDescisionStrategy;

import java.util.Random;

/**
 * Created by Christian on 02/12/2016.
 */
public class DiceBattleDecision implements BattleDescisionStrategy{

    @Override
    public boolean winDecision(int defendStr, int attackStr) {

        Random attackDie = new Random();
        Random defendDie = new Random();
        if (attackStr*(attackDie.nextInt(6)+1)>defendStr*(defendDie.nextInt(6)+1)){
            return true;
        }else{
            return false;
        }
    }
}
