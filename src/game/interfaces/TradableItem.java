package game.interfaces;

import game.managers.WalletManager;

/**
 * <h1>TradableItem</h1>
 * An interface that will be implemented by all tradable item
 */
public interface TradableItem {
    /**
     * returns value of the item
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
