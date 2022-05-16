package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Application;
import game.actions.ToadConverseAction;
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
    private ArrayList<TradableItem> tradableItems;

    /**
     * ArrayList of Item
     */
    private ArrayList<Item> items;

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

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        final ActionList actions = super.allowableActions(otherActor, direction, map);
        actions.add(new ToadConverseAction(this));

        for (int i = 0; i < items.size(); i++) {
            actions.add(new TradeAction(items.get(i), tradableItems.get(i)));
        }

        return actions;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (tick++ % 2 == 0) {
            processDialog(display);
        }
        return new DoNothingAction(); // Toad does not actively interact with the world.
    }

}
