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
    public void shouldRedBeWinnerAfterConqureingAllCities(){
        assertThat(game.getPlayerInTurn(),is(Player.RED));
        City redCity = game.getCityAt(new Position(1,1));
        assertThat(redCity.getOwner(),is(Player.RED));
        Unit redSettler = game.getUnitAt(new Position(4,3));
        assertThat(redSettler.getTypeString(),is(notNullValue()));
        assertThat(redSettler.getOwner(),is(Player.RED));
        game.moveUnit(new Position(4,3), new Position(4,2));
        game.moveUnit(new Position(4,2), new Position(4,1));
        City newRedCity = game.getCityAt(new Position(4,1));
        assertThat(newRedCity.getOwner(), is(Player.RED));
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void shouldBlueWinnerAfterConqueringAllCities(){
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(),is(Player.BLUE));
        City blueCity = game.getCityAt(new Position(4,1));
        assertThat(blueCity.getOwner(),is(Player.BLUE));
        Unit blueLegion = game.getUnitAt(new Position(3,2));
        assertThat(blueLegion.getTypeString(),is(notNullValue()));
        assertThat(blueLegion.getOwner(),is(Player.BLUE));
        game.moveUnit(new Position(3,2),new Position(3,1));
        game.moveUnit(new Position(3,1),new Position(2,1));
        game.moveUnit(new Position(2,1),new Position(1,1));
        City newBlueCity = game.getCityAt(new Position(1,1));
        assertThat(newBlueCity.getOwner(),is(Player.BLUE));
        assertThat(game.getWinner(),is(Player.BLUE));
    }

    @Test
    public void shouldBe4000bc() {
        assertThat(game.getAge(),is(4000));


    }
    @Test

    public void shouldAge100Between4000And3900(){
        assertThat(game.getAge(),is(4000));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getAge(),is(3900));
    }
}
