package hotciv.standard;

        import hotciv.framework.*;
        import hotciv.standard.Factories.ThetaFactory;
        import org.junit.Before;
        import org.junit.Test;

        import static org.hamcrest.CoreMatchers.is;
        import static org.hamcrest.CoreMatchers.notNullValue;
        import static org.junit.Assert.assertThat;


        import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Christian on 02/12/2016.
 */
public class TestThetaCiv {

    private GameImpl game;

    @Before
    public void setUp() {
        game = new GameImpl(new ThetaFactory());
    }
    public void endRound(int roundCount){

        for(int i = 0; i < roundCount; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }
    }

    @Test
    public void cityShouldSelectToProduceBomb(){
        game.getCityAt(new Position(1,1)).changeProduction(OurConstants.BOMB);
        assertThat(game.getCityAt(new Position(1,1)).getProduction(), is(OurConstants.BOMB));
    }

    @Test
    public void cityShouldProduceBomb(){
        game.getCityAt(new Position(1,1)).changeProduction(OurConstants.BOMB);
        endRound(11);
        assertThat(game.getUnitAt(new Position(1,1)).getTypeString(),is(OurConstants.BOMB));
    }

    @Test
    public void bombShouldHave1DefenceAnd0Attack(){
        game.getCityAt(new Position(1,1)).changeProduction(OurConstants.BOMB);
        endRound(11);
        assertThat(game.getUnitAt(new Position(1,1)).getTypeString(),is(OurConstants.BOMB));
        assertThat(game.getUnitAt(new Position(1,1)).getTypeString(),is(OurConstants.BOMB));
        assertThat(game.getUnitAt(new Position(1,1)).getAttackingStrength(), is(0));
        assertThat(game.getUnitAt(new Position(1,1)).getDefensiveStrength(), is(1));
    }

    @Test
    public void testIfBombDestorys8NeigbouringUnitsAndBombDies(){
        game.getCityAt(new Position(1,1)).changeProduction(OurConstants.BOMB);
        endRound(11);
        game.moveUnit(new Position(1,1), new Position(3,1));

        assertThat(game.getUnitAt(new Position(2,0)).getTypeString(), is(GameConstants.ARCHER));
        assertThat(game.getUnitAt(new Position(3,2)).getTypeString(), is(GameConstants.LEGION));
        assertThat(game.getCityAt(new Position(4,1)), is(notNullValue()));
        assertThat(game.getUnitAt(new Position(3,1)).getTypeString(), is(OurConstants.BOMB));
        game.performUnitActionAt(new Position(3,1));

        assertThat(game.getUnitAt(new Position(3,1)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(2,0)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(3,2)), is(nullValue()));
        assertThat(game.getCityAt(new Position(4,1)), is(notNullValue()));
    }
}
