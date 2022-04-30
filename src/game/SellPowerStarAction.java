package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class SellPowerStarAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        PowerStar powerStar = new PowerStar("Power Star", '*', false);//
        if (powerStar.getValue() <= WalletManager.getInstance().getWalletBalance(actor)) {
            WalletManager.getInstance().appendTradableItem(powerStar);
            actor.addItemToInventory(powerStar);
            return actor + " obtained a Power Star";
        }
        else {
            return "You don't have enough coins!";
        }

    }

    @Override
    public String menuDescription(Actor actor) {
        return "Player buys Power Star ($600)";
    }
}
