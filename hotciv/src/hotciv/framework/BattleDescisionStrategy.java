package hotciv.framework;

/**
 * Created by Christian on 02/12/2016.
 */
public interface BattleDescisionStrategy {

    boolean winDecision(int defendStr, int attackStr);
}
