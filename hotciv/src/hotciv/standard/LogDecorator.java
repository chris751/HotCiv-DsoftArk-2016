package hotciv.standard;

import hotciv.framework.*;

/**
 * Created by Christian on 07/12/2016.
 */
public class LogDecorator implements Game {

    private GameImpl game;

    public LogDecorator(GameImpl game) {
        this.game = game;
    }

    @Override
    public Tile getTileAt(Position p) {
        return game.getTileAt(p);
    }

    @Override
    public Unit getUnitAt(Position p) {
        return game.getUnitAt(p);
    }

    @Override
    public City getCityAt(Position p) {
        return game.getCityAt(p);
    }

    @Override
    public Player getPlayerInTurn() {
        return game.getPlayerInTurn();
    }

    @Override
    public Player getWinner() {
        System.out.println("Winner is: " + game.getWinner());
        return game.getWinner();

    }

    @Override
    public int getAge() {
        return game.getAge();
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        System.out.println("Player " + game.getUnitAt(from).getOwner() + " moves " + game.getUnitAt(from).getTypeString() + " to " + to);
        if(game.getUnitAt(to)!=null) {
            if (!game.getUnitAt(to).getOwner().equals(game.getUnitAt(from).getOwner())){
                System.out.println("Player " +game.getUnitAt(from).getOwner() + "attacked " + game.getUnitAt(to).getOwner() + " " + game.getUnitAt(to) + " at position" + to  );
            }

        }
        return game.moveUnit(from, to );
    }

    @Override
    public void endOfTurn() {
        System.out.println("Player " + game.getPlayerInTurn() + " ends turn");
        game.endOfTurn();

    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        game.changeWorkForceFocusInCityAt(p, balance);

    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        game.changeProductionInCityAt(p, unitType);

    }

    @Override
    public void performUnitActionAt(Position p) {
        if(game.getUnitAt(p).getTypeString().equals(GameConstants.SETTLER)){
            System.out.println("Player " + game.getPlayerInTurn() + " built city at " + p);
        }
        else if(game.getUnitAt(p).getTypeString().equals(GameConstants.ARCHER)){
            System.out.println("Player " + game.getPlayerInTurn() + " fortified archer at " + p);
        }

        game.performUnitActionAt(p);

    }
}
