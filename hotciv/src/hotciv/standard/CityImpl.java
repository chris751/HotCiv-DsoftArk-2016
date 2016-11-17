package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
/**
 * Created by Christian on 15/11/2016.
 */
public class CityImpl implements City{
    private Player owner;
    private String unitProducing = GameConstants.SETTLER;
    private int productionValue = 0;

    public CityImpl(Player owner) {
        this.owner = owner;

    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getProduction() {
        return unitProducing;
    }

    public int getProductionValue(){
        return productionValue;
    }

    public void addProductionValue(){
        productionValue += 6;
    }

    public void changeProduction(String unitType){
        unitProducing = unitType;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }
}
