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

  //------------------------------------------------Turn and Aging test-------------------------------------------------

  // FRS p. 455 states that 'Red is the first player to take a turn'.
  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }
  //test if blue gets the turn after red
  @Test
  public void shouldBeBlueSecondTurn() {
    assertThat(game.getPlayerInTurn(),is(Player.RED));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(),is(Player.BLUE));
  }
  //test if that red gets the turn in a new round
  @Test
  public void newRound() {
    assertThat(game.getPlayerInTurn(),is(Player.RED));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(),is(Player.BLUE));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(),is(Player.RED));
  }
  //test is age is 100 after first round
  @Test
  public void shouldAgeOneHundredYearsAfterFirstRoundEnds(){
    assertThat(game.getAge(),is(4000));
    assertThat(game.getPlayerInTurn(),is(Player.RED));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(),is(Player.BLUE));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(),is(Player.RED));
    assertThat(game.getAge(),is(3900));
  }
  //Test if game age progress a hundred years after each round has ended
  @Test
  public void shouldAgeOneHundredYearsAfterEachRoundEnds(){
    int roundAge = 4000;
    for(int i=0; i<10; i++) {
      assertThat(game.getPlayerInTurn(), is(Player.RED));
      game.endOfTurn();
      assertThat(game.getPlayerInTurn(), is(Player.BLUE));
      game.endOfTurn();
      assertThat(game.getPlayerInTurn(), is(Player.RED));
      roundAge += -100;
      assertThat(game.getAge(), is(roundAge));
    }
  }
  //Test if the start age is 4000BC
  @Test
  public void shouldStartAt4000bc(){
    assertThat(game.getAge(), is(4000));
  }


