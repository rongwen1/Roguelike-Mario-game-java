package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Drinkable;
import game.items.Bottle;

public class RefillAction extends Action {
    private Drinkable drink;

    public RefillAction(Drinkable drink) {
        this.drink = drink;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Bottle.getInstance().appendDrink(drink);
        return actor.toString() + " refills " + drink.toString();
    }


    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " refill " + drink.toString();
    }
}