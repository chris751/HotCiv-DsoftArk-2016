package hotciv.standard;
import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Christian on 18/11/2016.
 */
public class TestBetaCiv {
    private Game game;

    /** Fixture for betaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new BetaAging(), new BetaWin());
    }

    @Test
    public void shouldAllCitiesBeRed(){
        City redCity = game.getCityAt(new Position(1,1));
        assertThat(redCity.getOwner(),is(Player.RED));
        Unit redSettler = game.getUnitAt(new Position(4,3));
        assertThat(redSettler.getTypeString(),is(notNullValue()));
        assertThat(redSettler.getOwner(),is(Player.RED));
        game.moveUnit(new Position(4,3), new Position(4,2));
        game.moveUnit(new Position(4,2), new Position(4,1));
        City newRedCity = game.getCityAt(new Position(4,1));
        assertThat(newRedCity.getOwner(), is(Player.RED));
    }
}
