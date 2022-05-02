package game;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * <h1>TradableItem</h1>
 * An interface that will be implemented by all tradable item
 */
public interface TradableItem {
    /**
     *
     * @return the value of the item
     */
    int getValue();

    /**
     * adding the tradable item into an arraylist
     */
    default void addToWalletManager(){
        WalletManager.getInstance().appendTradableItem(this);
    }

}
