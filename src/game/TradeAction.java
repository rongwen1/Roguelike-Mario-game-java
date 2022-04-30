package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

public class TradeAction extends Action {
    private final TradableItem tradableItem;
    private final Item item;

    public TradeAction(Item item, TradableItem tradableItem){
        this.item = item;
        this.tradableItem = tradableItem;
    }

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

    @Override
    public String menuDescription(Actor actor) {
        return "Player buys " + item.toString() + " ($" + tradableItem.getValue() + ")";
    }
}
