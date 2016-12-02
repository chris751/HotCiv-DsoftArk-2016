package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import org.junit.*;
import org.junit.validator.PublicClassValidator;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Christian on 01/12/2016.
 */
public class TestEpsilonCiv {

    private GameImpl game;
    private Iterator<Position> iter;
    private ArrayList<Position> neighborhood;
    private Position center, p;


    /** Fixture for betaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new AlphaAging(), new EpsilonWin(), new AlphaUnitAction(), new AlphaWorld(), new EpsilonBattle(new FixedBattleDecision()));
    }
    /** helper method to insert elements in an iterator into a list. */
    private ArrayList<Position> convertIteration2List(Iterator<Position> iter) {
        neighborhood = new ArrayList<Position>();
        while ( iter.hasNext() ) {
            p = iter.next();
            neighborhood.add(p);
        }
        return neighborhood;
    }

    public Position pos(int y, int x){
        return new Position(y,x);
    }




    @Test public void shouldGiveCorrectTerrainFactors() {
        // plains have multiplier 1
        assertEquals( 1, EpsilonBattle.getTerrainFactor( game, new Position(0,0)));
        // hills have multiplier 2
        assertEquals( 2, EpsilonBattle.getTerrainFactor( game, new Position(0,1)));
        // cities have multiplier 3
        assertEquals( 3, EpsilonBattle.getTerrainFactor( game, new Position(1,1)));
    }
    @Test public void shouldGive8PositionsForP8_8() {
        center = new Position(8,8);
        iter = EpsilonBattle.get8NeighborhoodIterator(center);
        neighborhood = convertIteration2List( iter );

        assertTrue( "Must contain (7,7)",
                neighborhood.contains( new Position(7,7)));
        assertTrue( "Must contain (9,9)",
                neighborhood.contains( new Position(9,9)));
        assertTrue( "Must contain (7,9)",
                neighborhood.contains( new Position(7,9)));
        assertTrue( "Must contain (8,7)",
                neighborhood.contains( new Position(8,7)));

        assertFalse( "Must not contain center position",
                neighborhood.contains( center ));

        assertFalse( "Must not contain (5,5) position",
                neighborhood.contains( new Position(5,5) ));

        assertEquals( "Should be 8 positions in the iterator",
                8, neighborhood.size());
    }

    @Test public void shouldGive3PositionsForP0_0() {
        center = new Position(0,0);
        iter = EpsilonBattle.get8NeighborhoodIterator(center);
        neighborhood = convertIteration2List( iter );

        assertTrue( "Must contain (1,0)",
                neighborhood.contains( new Position(1,0)));
        assertTrue( "Must contain (1,1)",
                neighborhood.contains( new Position(1,1)));
        assertTrue( "Must contain (0,1)",
                neighborhood.contains( new Position(0,1)));

        assertEquals( "Should be 3 positions in the iterator",
                3, neighborhood.size());

    }
    @Test public void shouldGive3PositionsForP15_15() {
        center = new Position(15,15);
        iter = EpsilonBattle.get8NeighborhoodIterator(center);
        neighborhood = convertIteration2List( iter );

        assertTrue( "Must contain (14,15)",
                neighborhood.contains( new Position(14,15)));
        assertTrue( "Must contain (14,14)",
                neighborhood.contains( new Position(14,14)));
        assertTrue( "Must contain (15,14)",
                neighborhood.contains( new Position(15,14)));

        assertEquals( "Should be 3 positions in the iterator",
                3, neighborhood.size());

    }
    @Test public void shouldGiveSum1ForBlueAtP4_1() {
        assertEquals("Blue unit at (4,1) should get +1 support",
                +1, EpsilonBattle.getFriendlySupport( game, new Position(4,1), Player.BLUE));
    }
    @Test public void shouldGiveSum0ForBlueAtP0_3() {
        assertEquals("Blue unit at (0,3) should get +0 support",
                +0, EpsilonBattle.getFriendlySupport( game, new Position(0,3), Player.BLUE));
    }

