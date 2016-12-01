package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Christian on 01/12/2016.
 */
public class TestEpsilonCiv {

    private Game game;

    /** Fixture for betaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new AlphaAging(), new AlphaWin(), new AlphaUnitAction(), new AlphaWorld(), new AlphaBattle());
    }

    public Position pos(int y, int x){
        return new Position(y,x);
    }

    @Ignore
    @Test
    public void whenAttackingUnitWinsBattleDeleteDefeatedAndMove() {
        assertThat(game.getUnitAt(new Position(2,0)), is(notNullValue()));
        game.moveUnit(new Position(2,0), new Position(3,0));
        game.endOfTurn();
        game.endOfTurn();
        game.moveUnit(new Position(3,0), new Position(3,1));
        assertThat(game.moveUnit(new Position(3,1), new Position(3,2)), is(true));
    }

    @Test
    public void shouldGet2TimesAttackingStrengthWhenOnForrestOrHill(){
        assertThat(game.getUnitAt(new Position(2,0)), is(notNullValue()));
        game.moveUnit(pos(2,0),pos(2,1));
        game.endOfTurn();
        game.endOfTurn();
        game.moveUnit(pos(2,1),pos(1,1));
        game.endOfTurn();
        game.endOfTurn();
        game.moveUnit(pos(1,1),pos(0,1));
        assertThat(game.getTileAt(pos(0,1)).getTypeString(), is(GameConstants.HILLS));
        assertThat(game.getUnitAt(pos(0,1)).getTypeString(), is(GameConstants.ARCHER));

    }
}
