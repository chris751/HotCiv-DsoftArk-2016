package hotciv.standard;

import hotciv.framework.AgingStrategy;
import hotciv.framework.Game;

/**
 * Created by Christian on 18/11/2016.
 */
public class AlphaAging implements AgingStrategy {
    int age = 4000;

    @Override
    public void aging() {
        age += -100;
    }

    @Override
    public int getAge() {
        return age;
    }
}
