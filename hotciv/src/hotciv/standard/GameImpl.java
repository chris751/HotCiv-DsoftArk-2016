package hotciv.standard;

import hotciv.framework.*;

import static hotciv.framework.Player.*;

/** Skeleton implementation of HotCiv.
 
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

public class GameImpl implements Game {
  //@param p to enable access to the positions
  public static Position p;

  //which player has the turn, RED is set to begin
  Player whosTurn = RED;
  //Starting year
  int age = 4000;
  private WorldImpl world = new WorldImpl();

  //Gets a new tile terrainTile, and returns it.
  public Tile getTileAt( Position p ) {
    this.p = p;
    Tile tile = WorldImpl.worldTileMap.get(p);
    return tile;
  }
  //Returns a unit at position p
  public Unit getUnitAt( Position p ) {
    return WorldImpl.unitMap.get(p);
  }
  //Returns cityPosition at Position p
  public CityImpl getCityAt( Position p ) {
    return WorldImpl.cityMap.get(p);
  }

  //returns the variable whosTurn
  public Player getPlayerInTurn() { return   whosTurn; }

  //returns the winning player, which is RED at year 3000BC
  public Player getWinner() {
    if (getAge()==3000){
      return RED;
    }
    else{
      return null;
    }

  }

  //returns the current year of the game (age varaible)
  public int getAge() { return age; }

  //Returns true and removes the unit from current position, and adds the unit to the wanted position.
  //Unless the wanted position is either a mountain tile or ocean tile.
  public boolean moveUnit( Position from, Position to ) {

    String tileType = WorldImpl.worldTileMap.get(to).getTypeString();
    Player thisUnitOwner = WorldImpl.unitMap.get(from).getOwner();;
    Player otherUnitOwner;

    //Test variables for obstacles
    boolean thisPlayersTurn = getPlayerInTurn().equals(thisUnitOwner);
    boolean isMountain = tileType.equals(GameConstants.MOUNTAINS);
    boolean isOcean = tileType.equals(GameConstants.OCEANS);
    boolean isFriendlyUnit = false;
    boolean isEnemyUnit = false;
    boolean isEmpty = WorldImpl.unitMap.get(to)== null;

    //If there is a unit at the wanted tile, test if it is friend or foe, and save it in parameters.
    if(!isEmpty){
      otherUnitOwner = WorldImpl.unitMap.get(to).getOwner();
      isFriendlyUnit = thisUnitOwner.equals(otherUnitOwner);
      isEnemyUnit = !thisUnitOwner.equals(otherUnitOwner);
    }

    //Decision making, whether to allow the movement or not.
    if(!thisPlayersTurn){return false;}
    else if(isMountain) {return false;}
    else if(isOcean){return false;}
    else if(isFriendlyUnit){return false;}
    else if(isEmpty || isEnemyUnit){
      Unit unit = WorldImpl.unitMap.get(from);
      WorldImpl.unitMap.remove(from);
      WorldImpl.unitMap.put(to, unit);
      return true;
    }
    return false;
  }

  //at the end of turn switch turn to the correct player and set age to 100
  public void endOfTurn() {

    if(whosTurn == BLUE){
      whosTurn = RED;
      age += -100;
      WorldImpl.cityMap.get(new Position(1,1)).addProductionValue();
      WorldImpl.cityMap.get(new Position(4,1)).addProductionValue();
      produceUnit();
    }
    else{
      whosTurn = BLUE;
    }

  }

  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {
    CityImpl city = getCityAt(p);
    city.changeProduction(unitType);
  }

  public void produceUnit(){
    CityImpl redCity = WorldImpl.cityMap.get(new Position(1,1));
    int redCurrentValue = redCity.getProductionValue();

    CityImpl blueCity = WorldImpl.cityMap.get(new Position(4,1));
    int blueCurrentValue = blueCity.getProductionValue();

    String redUnitProducing = redCity.getProduction();
    String blueUnitProducing = blueCity.getProduction();
    int settlerCost = 30;
    int archerCost = 10;
    int legionCost = 15;

    boolean redArcherProducing = redUnitProducing.equals(GameConstants.ARCHER);
    boolean redLegionProducing = redUnitProducing.equals(GameConstants.LEGION);
    boolean redSettlerProducing = redUnitProducing.equals(GameConstants.SETTLER);

    boolean blueArcherProducing = blueUnitProducing.equals(GameConstants.ARCHER);
    boolean blueLegionProducing = blueUnitProducing.equals(GameConstants.LEGION);
    boolean blueSettlerProducing = blueUnitProducing.equals(GameConstants.SETTLER);

      if(redArcherProducing && redCurrentValue >= archerCost){
        redCity.buyUnit(archerCost);
        WorldImpl.createUnit(new Position(1,1), Player.RED, GameConstants.ARCHER);
      } else if(redSettlerProducing && redCurrentValue >= settlerCost){
        redCity.buyUnit(settlerCost);
        WorldImpl.createUnit(new Position(1,1), Player.RED, GameConstants.SETTLER);
      } else if(redLegionProducing && redCurrentValue >= legionCost){
        redCity.buyUnit(legionCost);
        WorldImpl.createUnit(new Position(1,1), Player.RED, GameConstants.LEGION);
      }

    if(blueArcherProducing && blueCurrentValue >= archerCost){
      blueCity.buyUnit(archerCost);
      WorldImpl.createUnit(new Position(4,1), Player.BLUE, GameConstants.ARCHER);
    } else if(blueSettlerProducing && blueCurrentValue >= settlerCost){
      blueCity.buyUnit(settlerCost);
      WorldImpl.createUnit(new Position(4,1), Player.BLUE, GameConstants.SETTLER);
    } else if(blueLegionProducing && blueCurrentValue >= legionCost){
      blueCity.buyUnit(legionCost);
      WorldImpl.createUnit(new Position(4,1), Player.BLUE, GameConstants.LEGION);
    }

  }
  public void performUnitActionAt( Position p ) {}
}
