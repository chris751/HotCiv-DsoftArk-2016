package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

/** Skeleton class for AlphaCiv test cases

    Updated Oct 2015 for using Hamcrest matchers

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/
public class TestAlphaCiv {
  private Game game;

  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl();
  }

  // FRS p. 455 states that 'Red is the first player to take a turn'.

  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void shouldBeBlueSecondTurn() {
    assertThat(game.getPlayerInTurn(),is(Player.RED));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(),is(Player.BLUE));
  }
  //test if ocean is in tile 1,0
  @Ignore
  @Test
  public void shouldBeOceanInTileOnePointZero() {
    Tile isOcean = game.getTileAt(new Position(1,0));

    assertThat(isOcean.getTypeString(), is(GameConstants.OCEANS));
  }



  /** REMOVE ME.  Not a test of HotCiv, just an example of what
      matchers the hamcrest library has... */
  /*
  @Test
  public void shouldDefinetelyBeRemoved() {
    assertThat("This is a dummy test", containsString("dummy"));
    String s = null;
    assertThat(s, is(nullValue()));
    s = "Ok";
    assertThat(s, is("Ok"));
    List<String> l = new ArrayList<String>();
    l.add("Bimse");
    l.add("Bumse");
    assertThat(l, hasItems(new String[] {"Bimse","Bumse"}));
  }*/
}
