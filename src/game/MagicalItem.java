package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.List;

public abstract class MagicalItem extends Item implements ConsumableItem{
    protected int turns;
    private boolean isInActorInventory;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public MagicalItem(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        turns = 0;
        isInActorInventory = false;
    }

    @Override
    public void addCapabilityToActor(Actor actor) {
        //Add capability of this item to the actor
        List<Enum<?>> status = this.capabilitiesList();
        for (Enum<?> stat: status){
            actor.addCapability(stat);
        }
    }

    @Override
    public void removeCapabilityFromActor(Actor actor) {
        //Remove capability of this item to the actor
        List<Enum<?>> status = this.capabilitiesList();
        for (Enum<?> stat: status){
            actor.removeCapability(stat);
        }
    }

    @Override
    public void removeItemFromInventory(Actor actor) {
        actor.removeItemFromInventory(this);
    }

    @Override
    public void tick(Location currentLocation) {
        //Increment turns
        turns += 1;
        //Item not in actor inventory
        isInActorInventory = false;

        super.tick(currentLocation);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        //Increment turns
        turns += 1;
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
}
