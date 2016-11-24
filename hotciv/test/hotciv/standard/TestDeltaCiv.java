package hotciv.standard;

import hotciv.framework.Game;
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
        game = new GameImpl(new AlphaAging(), new AlphaWin(), new AlphaUnitAction(), new AlphaWorld());
    }




}