    //Test nedenstående er hvor der er ingen tegningslag
    @Test
    public void bigAttackStrWinsOverDefendStr() {
        assertThat(game.getUnitAt(pos(2,0)).getTypeString(),is(GameConstants.ARCHER));
        game.moveUnit(pos(2,0),pos(1,1));
        game.endOfTurn();
        assertThat(game.getUnitAt(pos(3,2)).getTypeString(),is(GameConstants.LEGION));
        game.moveUnit(pos(3,2),pos(2,1));
        assertThat(game.getUnitAt(pos(1,1)),is(notNullValue()));
        game.moveUnit(pos(2,1),pos(1,1));
        assertThat(game.getUnitAt(pos(2,1)),is(nullValue()));
        assertThat(game.getUnitAt(pos(1,1)).getTypeString(),is(GameConstants.ARCHER));
        assertThat((game.getUnitAt(pos(1,1)).getOwner()),is(Player.RED));
    }

    @Test
    public void firstPlayerToWinThreeAttacksWinsGame() {
        System.out.print(game.getUnitMap());
        System.out.print(game.getUnitAt(pos(1, 1)).getOwner());
        System.out.print(game.getUnitAt(pos(3, 3)).getOwner());
        System.out.print(game.getUnitAt(pos(4, 1)).getOwner());
        assertThat(game.getUnitAt(pos(4,3)).getTypeString(),is(GameConstants.SETTLER));
        game.endOfTurn();
        game.endOfTurn();

        game.endOfTurn();
        game.endOfTurn();

        game.endOfTurn();
        game.endOfTurn();

        game.endOfTurn();
        game.endOfTurn();

        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getUnitAt(pos(1, 1)).getTypeString(), is(GameConstants.SETTLER));
        assertThat(game.getUnitAt(pos(4, 1)).getTypeString(), is(GameConstants.SETTLER));

        assertThat(game.getUnitAt(pos(1, 1)).getOwner(), is(Player.RED));
        assertThat(game.getUnitAt(pos(4, 1)).getOwner(), is(Player.BLUE));

        game.moveUnit(pos(1,1), pos(2,1));
        game.moveUnit(pos(4,1), pos(5,1));

        game.endOfTurn();
        game.endOfTurn();

        game.endOfTurn();
        game.endOfTurn();

        game.endOfTurn();
        game.endOfTurn();

        game.endOfTurn();
        game.endOfTurn();

        game.endOfTurn();
        game.endOfTurn();

        assertThat(game.getUnitAt(pos(1, 1)).getTypeString(), is(GameConstants.SETTLER));
        assertThat(game.getUnitAt(pos(4, 1)).getTypeString(), is(GameConstants.SETTLER));

        assertThat(game.getUnitAt(pos(1, 1)).getOwner(), is(Player.RED));
        assertThat(game.getUnitAt(pos(4, 1)).getOwner(), is(Player.BLUE));

        assertThat(game.getUnitAt(pos(4,3)).getAttackingStrength(), is(0));
        System.out.println(game.getUnitAt(pos(3,2)).getDefensiveStrength());

        game.moveUnit(pos(4,3),pos(3,2));
        assertThat(game.getUnitAt(pos(3,2)).getTypeString(), is(GameConstants.LEGION));
        game.moveUnit(pos(1,1),pos(0,7));
        game.moveUnit(pos(0,7),pos(3,2));
        assertThat(game.getUnitAt(pos(3,2)).getTypeString(), is(GameConstants.LEGION));
        game.moveUnit(pos(2,1),pos(3,2));
        assertThat(game.getUnitAt(pos(3,2)).getTypeString(), is(GameConstants.LEGION));

        System.out.print(game.getRedWinCounter());
        System.out.print(game.getBlueWinCounter());
        assertThat(game.getWinner(), is(Player.BLUE));
    }
}
