package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Christian on 24/11/2016.
 */
public class TestGammaCiv {
    private Game game;

    /** Fixture for betaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new AlphaAging(), new AlphaWin(), new GammaUnitAction());
    }

    @Test
    public void settlerCanProdudeCity(){
        assertThat(game.getUnitAt(new Position(4,3)).getTypeString(), is(GameConstants.SETTLER));
        game.performUnitActionAt(new Position(4,3));
        assertThat(game.getCityAt(new Position(4,3)), is(notNullValue()));

    }
}
