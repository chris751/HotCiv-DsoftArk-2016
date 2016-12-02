package hotciv.standard;

import hotciv.framework.Game;
import hotciv.standard.Factories.TestGameFactory;
import org.junit.Before;

/**
 * Created by Christian on 02/12/2016.
 */
public class TestAbstractFactory {
    Game game;

    @Before
    public void setUp(){
        game = new GameImpl(new TestGameFactory());

    }
}
