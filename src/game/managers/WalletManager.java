package game.managers;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.interfaces.TradableItem;
import game.items.Coin;
import java.util.ArrayList;

/**
 * <h1>WalletManager</h1>
 * Class that represents the player's wallet.
 */
public class WalletManager {
    /**
     * An arraylist of TradableItem
     */
    private ArrayList<TradableItem> tradableItemList;
    /**
     * An integer representing the wallet balance
     */
    private int walletBalance;
    /**
     * An instance of WalletManager
     */
    private static WalletManager instance;

    /**
     * Constructor
     */
    private WalletManager() {
        tradableItemList = new ArrayList<>();
    }

    /**
     *
     * @return an instance of WalletManager
     */
    public static WalletManager getInstance() {
        if (instance == null) {
            instance = new WalletManager();
        }
        return instance;
    }

    /**
     *
     * @param item A TradableItem item that needs to be addded into the tradebleItemList
     */
    public void appendTradableItem(TradableItem item) {
        this.tradableItemList.add(item);
    }

    /**
     *
     * @return An arraylist of TradableItem
     */
    public ArrayList<TradableItem> getTradableItem() {
        return new ArrayList<TradableItem>(this.tradableItemList);
    }

    /**
     *
     * @param actor Actor that has the wallet
     * @return An integer of the wallet balance
     */
    public int getWalletBalance(Actor actor){
        walletBalance = 1000;

        for(TradableItem item: getTradableItem()){
            walletBalance -= item.getValue();
        }

        for(Item item: actor.getInventory()){
            if(item instanceof Coin){
                walletBalance += ((Coin) item).getValue();
            }
        }
        return walletBalance;
    }
}


