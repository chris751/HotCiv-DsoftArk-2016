package hotciv.standard;

import hotciv.framework.*;

import hotciv.standard.Factories.AlphaFactory;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
/**
 * Created by Christian on 07/12/2016.
 */
public class TestLogDecorator {
    private Game game;

    /** Fixture for alphaciv testing. */
    @Before
    public void setUp() {
        game = new LogDecorator(new GameImpl(new AlphaFactory()));

    }

    @Test
    public void endTurn(){
        game.endOfTurn();

    }
    @Test
    public void moveUnit(){
        game.moveUnit(new Position(2,0),new Position(2,1));
        assertThat(game.getUnitAt(new Position(2,1)),is(notNullValue()));

    }

}