//---------------------------------------------------TileMap world testing----------------------------------------------

  //test if ocean is in tile 1,0
  @Test
  public void shouldBeOceanInTile1_0() {
    Tile isOcean = game.getTileAt(new Position(1,0));
    assertThat(isOcean.getTypeString(), is(GameConstants.OCEANS));
  }

  //test if mountain is in tile 2,2
  @Test
  public void shouldBeMountainInTile2_2(){
    Tile isMountain = game.getTileAt(new Position(2,2));
    assertThat(isMountain.getTypeString(),is(GameConstants.MOUNTAINS));

  }

  //test if hills is in tile 0,1
  @Test
  public void shouldBeHillsInTile0_1(){
    Tile isHills = game.getTileAt(new Position(0,1));
    assertThat(isHills.getTypeString(),is(GameConstants.HILLS));
  }


  //Test if plains is in tiles 0,0 and 15,15. To make the assumption that most tiles is plains
  @Test
  public void shouldBePlainsInAllTilesTest0_0And15_15(){
    Tile isPlains0_0 = game.getTileAt(new Position(0,0));
    Tile isPlains15_15 = game.getTileAt(new Position(15,15));
    assertThat(isPlains0_0.getTypeString(), is(GameConstants.PLAINS));
    assertThat(isPlains15_15.getTypeString(), is(GameConstants.PLAINS));
  }

    //Make sure that there is a city at position (1,1)
    @Test
    public void shouldBeCityAt1_1(){
        assertThat(game.getCityAt(new Position(1,1)),is(notNullValue()));
    }
    //Make sure the city is red at position (1,1)
    @Test
    public void shouldBeRedCityAt1_1(){
        City cityPos = game.getCityAt(new Position(1,1));
        assertThat(cityPos.getOwner(),is(Player.RED));
    }
    //Make sure that city is blue at (4,1)
    @Test
    public void shouldBeBlueCityAt4_1(){
        City cityPos = game.getCityAt(new Position(4,1));
        assertThat(cityPos.getOwner(),is(Player.BLUE));
    }

  //-----------------------------------------------Winning test---------------------------------------------------------

  //test if RED wins in year 3000BC
  @Test
  public void redAlwaysWinInYear3000BC(){
    for(int i=0; i<10; i++) {
      game.endOfTurn();
      game.endOfTurn();
    }
      assertThat(game.getAge(),is(3000));
      assertThat(game.getWinner(), is(Player.RED));
  }
  //Make sure that Red doesn't always win
  @Test
  public void redShouldNotWinInYear3500BC(){
    for(int i=0; i<5; i++) {
      game.endOfTurn();
      game.endOfTurn();
    }
    assertThat(game.getAge(),is(3500));
    assertThat(game.getWinner(), is(nullValue()));
  }




  //--------------------------------------------------------Unit Archer Test--------------------------------------------
  //Make sure that there is a unit at position (2,0)
  @Test
  public void shouldBeUnitAt2_0(){
      assertThat(game.getUnitAt(new Position(2,0)),is(notNullValue()));
  }
  //Make sure that there is an archer at position (2,0)
  @Test
  public void shouldBeArcherAt2_0(){
      Unit archerUnit = game.getUnitAt(new Position(2,0));
      assertThat(archerUnit.getTypeString(),is(GameConstants.ARCHER));
  }
  //Make sure that there is an red archer at position (2,0)
  @Test
  public void shouldBeRedArcherAt2_0(){
      Unit archerUnit = game.getUnitAt(new Position(2,0));
      assertThat(archerUnit.getTypeString(),is(GameConstants.ARCHER));
      assertThat(archerUnit.getOwner(),is(Player.RED));
  }
  //--------------------------------------------------------Unit Legion Test -------------------------------------------
  //Make sure that there is a unit at position (3,2)
  @Test
  public void shouldBeUnitAt3_2(){
      assertThat(game.getUnitAt(new Position(3,2)),is(notNullValue()));
  }

  //Make sure that there is a legion at position (3,2)
  @Test
  public void shouldBeLegionAt3_2(){
      Unit legionUnit = game.getUnitAt(new Position(3,2));
      assertThat(legionUnit.getTypeString(),is(GameConstants.LEGION));
    }
  //Make sure that ther is a blue legion unit at position (3,2)
  @Test
  public void shouldBeBlueLegionAt3_2(){
      Unit legionUnit = game.getUnitAt(new Position(3,2));
      assertThat(legionUnit.getTypeString(),is(GameConstants.LEGION));
      assertThat(legionUnit.getOwner(),is(Player.BLUE));
  }

  //-------------------------------------------------------Settler Unit Test -------------------------------------------
  //Make sure that there is a unit at (4,3)
    @Test
  public void shouldBeUnitAt4_3(){
      assertThat(game.getUnitAt(new Position(4,3)),is(notNullValue()));
  }
  //Make sure that there is a settler unit at (4,3)
  @Test
  public void shouldBeSettlerAt4_3(){
      Unit settlerUnit = game.getUnitAt(new Position(4,3));
      assertThat(settlerUnit.getTypeString(),is(GameConstants.SETTLER));
    }

   //Make sure that there is a red settler at (4,3)
    @Test
    public void shouldBeRedSettlerAt4_3(){
        Unit settlerUnit = game.getUnitAt(new Position(4,3));
        assertThat(settlerUnit.getTypeString(),is(GameConstants.SETTLER));
        assertThat(settlerUnit.getOwner(),is(Player.RED));
    }
    //Make sure that an archer move from (2,0) to (2,1)

    //-----------------------------------------------------Movement test------------------------------------------------

    @Test
    public void shouldMoveArcherFrom2_0To2_1(){
        Unit archerUnit = game.getUnitAt(new Position(2,0));

        assertThat(archerUnit.getTypeString(),is(GameConstants.ARCHER));
        assertThat(game.moveUnit(new Position(2,0), new Position(2,1)),is(true));
        assertThat(game.getUnitAt(new Position(2,0)), is(nullValue()));

        archerUnit = game.getUnitAt(new Position(2,1));
        assertThat(archerUnit.getTypeString(), is(GameConstants.ARCHER));
    }

    @Test
    public void shouldMoveLegionFrom3_2To3_3() {
        Unit legion = game.getUnitAt((new Position(3, 2)));
        assertThat(legion.getTypeString(),is(GameConstants.LEGION));
        assertThat(game.moveUnit(new Position(3,2),new Position(3,3)),is(true));
        assertThat(game.getUnitAt(new Position(3,2)),is(nullValue()));
        legion = game.getUnitAt(new Position(3,3));
        assertThat(legion.getTypeString(),is(GameConstants.LEGION));

    }

    @Test
    public void shouldNotMoveOntoMountain(){
        Unit legion = game.getUnitAt((new Position(3,2)));
        Tile mountainTile = game.getTileAt(new Position(2,2));
        assertThat(mountainTile.getTypeString(), is(GameConstants.MOUNTAINS));
        assertThat(game.moveUnit(new Position(3,2), new Position(2,2)), is(false));
        assertThat(game.getUnitAt(new Position(3,2)), is(legion));
        assertThat(game.getUnitAt(new Position(2,2)), is(nullValue()));
    }

    @Test
    public void shouldNotMoveOntoOcean(){
        Unit Archer = game.getUnitAt(new Position(2,0));
        Tile oceanTile = game.getTileAt(new Position(1,0));
        assertThat(oceanTile.getTypeString(), is(GameConstants.OCEANS));
        assertThat(game.moveUnit(new Position(2,0),new Position(1,0)), is(false));
        assertThat(game.getUnitAt(new Position(2,0)), is(Archer));
        assertThat(game.getUnitAt(new Position(1,0)), is(nullValue()));
    }

    @Test
    public void shouldBeOnSameTileAsOtherFriendlyUnits(){
        assertThat(game.moveUnit(new Position(2,0), new Position(3,0)), is(true));
        assertThat(game.moveUnit(new Position(3,0), new Position(4,0)), is(true));
        assertThat(game.moveUnit(new Position(4,0), new Position(4,1)), is(true));
        assertThat(game.moveUnit(new Position(4,1), new Position(4,2)), is(true));
        assertThat(game.moveUnit(new Position(4,2), new Position(4,3)), is(false));

    }

    //---------------------------------------------------Attack Test----------------------------------------------------

    @Test
    public void AttackingUnitShouldWin(){
        Unit settler = game.getUnitAt(new Position(4,3));
        assertThat(settler.getTypeString(), is(GameConstants.SETTLER));
        Unit legion = game.getUnitAt(new Position(3,2));
        assertThat(legion.getTypeString(), is(GameConstants.LEGION));
        assertThat(game.moveUnit(new Position(4,3), new Position(3,2)), is(true));
        settler = game.getUnitAt(new Position(3,2));
        assertThat(settler.getTypeString(),is(GameConstants.SETTLER));
        assertThat(game.getUnitAt(new Position(4,3)), is(nullValue()));
    }

    //------------------------------------------------City Production test----------------------------------------------

    //Makes sure that the cities are producing stuff
    @Test
    public void cityShouldBeProducing(){
        City city = game.getCityAt(new Position(1,1));
        assertThat(city.getProduction(), is(notNullValue()));
    }
    //Makes sure that the production in city at 1,1 can be set to legion
    @Test
    public void shouldSetProductionToLegionAtCity1_1(){
        City city = game.getCityAt(new Position(1,1));
        game.changeProductionInCityAt(new Position(1,1),GameConstants.LEGION);
        assertThat(city.getProduction(),is(GameConstants.LEGION));
    }
    //Makes sure that the production in city at 1,1 can be set to archer
    @Test
    public void shouldSetProductionToArcherAtCity1_1(){
        City city = game.getCityAt(new Position(1,1));
        game.changeProductionInCityAt(new Position(1,1),GameConstants.ARCHER);
        assertThat(city.getProduction(),is(GameConstants.ARCHER));
    }
    //Makes sure that the production in city at 1,1 can be set to archer
    @Test
    public void shouldSetProductionToSettlerAtCity1_1(){
        City city = game.getCityAt(new Position(1,1));
        game.changeProductionInCityAt(new Position(1,1),GameConstants.SETTLER);
        assertThat(city.getProduction(),is(GameConstants.SETTLER));
    }
    //Makes sure that the production in city at 4,1 can be set to Legion
    @Test
    public void shouldSetProductionToLegionAtCity4_1(){
        City city = game.getCityAt(new Position(4,1));
        game.changeProductionInCityAt(new Position(4,1),GameConstants.LEGION);
        assertThat(city.getProduction(),is(GameConstants.LEGION));
    }

    //Make sure cities at (4,1) and (1,1) have a population of 1
    @Test
    public void shouldBePopulation1FirstRound(){
        City cityPosRed = game.getCityAt(new Position(4,1));
        City cityPosBlue = game.getCityAt(new Position(1,1));

        assertThat(cityPosRed.getSize(),is(1));
        assertThat(cityPosBlue.getSize(),is(1));

    }
}
