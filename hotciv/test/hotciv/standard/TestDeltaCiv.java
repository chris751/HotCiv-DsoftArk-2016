package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Christian on 24/11/2016.
 */
public class TestDeltaCiv {
    private Game game;

    /** Fixture for betaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new AlphaAging(), new AlphaWin(), new AlphaUnitAction(), new DeltaWorld());
    }

    @Test
    public void shouldBeOceanAt1_1And16_16(){
        assertThat(game.getTileAt(new Position(1,1)).getTypeString(), is(GameConstants.OCEANS));
        assertThat(game.getTileAt(new Position(15,15)).getTypeString(), is(GameConstants.OCEANS));
    }
}
