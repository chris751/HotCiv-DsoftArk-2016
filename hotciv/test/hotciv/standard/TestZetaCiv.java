package hotciv.standard;

import hotciv.framework.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Christian on 02/12/2016.
 */
public class TestZetaCiv {

    private GameImpl game;

    @Before
    public void setUp() {
        game = new GameImpl(new ZetaFactory());
    }

    public void endRound(int roundCount){

        for(int i = 0; i < roundCount; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }
    }


    @Test
    public void shouldRedBeWinnerAfterConqureingAllCities(){
        assertThat(game.getPlayerInTurn(),is(Player.RED));
        City redCity = game.getCityAt(new Position(1,1));
        assertThat(redCity.getOwner(),is(Player.RED));
        assertThat(game.getAge(), is (4000));
        Unit redSettler = game.getUnitAt(new Position(4,3));
        assertThat(redSettler.getTypeString(),is(notNullValue()));
        assertThat(redSettler.getOwner(),is(Player.RED));
        game.moveUnit(new Position(4,3), new Position(4,2));
        game.moveUnit(new Position(4,2), new Position(4,1));
        City newRedCity = game.getCityAt(new Position(4,1));
        assertThat(newRedCity.getOwner(), is(Player.RED));
        assertThat(game.getWinner(), is(Player.RED));
    }
    @Ignore
    @Test
    public void shouldBlueWinnerAfterConqueringAllCities(){
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(),is(Player.BLUE));
        City blueCity = game.getCityAt(new Position(4,1));
        assertThat(blueCity.getOwner(),is(Player.BLUE));
        assertThat(game.getAge(), is (4000));
        Unit blueLegion = game.getUnitAt(new Position(3,2));
        assertThat(blueLegion.getTypeString(),is(notNullValue()));
        assertThat(blueLegion.getOwner(),is(Player.BLUE));
        game.moveUnit(new Position(3,2),new Position(3,1));
        game.moveUnit(new Position(3,1),new Position(2,1));
        assertThat(game.getUnitAt(new Position(2,1)).getOwner(),is(Player.BLUE));
        assertThat(game.getCityAt(new Position(1,1)).getOwner(), is(Player.RED));
        game.moveUnit(new Position(2,1),new Position(1,1));
        assertThat(game.getUnitAt(new Position(1,1)).getOwner(),is(Player.BLUE));
        City newBlueCity = game.getCityAt(new Position(1,1));
        assertThat(newBlueCity.getOwner(),is(Player.BLUE));
        assertThat(game.getWinner(),is(Player.BLUE));
    }

    @Test
    public void firstPlayerToWinThreeAttacksWinsGame() {
        endRound(22);
        game.endOfTurn();
        game.moveUnit(new Position(3,2), new Position(2,0));
        game.moveUnit(new Position(2,0), new Position(4,3));
        game.moveUnit(new Position(4,3), new Position(1,1));
        assertThat(game.getWinner(), is (Player.BLUE));
    }
}
