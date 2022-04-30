package game;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;

public class ConsumedItemManager {
    private final ArrayList<ConsumableItem> consumedItems;
    /**
     * Instance of ConsumedItemManager
     */
    private static ConsumedItemManager instance;

    private ConsumedItemManager(){
        consumedItems = new ArrayList<>();
    }

    public static ConsumedItemManager getInstance(){
        if (instance == null) {
            instance = new ConsumedItemManager();
        }
        return instance;
    }

    public void addConsumableItem(ConsumableItem item){
        this.consumedItems.add(item);
    }

    public void consumedItemTicker(){
        //Stores ConsumableItem to be removed
        ArrayList<ConsumableItem> itemsToBeRemoved = new ArrayList<>();

        //Tick consumed item. If item effect is over, removeBuff will return true.
        for (ConsumableItem item: this.consumedItems) {
            if (item.removeBuff()){
                itemsToBeRemoved.add(item);
            }
        }

        //Iterate through items to be removed
        for (ConsumableItem item: itemsToBeRemoved){
            //Remove capability from actor
            item.removeCapabilityFromActor();
            //Remove item from consumedItems list
            this.consumedItems.remove(item);

        }

        //After effect of item is removed from actor, ongoing item effects must be added again
        //to ensure other item effect does not get removed
        for (ConsumableItem item: this.consumedItems){
            item.addCapabilityToActor();
        }
    }

}
