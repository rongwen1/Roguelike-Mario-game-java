package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;

public class Toad extends Actor {
    private ArrayList<TradableItem> tradableItems;
    private ArrayList<Item> items;
    public Toad() {
        super("Toad", 'O', 9999); // arbitrary amounts of health.
        tradableItems = new ArrayList<>();
        items = new ArrayList<>();

        tradableItems.add(new SuperMushroom());
        tradableItems.add(new PowerStar());
        items.add(new SuperMushroom());
        items.add(new PowerStar());
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        final ActionList actions = super.allowableActions(otherActor, direction, map);
        actions.add(new ToadConverseAction());
        //actions.add(new SellWrenchAction());

        for (int i = 0; i < items.size(); i++){
            actions.add(new TradeAction(items.get(i), tradableItems.get(i)));
        }

        //actions.add((new SellSuperMushroomAction()));
        //actions.add(new SellPowerStarAction());



        return actions;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction(); // Toad does not actively interact with the world.
    }

}
