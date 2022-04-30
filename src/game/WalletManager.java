package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

import java.util.ArrayList;

public class WalletManager {

    private ArrayList<TradableItem> tradableItemList;
    private int walletBalance;
    private static WalletManager instance;


    private WalletManager() {
        tradableItemList = new ArrayList<>();
    }

    public static WalletManager getInstance() {
        if (instance == null) {
            instance = new WalletManager();
        }
        return instance;
    }

    public void appendTradableItem(TradableItem item) {
        this.tradableItemList.add(item);
    }

    public ArrayList<TradableItem> getTradableItem() {
        return new ArrayList<TradableItem>(this.tradableItemList);
    }

    public int getWalletBalance(Actor actor){
        walletBalance = 10000;
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


