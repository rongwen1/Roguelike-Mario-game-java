package game;

import edu.monash.fit2099.engine.actors.Actor;

public interface TradableItem {

    int getValue();

    //String bought(TradableItem item, Actor by, Actor From);

    default void addToWalletManager(){
        WalletManager.getInstance().appendTradableItem(this);
    }

}
