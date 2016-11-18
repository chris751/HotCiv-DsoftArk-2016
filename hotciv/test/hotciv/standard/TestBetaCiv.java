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
        
    }
}
