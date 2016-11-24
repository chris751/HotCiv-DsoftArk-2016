package hotciv.standard;

import hotciv.framework.*;

import java.util.Iterator;

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
  AgingStrategy agingStrategy;
  WinningStrategy winningStrategy;
    UnitActionStrategy unitActionStrategy;
  WorldStrategy worldStrategy;

    public GameImpl(AgingStrategy agingStrategy, WinningStrategy winningStrategy, UnitActionStrategy unitActionStrategy, WorldStrategy worldStrategy) {
        this.agingStrategy = agingStrategy;
        this.winningStrategy = winningStrategy;
        this.unitActionStrategy = unitActionStrategy;
        this.worldStrategy = worldStrategy;
  }

  //which player has the turn, RED is set to begin
  Player whosTurn = RED;
  private AlphaWorld world = new AlphaWorld();

  //Gets a new tile terrainTile, and returns it.
  public Tile getTileAt( Position p ) {
    this.p = p;
    Tile tile = AlphaWorld.worldTileMap.get(p);
    return tile;
  }
  //Returns a unit at position p
  public Unit getUnitAt( Position p ) {
    return AlphaWorld.unitMap.get(p);
  }
  //Returns cityPosition at Position p
  public CityImpl getCityAt( Position p ) {
    return AlphaWorld.cityMap.get(p);
  }

  //returns the variable whosTurn
  public Player getPlayerInTurn() { return whosTurn; }

  //returns the winning player, which is RED at year 3000BC
  public Player getWinner() {
    return winningStrategy.getWinner(this);
  }

  //returns the current year of the game (age varaible)
  public int getAge() { return agingStrategy.getAge(); }

  //Returns true and removes the unit from current position, and adds the unit to the wanted position.
  //Unless the wanted position is either a mountain tile or ocean tile.
  public boolean moveUnit( Position from, Position to ) {

    String tileType = AlphaWorld.worldTileMap.get(to).getTypeString();
    Player thisUnitOwner = AlphaWorld.unitMap.get(from).getOwner();;
    Player otherUnitOwner;

    //Test variables for obstacles
    boolean thisPlayersTurn = getPlayerInTurn().equals(thisUnitOwner);
    boolean isMountain = tileType.equals(GameConstants.MOUNTAINS);
    boolean isOcean = tileType.equals(GameConstants.OCEANS);
    boolean isFriendlyUnit = false;
    boolean isEnemyUnit = false;
    boolean isEmpty = AlphaWorld.unitMap.get(to)== null;
    boolean isEnemyCity = false;
    boolean noCity = AlphaWorld.cityMap.get(to) == null;

    //If there is a unit at the wanted tile, test if it is friend or foe, and save it in parameters.
    if(!isEmpty){
      otherUnitOwner = AlphaWorld.unitMap.get(to).getOwner();
      isFriendlyUnit = thisUnitOwner.equals(otherUnitOwner);
      isEnemyUnit = !thisUnitOwner.equals(otherUnitOwner);

    }
    if(!noCity){
      isEnemyCity = !AlphaWorld.cityMap.get(to).getOwner().equals(getPlayerInTurn());
    }

    //Decision making, whether to allow the movement or not.
    if(!thisPlayersTurn){return false;}
    else if(isMountain) {return false;}
    else if(isOcean){return false;}
    else if(isFriendlyUnit){return false;}
    else if(isEmpty || isEnemyUnit){
        if(isEnemyCity){
          AlphaWorld.cityMap.put(to,new CityImpl(getPlayerInTurn()));
          Unit unit = AlphaWorld.unitMap.get(from);
          AlphaWorld.unitMap.remove(from);
          AlphaWorld.unitMap.put(to, unit);
          return true;
        }else {
          Unit unit = AlphaWorld.unitMap.get(from);
          AlphaWorld.unitMap.remove(from);
          AlphaWorld.unitMap.put(to, unit);
          return true;
        }
    }
    return false;
  }

  //at the end of turn switch turn to the correct player and set age to 100
  public void endOfTurn() {

    if(whosTurn == BLUE){
      whosTurn = RED;
      agingStrategy.aging(); // get older
      AlphaWorld.cityMap.get(new Position(1,1)).addProductionValue();
      AlphaWorld.cityMap.get(new Position(4,1)).addProductionValue();
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

    int settlerCost = 30;
    int archerCost = 10;
    int legionCost = 15;

    //While loop, Running through the cityHashMap, and produces units, if the city has enough productionValue.

    Iterator<Position> keySetIterator = AlphaWorld.cityMap.keySet().iterator();

    while(keySetIterator.hasNext()){
      Position position = keySetIterator.next();
      CityImpl city = AlphaWorld.cityMap.get(position);
      String cityProduction = city.getProduction();
      int currentValue = city.getProductionValue();
      boolean ArcherProducing = cityProduction.equals(GameConstants.ARCHER);
      boolean LegionProducing = cityProduction.equals(GameConstants.LEGION);
      boolean SettlerProducing = cityProduction.equals(GameConstants.SETTLER);
      boolean enoughForArcher = currentValue >= archerCost;
      boolean enoughForLegion = currentValue >= legionCost;
      boolean enoughForSettler = currentValue >= settlerCost;

      if(ArcherProducing && enoughForArcher){
        city.buyUnit(archerCost);
        AlphaWorld.createUnit(position, city.getOwner(), cityProduction);
      }else if (LegionProducing && enoughForLegion){
        city.buyUnit(legionCost);
        AlphaWorld.createUnit(position, city.getOwner(), cityProduction);
      }else if (SettlerProducing && enoughForSettler){
        city.buyUnit(settlerCost);
        AlphaWorld.createUnit(position, city.getOwner(), cityProduction);
      }
    }

  }
  public void performUnitActionAt( Position p ) {
      unitActionStrategy.unitAction(this, p);
  }

  public AlphaWorld getWorld(){
    return this.world;
  }

}
