package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.PowerStar;
import game.items.SuperMushroom;

import java.util.ArrayList;

public class Toad extends Actor {
    private ArrayList<TradableItem> tradableItems;
    private ArrayList<Item> items;
    public Toad() {
        super("Toad", 'O', 9999); // arbitrary amounts of health.
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
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        final ActionList actions = super.allowableActions(otherActor, direction, map);
        actions.add(new ToadConverseAction());

        for (int i = 0; i < items.size(); i++){
            actions.add(new TradeAction(items.get(i), tradableItems.get(i)));
        }

        return actions;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction(); // Toad does not actively interact with the world.
    }

}
