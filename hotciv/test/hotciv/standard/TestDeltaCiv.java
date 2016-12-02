package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
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
        game = new GameImpl(new DeltaFactory());
    }

    @Test
    public void shouldBeOceanAt1_1And15_15(){
        assertThat(game.getTileAt(new Position(1,1)).getTypeString(), is(GameConstants.OCEANS));
        assertThat(game.getTileAt(new Position(15,15)).getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void shouldBeRedCityAt8_12(){
        assertThat(game.getTileAt(new Position(8,12)), is(notNullValue()));
        assertThat(game.getCityAt(new Position(8,12)).getOwner(),is(Player.RED));
    }

    @Test
    public void blueCityAt4_5(){
        assertThat(game.getTileAt(new Position(4,5)), is(notNullValue()));
        assertThat(game.getCityAt(new Position(4,5)).getOwner(),is(Player.BLUE));
    }

    @Test
    public void shouldBeBlueLegionAt4_4(){
        assertThat(game.getTileAt(new Position(4,4)), is(notNullValue()));
        assertThat(game.getUnitAt(new Position(4,4)).getTypeString(),is(GameConstants.LEGION));
        assertThat(game.getUnitAt(new Position(4,4)).getOwner(),is(Player.BLUE));
    }

    @Test
    public void shouldBeRedArcherAt3_8(){
        assertThat(game.getTileAt(new Position(3,8)), is(notNullValue()));
        assertThat(game.getUnitAt(new Position(3,8)).getTypeString(),is(GameConstants.ARCHER));
        assertThat(game.getUnitAt(new Position(3,8)).getOwner(),is(Player.RED));
    }

    @Test
    public void shouldBeRedSettlerAt5_5(){
        assertThat(game.getTileAt(new Position(5,5)), is(notNullValue()));
        assertThat(game.getUnitAt(new Position(5,5)).getTypeString(),is(GameConstants.SETTLER));
        assertThat(game.getUnitAt(new Position(5,5)).getOwner(),is(Player.RED));
    }
}
