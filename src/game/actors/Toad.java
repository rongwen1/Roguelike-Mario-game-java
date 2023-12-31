package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Application;
import game.actions.ConverseAction;
import game.actions.TradeAction;
import game.enums.Status;
import game.interfaces.Talkable;
import game.interfaces.TradableItem;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.weapons.Wrench;
import java.util.ArrayList;
import java.util.List;

/**
 * A friendly NPC with goods to trade.
 */
public class Toad extends Actor implements Talkable {

    /**
     * Arraylist of TradableItem
     */
    private final ArrayList<TradableItem> tradableItems;

    /**
     * ArrayList of Item
     */
    private final ArrayList<Item> items;

    /**
     * Number of turns played by the game since this object's instantiation.
     */
    private int tick = 0;

    /**
     * Constructor.
     */
    public Toad() {
        super("Toad", 'O', 9999); // arbitrary amounts of health.

        //list of item that Toad sells
        tradableItems = new ArrayList<>();
        items = new ArrayList<>();
        tradableItems.add(new SuperMushroom("Super Mushroom", '^', false));
        tradableItems.add(new PowerStar("Power Star", '*', false));
        tradableItems.add((new Wrench()));
        items.add(new SuperMushroom("Super Mushroom", '^', false));
        items.add(new PowerStar("Power Star", '*', false));
        items.add(new Wrench());
    }

    /**
     * Provides a list of possible dialog to be chosen from by {@code processDialog()}.
     *
     * @return a list of possible dialog to choose from.
     */
    @Override
    public List<String> getDialog() {
        final List<String> opts = new ArrayList<>();
        opts.add("The Princess is depending on you! You are our only hope.");
        opts.add("Being imprisoned in these walls can drive a fungus crazy :(");
        if (!Application.getCurrentPlayer().hasCapability(Status.BREAKS_KOOPA_SHELL)) {
            opts.add("You might need a wrench to smash Koopa's hard shells.");
        }
        if (!Application.getCurrentPlayer().hasCapability(Status.POWER_STAR_EFFECT_ONGOING)) {
            opts.add("You better get back to finding the Power Stars.");
        }
        return opts;
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        final ActionList actions = super.allowableActions(otherActor, direction, map);
        actions.add(new ConverseAction(this));

        for (int i = 0; i < items.size(); i++) {
            actions.add(new TradeAction(items.get(i), tradableItems.get(i)));
        }

        return actions;
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in
     *                   conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (tick++ % 2 == 0) {
            processDialog(display);
        }
        return new DoNothingAction(); // Toad does not actively interact with the world.
    }

}
