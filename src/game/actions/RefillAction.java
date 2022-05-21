package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Drinkable;
import game.items.Bottle;

/**
 * <h1>Refill Action</h1>
 * Action for refilling bottle
 */
public class RefillAction extends Action {
    /**
     * drinkable item
     */
    private Drinkable drink;

    /**
     * Constructor
     * @param drink drinkable item
     */
    public RefillAction(Drinkable drink) {
        this.drink = drink;
    }

    /**
     * perform the refill action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return actor performing the action will be printed out in menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Bottle.getInstance().appendDrink(drink);
        return actor.toString() + " refills " + drink.toString();
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return action that actor can perform
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " refill " + drink.toString();
    }
}