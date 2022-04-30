package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.List;

public abstract class MagicalItem extends Item implements ConsumableItem{
    private boolean isInActorInventory;
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

    @Override
    public void removeItemFromInventory() {
        buffedActor.removeItemFromInventory(this);
    }

    @Override
    public void tick(Location currentLocation) {
        //Item not in actor inventory
        isInActorInventory = false;

        super.tick(currentLocation);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        //Item in actor inventory
        isInActorInventory = true;

        super.tick(currentLocation, actor);
    }

    @Override
    public List<Action> getAllowableActions() {
        List<Action> actionList = new ArrayList<Action>();
        if (isInActorInventory){
            actionList.add(new ConsumeAction(this));
            return actionList;
        }

        return super.getAllowableActions();
    }

    @Override
    public void consume(Actor actor) {
        buffedActor = actor;
    }
}
