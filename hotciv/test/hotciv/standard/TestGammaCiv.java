package hotciv.standard;

import hotciv.framework.*;

import hotciv.standard.Factories.GammaFactory;
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
        game = new GameImpl(new GammaFactory());
    }

    @Test
    public void settlerCanProduceCity(){
        assertThat(game.getUnitAt(new Position(4,3)).getTypeString(), is(GameConstants.SETTLER));
        game.performUnitActionAt(new Position(4,3));
        assertThat(game.getCityAt(new Position(4,3)), is(notNullValue()));

    }

    @Test
    public void settlerOwnerIsTheSameAsProducedCity(){
        assertThat(game.getUnitAt(new Position(4,3)).getTypeString(), is(GameConstants.SETTLER));
        assertThat(game.getUnitAt(new Position(4,3)).getOwner(), is(Player.RED));
        game.performUnitActionAt(new Position(4,3));
        assertThat(game.getCityAt(new Position(4,3)), is(notNullValue()));
        assertThat(game.getCityAt(new Position(4,3)).getOwner(),is(Player.RED));
    }

    @Test
    public void settlerShouldBeRemovedWhenBuildingACity(){
        assertThat(game.getUnitAt(new Position(4,3)).getTypeString(), is(GameConstants.SETTLER));
        game.performUnitActionAt(new Position(4,3));
        assertThat(game.getCityAt(new Position(4,3)), is(notNullValue()));
        assertThat(game.getUnitAt(new Position(4,3)), is(nullValue()));

    }

    @Test
    public void archerFortificationShouldHaveDoubleAmountStrength(){
        assertThat(game.getUnitAt(new Position(2,0)).getTypeString(), is(GameConstants.ARCHER));
        game.performUnitActionAt(new Position(2,0));
        assertThat(game.getUnitAt(new Position(2,0)).getDefensiveStrength(),is(6));
    }

    @Test
    public void shouldNotMoveWhenFortified(){
        assertThat(game.getUnitAt(new Position(2,0)).getTypeString(), is(GameConstants.ARCHER));
        game.performUnitActionAt(new Position(2,0));
        assertThat(game.getUnitAt(new Position(2,0)).getMoveCount(),is(0));
    }

    @Test
    public void shouldbeAbleToMoveWhenNotFortified(){
        assertThat(game.getUnitAt(new Position(2,0)).getTypeString(), is(GameConstants.ARCHER));
        assertThat(game.getUnitAt(new Position(2,0)).getMoveCount(),is(1));
    }

    @Test
    public void shouldBeAbleToReverseFortify(){
        assertThat(game.getUnitAt(new Position(2,0)).getTypeString(), is(GameConstants.ARCHER));
        game.performUnitActionAt(new Position(2,0));
        assertThat(game.getUnitAt(new Position(2,0)).getDefensiveStrength(),is(6));
        game.performUnitActionAt(new Position(2,0));
        assertThat(game.getUnitAt(new Position(2,0)).getDefensiveStrength(),is(3));
    }
}
