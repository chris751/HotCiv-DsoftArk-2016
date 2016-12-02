package hotciv.standard;

import hotciv.framework.AgingStrategy;
import hotciv.framework.Factories.GameFactory;

/**
 * Created by Christian on 02/12/2016.
 */
public class TestGameFactory implements GameFactory{
    @Override
    public AgingStrategy createAgingStrategy() {
        return null;
    }
}
