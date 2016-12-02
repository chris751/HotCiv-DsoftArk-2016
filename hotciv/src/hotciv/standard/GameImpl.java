package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.Factories.GameFactory;

import java.util.HashMap;
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
    BattleStrategy battleStrategy;

    HashMap<Position, Tile> tileMap;
    HashMap<Position, Unit> unitMap;
    HashMap<Position, CityImpl> cityMap;

  private int redWinCounter;
  private int blueWincounter;
  private GameFactory factory;



  public GameImpl(GameFactory factory) {
        this.factory = factory;
        this.agingStrategy = factory.createAgingStrategy();
        this.winningStrategy = factory.createWinningStrategy();
        this.unitActionStrategy = factory.createActionStrategy();
        this.worldStrategy = factory.createWorldStrategy();
        this.battleStrategy = factory.createBattleStrategy();


        tileMap = worldStrategy.getWorldTileMap();
        unitMap = worldStrategy.getUnitMap();
        cityMap = worldStrategy.getCityMap();
  }

  //which player has the turn, RED is set to begin
  Player whosTurn = RED;

  //Gets a new tile terrainTile, and returns it.
  public Tile getTileAt( Position p ) {
    this.p = p;
    Tile tile = tileMap.get(p);
    return tile;
  }
  //Returns a unit at position p
  public Unit getUnitAt( Position p ) {
    return unitMap.get(p);
  }
  //Returns cityPosition at Position p
  public CityImpl getCityAt( Position p ) {
    return cityMap.get(p);
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

    String tileType = tileMap.get(to).getTypeString();
    Player thisUnitOwner = unitMap.get(from).getOwner();;
    Player otherUnitOwner;

    //Test variables for obstacles
    boolean thisPlayersTurn = getPlayerInTurn().equals(thisUnitOwner);
    boolean isMountain = tileType.equals(GameConstants.MOUNTAINS);
    boolean isOcean = tileType.equals(GameConstants.OCEANS);
    boolean isFriendlyUnit = false;
    boolean isEnemyUnit = false;
    boolean isEmpty = unitMap.get(to)== null;
    boolean isEnemyCity = false;
    boolean noCity = cityMap.get(to) == null;

    //If there is a unit at the wanted tile, test if it is friend or foe, and save it in parameters.
    if(!isEmpty){
      otherUnitOwner = unitMap.get(to).getOwner();
      isFriendlyUnit = thisUnitOwner.equals(otherUnitOwner);
      isEnemyUnit = !thisUnitOwner.equals(otherUnitOwner);

    }
    if(!noCity){
      isEnemyCity = !cityMap.get(to).getOwner().equals(getUnitAt(from).getOwner());
    }

    //Decision making, whether to allow the movement or not.
    if(!thisPlayersTurn){return false;}
    else if(isMountain) {return false;}
    else if(isOcean){return false;}
    else if(isFriendlyUnit){return false;}
    else if(isEnemyUnit){
      if(battleStrategy.winBattle(this, to, from)) {
        if(getUnitAt(from).equals(Player.RED)){
          redWinCounter++;
        }
        else{
          blueWincounter++;
        }
        if (isEnemyCity) {
          cityMap.remove(to);
          cityMap.put(to, new CityImpl(getUnitAt(from).getOwner()));
          Unit unit = unitMap.get(from);
          unitMap.remove(from);
          unitMap.put(to, unit);
          return true;
        } else {
          Unit unit = unitMap.get(from);
          unitMap.remove(from);
          unitMap.put(to, unit);
          return true;
        }
      }
      else{

        if(getUnitAt(from).equals(Player.RED)){
          redWinCounter++;
        }
        else{
          blueWincounter++;
        }
        unitMap.remove(from);
        return false;}
    } else if(isEmpty){
        Unit unit = unitMap.get(from);
        unitMap.remove(from);
        unitMap.put(to, unit);
        return true;
    }
    return false;
  }

  //at the end of turn switch turn to the correct player and set age to 100
  public void endOfTurn() {
    if(whosTurn == BLUE){
      whosTurn = RED;
      agingStrategy.aging(); // get older
      cityMap.get(new Position(1,1)).addProductionValue();
      cityMap.get(new Position(4,1)).addProductionValue();
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

    int bombCost = 60;
    int settlerCost = 30;
    int archerCost = 10;
    int legionCost = 15;

    //While loop, Running through the cityHashMap, and produces units, if the city has enough productionValue.

    Iterator<Position> keySetIterator = cityMap.keySet().iterator();

    while(keySetIterator.hasNext()){
      Position position = keySetIterator.next();
      CityImpl city = cityMap.get(position);
      String cityProduction = city.getProduction();
      int currentValue = city.getProductionValue();
      boolean ArcherProducing = cityProduction.equals(GameConstants.ARCHER);
      boolean LegionProducing = cityProduction.equals(GameConstants.LEGION);
      boolean SettlerProducing = cityProduction.equals(GameConstants.SETTLER);
      boolean BombProducing = cityProduction.equals(OurConstants.BOMB);
      boolean enoughForArcher = currentValue >= archerCost;
      boolean enoughForLegion = currentValue >= legionCost;
      boolean enoughForSettler = currentValue >= settlerCost;
      boolean enoughForBomb = currentValue >= bombCost;

      if(ArcherProducing && enoughForArcher){
        city.buyUnit(archerCost);
        unitMap = worldStrategy.createUnit(position, city.getOwner(), cityProduction, unitMap);
      }else if (LegionProducing && enoughForLegion){
        city.buyUnit(legionCost);
        unitMap = worldStrategy.createUnit(position, city.getOwner(), cityProduction, unitMap);
      }else if (SettlerProducing && enoughForSettler){
        city.buyUnit(settlerCost);
        unitMap = worldStrategy.createUnit(position, city.getOwner(), cityProduction, unitMap);
      }else if (BombProducing && enoughForBomb){
        city.buyUnit(bombCost);
        unitMap = worldStrategy.createUnit(position, city.getOwner(), cityProduction, unitMap);
      }
    }

  }
  public void performUnitActionAt( Position p ) {
      if(getUnitAt(p).getTypeString().equals(OurConstants.BOMB)){
        unitMap = unitActionStrategy.unitAction(this, p);
      }else {
        unitActionStrategy.unitAction(this, p);
      }
  }

  public WorldStrategy getWorld(){
    return worldStrategy;
  }

  public HashMap<Position,Unit> getUnitMap(){
      return unitMap;
  }

  public HashMap<Position, CityImpl> getCityMap(){
      return cityMap;
  }

  public int getRedWinCounter(){ return redWinCounter; }
  public int getBlueWinCounter(){ return blueWincounter; }

}
