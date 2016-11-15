package hotciv.framework;

/**
 * Created by Christian on 15/11/2016.
 */
public interface World {
    Unit removeUnitAt(Position from);

    void setUnitAt(Position to, Unit unit);

    Unit getUnitAt(Position p);
}
