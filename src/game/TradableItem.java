package game;

import edu.monash.fit2099.engine.actors.Actor;

public interface TradableItem {

    int getValue();

    default void addToWalletManager(){
        WalletManager.getInstance().appendTradableItem(this);
    }

}
