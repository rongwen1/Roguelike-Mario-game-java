package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
/**
 * <h1>TradeAction</h1>
 * Special action that trading item with player.
 */
public class TradeAction extends Action {
    /**
     * TradebleItem that is traded
     */
    private final TradableItem tradableItem;
    /**
     * Item that is traded
     */
    private final Item item;

    /**
     * Constructor
     * @param item An item to be traded
     * @param tradableItem An tradebleItem to be traded
     */
    public TradeAction(Item item, TradableItem tradableItem){
        this.item = item;
        this.tradableItem = tradableItem;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String that representing the result.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (this.tradableItem.getValue() <= WalletManager.getInstance().getWalletBalance(actor)) {
            WalletManager.getInstance().appendTradableItem(this.tradableItem);
            actor.addItemToInventory(this.item);
            return actor + " obtained a " + item.toString();
        }
        else {
            return "You don't have enough coins!";
        }
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return A string that representing the action can be done by actor on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Player buys " + item.toString() + " ($" + tradableItem.getValue() + ")";
    }
}
