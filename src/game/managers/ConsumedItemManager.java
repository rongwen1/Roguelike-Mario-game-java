package game.managers;

import game.interfaces.MultiTurnBuffItem;
import game.interfaces.Resettable;

import java.util.ArrayList;

/**
 * <h1>Consumed Item Manager</h1>
 * Stores item that has been consumed by the actor. It manages and
 * checks if the item buff should be removed from the actor every turn.
 */
public class ConsumedItemManager implements Resettable {
    /**
     * List of consumable item
     */
    private final ArrayList<MultiTurnBuffItem> consumedItems;
    /**
     * A singleton consumed item manager instance
     */
    private static ConsumedItemManager instance;

    /**
     * Private constructor for ConsumedItemManager. It initializes new Arraylist
     * and register itself into ResetManager.
     */
    private ConsumedItemManager(){
        consumedItems = new ArrayList<>();
        this.registerInstance();
    }

    /**
     * Get the singleton instance of consumed item manager
     * @return ConsumedItemManager
     */
    public static ConsumedItemManager getInstance(){
        if (instance == null) {
            instance = new ConsumedItemManager();
        }
        return instance;
    }

    /**
     * Method that can append ConsumableItem into Arraylist of consumed items
     * @param item consumable item to be added
     */
    public void addConsumableItem(MultiTurnBuffItem item){
        this.consumedItems.add(item);
    }

    /**
     * Method that can remove ConsumableItem from Arraylist of consumed items
     * @param item consumable item to be removed
     */
    public void removeConsumableItem(MultiTurnBuffItem item){
        this.consumedItems.remove(item);
    }

    /**
     * It can be called every turn to check if consumed item buffs
     * should be removed. If yes, it removes that item from consumed
     * item list and remove buffs from the actor.
     */
    public void consumedItemTicker(){
        //Stores ConsumableItem to be removed
        ArrayList<MultiTurnBuffItem> itemsToBeRemoved = new ArrayList<>();

        //Tick consumed item. If item effect is over, removeBuff will return true.
        for (MultiTurnBuffItem item: this.consumedItems) {
            if (item.removeBuff()){
                itemsToBeRemoved.add(item);
            }
        }

        //Iterate through items to be removed
        for (MultiTurnBuffItem item: itemsToBeRemoved){
            //Remove capability from actor
            item.removeCapabilityFromActor();
            //Remove item from consumedItems list
            this.removeConsumableItem(item);

        }

        //After effect of item is removed from actor, ongoing item effects must be added again
        //to ensure other item effect does not get removed
        for (MultiTurnBuffItem item: this.consumedItems){
            item.addCapabilityToActor();
        }
    }

    /**
     * Called when reset the game
     */
    @Override
    public void resetInstance() {
        //Stores ConsumableItem to be removed
        ArrayList<MultiTurnBuffItem> itemsToBeRemoved = new ArrayList<>();

        //Iterate through consumed items
        for (MultiTurnBuffItem item: this.consumedItems){
            //Add item into itemsToBeRemoved
            itemsToBeRemoved.add(item);
        }

        for (MultiTurnBuffItem item: itemsToBeRemoved){
            //Remove capability from actor
            item.removeCapabilityFromActor();
            //Remove item from consumedItems list
            this.removeConsumableItem(item);
        }
    }

    /**
     * Method that can add this into reset manager
     */
    @Override
    public void registerInstance() {
        Resettable.super.registerInstance();
    }

}
