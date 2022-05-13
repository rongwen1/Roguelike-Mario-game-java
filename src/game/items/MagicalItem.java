package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.ConsumableItem;
import game.actions.ConsumeAction;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Magical Item</h1>
 * Abstract class that represents consumable item that has buff
 * effect for several turns when consumed.
 */
public abstract class MagicalItem extends Item implements ConsumableItem {
    /**
     * Whether this item is current inside actor's inventory
     */
    private boolean isInActorInventory;
    /**
     * Stores actor that consumed this item
     */
    protected Actor buffedActor;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public MagicalItem(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        isInActorInventory = false;
    }

    /**
     * Remove item from actor inventory after called.
     */
    @Override
    public void removeItemFromInventory() {
        buffedActor.removeItemFromInventory(this);
    }

    /**
     * Called once per turn, so that items can experience the passage of time.
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        //Item not in actor inventory
        isInActorInventory = false;

        super.tick(currentLocation);
    }

    /**
     * Called once per turn, so that items can experience the passage of time.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        //Item in actor inventory
        isInActorInventory = true;

        super.tick(currentLocation, actor);
    }

    /**
     * When this method is called, it checks if item is inside actor's inventory.
     * If it is inside actor's inventory, return new action that this item gives.
     * @return List of new actions that this item gives
     */
    @Override
    public List<Action> getAllowableActions() {
        List<Action> actionList = new ArrayList<Action>();
        if (isInActorInventory){
            actionList.add(new ConsumeAction(this));
            return actionList;
        }

        return super.getAllowableActions();
    }

    /**
     * This method will run after actor consumes this item.
     * It will set buffs on actor that consumes this item
     * @param actor
     */
    @Override
    public String consume(Actor actor) {
        buffedActor = actor;
        return actor.toString() + " consumes " + this;
    }

}
